package phoneisure.domain.service.merchant;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.merchant.command.*;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.commons.Constants;
import phoneisure.core.commons.PasswordHelper;
import phoneisure.core.enums.AuthStatus;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.enums.UserType;
import phoneisure.core.enums.YesOrNoStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.exception.NotCreateException;
import phoneisure.core.exception.PasswordNotEquals;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.model.merchant.IMerchantRepository;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.model.role.Role;
import phoneisure.domain.service.account.IAccountService;
import phoneisure.domain.service.appkey.IAppKeyService;
import phoneisure.domain.service.area.IAreaService;
import phoneisure.domain.service.role.IRoleService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("merchantService")
public class MerchantService implements IMerchantService {

    @Autowired
    private IMerchantRepository<Merchant, String> merchantRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAppKeyService appKeyService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAreaService areaService;

    @Override
    public Pagination<Merchant> pagination(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Pagination<Merchant> paginationAgency(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("userType", UserType.AGENT));
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        if (!CoreStringUtils.isEmpty(command.getMerchantName())) {
            criterionList.add(Restrictions.like("merchantName", command.getMerchantName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            List<String> ids = getChildrenId(new ArrayList<String>(), command.getAgent());
            criterionList.add(Restrictions.in("agent.id", ids));
        }
        List<Order> orderList = new ArrayList<Order>();
        return merchantRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Pagination<Merchant> paginationWaitAgency(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("userType", UserType.AGENT));
        criterionList.add(Restrictions.eq("status", EnableStatus.DISABLE));

        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            List<String> ids = getChildrenId(new ArrayList<String>(), command.getAgent());
            criterionList.add(Restrictions.in("agent.id", ids));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Merchant searchByID(String id) {
        Merchant merchant = merchantRepository.getById(id);
        if (null == merchant) {
            throw new NoFoundException("没有找到ID[" + id + "]的Merchant数据");
        }
        return merchant;
    }

    @Override
    public void auth(SharedCommand command) {
        Merchant merchant = this.searchByID(command.getId());
        merchant.fainWhenConcurrencyViolation(command.getVersion());
        if (merchant.getStatus() == EnableStatus.DISABLE) {
            merchant.changeStatus(EnableStatus.ENABLE);
            merchant.changeAuthStatus(AuthStatus.SUCCESS);
        }
        merchantRepository.update(merchant);
    }

    @Override
    public void agency(SharedCommand command) {
        Merchant merchant = this.searchByID(command.getId());
        merchant.fainWhenConcurrencyViolation(command.getVersion());
        if (merchant.getStatus() == EnableStatus.DISABLE) {
            merchant.changeStatus(EnableStatus.ENABLE);
            merchant.changeAuthStatus(AuthStatus.SUCCESS);
        }
    }

    @Override
    public void create(RegisterMerchantCommand command) {
        if (null != accountService.searchByAccountName(command.getUserName())) {
            throw new ExistException("userName[" + command.getUserName() + "]的Account数据已存在");
        }
        AppKey appKey = null;
        List<Role> roleList = new ArrayList<Role>();
        Merchant agent = null;
        Role role = roleService.searchByName("merchant", command.getAppKey());
        roleList.add(role);
        appKey = appKeyService.searchByName(command.getAppKey());
        agent = this.searchByUserName(command.getAgent(), Constants.APP_KRY);
        if (null == agent) {
            throw new NoFoundException("Agent[" + command.getAgent() + "]代理不存在");
        }
        Area area = areaService.searchByID(command.getArea());
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);
        Merchant merchant = new Merchant(command.getUserName(), password, salt, null, null, null, roleList, null, appKey, EnableStatus.DISABLE, null,
                command.getMerchantName(), command.getContacts(), command.getContactsPhone(), area, command.getDetailedArea(),
                command.getRemarks(), new BigDecimal(0), AuthStatus.NOT, null, UserType.MERCHANT, agent);
        merchantRepository.save(merchant);
    }

    @Override
    public Merchant searchByUserName(String parentAgent, String appKey) {
        return merchantRepository.searchByUserName(parentAgent, appKey);
    }

    @Override
    public Merchant searchByUserName(String parentAgent) {
        return merchantRepository.searchByUserName(parentAgent);
    }

    @Override
    public Merchant searchByArea(String areaId, String appKey) {
        return merchantRepository.searchByArea(areaId, appKey);
    }

    @Override
    public Pagination<Merchant> paginationWaitMerchant(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("userType", UserType.MERCHANT));
        criterionList.add(Restrictions.eq("status", EnableStatus.DISABLE));
        criterionList.add(Restrictions.eq("authStatus", AuthStatus.NOT));

        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            List<String> ids = getChildrenId(new ArrayList<String>(), command.getAgent());
            criterionList.add(Restrictions.in("agent.id", ids));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Pagination<Merchant> paginationMerchant(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getMerchantName())) {
            criterionList.add(Restrictions.like("merchantName", command.getMerchantName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            Merchant agent = this.searchByUserName(command.getAgent(), Constants.APP_KRY);
            if (null != agent) {
                List<String> ids = getChildrenId(new ArrayList<String>(), agent.getId());
                criterionList.add(Restrictions.in("agent.id", ids));
            } else {
                List<String> ids = getChildrenId(new ArrayList<String>(), command.getAgent());
                criterionList.add(Restrictions.in("agent.id", ids));
            }
        }
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (command.getIsRecharge() == YesOrNoStatus.YES) {
            criterionList.add(Restrictions.ne("money", new BigDecimal(0)));
        } else if (command.getIsRecharge() == YesOrNoStatus.NO) {
            criterionList.add(Restrictions.eq("money", new BigDecimal(0)));
        }
        criterionList.add(Restrictions.eq("userType", UserType.MERCHANT));
        criterionList.add(Restrictions.eq("status", EnableStatus.ENABLE));

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Pagination<Merchant> paginationByAgent(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getMerchantName())) {
            criterionList.add(Restrictions.like("merchantName", command.getMerchantName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        criterionList.add(Restrictions.eq("userType", UserType.MERCHANT));
        criterionList.add(Restrictions.in("agent.id", getChildrenId(new ArrayList<String>(), command.getAgent())));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<Merchant> agentList() {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("userType", UserType.AGENT));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.list(criterionList, orderList);
    }

    @Override
    public void changeAgent(ChangeAgentCommand command) {
        Merchant merchant = this.searchByID(command.getMerchant());
        Merchant agent = this.searchByID(command.getAgent());
        merchant.changeAgent(agent);
        merchantRepository.update(merchant);
    }

    @Override
    public void update(Merchant merchant) {
        merchantRepository.update(merchant);
    }

    @Override
    public List<Merchant> searchByParent(String id) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("agent.id", id));
        criterionList.add(Restrictions.eq("userType", UserType.AGENT));
        return merchantRepository.list(criterionList, null);
    }

    @Override
    public Merchant createAgent(CreateAgentCommand command) {
        if (null != accountService.searchByAccountName(command.getUserName())) {
            throw new ExistException("userName[" + command.getUserName() + "]的Account数据已存在");
        }

        List<Role> roleList = new ArrayList<Role>();
        Role role = roleService.searchByName("agent", Constants.APP_KRY);
        roleList.add(role);
        AppKey appKey = appKeyService.searchByName(Constants.APP_KRY);
        Merchant agent = this.searchByID(command.getAgent());
        Area area = areaService.searchByID(command.getArea());
        int index = 0;
        Merchant parentAgent = agent.getAgent();
        while (true) {
            if (parentAgent != null) {
                parentAgent = parentAgent.getAgent();
                index++;
            } else {
                break;
            }
        }
        if (index == 3) {
            throw new NotCreateException("不能创建下级代理");
        }

        if (null != this.searchByArea(command.getArea(), appKey.getId())) {
            throw new ExistException("此区域已有代理");
        }
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);

        Merchant merchant = new Merchant(command.getUserName(), password, salt, null, null, null, roleList, null, appKey, EnableStatus.ENABLE, null,
                command.getMerchantName(), command.getContacts(), command.getContactsPhone(), area, command.getDetailedArea(),
                null, new BigDecimal(0), AuthStatus.SUCCESS, null, UserType.AGENT, agent);

        merchantRepository.save(merchant);
        return merchant;
    }

    @Override
    public List<Merchant> apiSearchAgent(ListMerchantCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("appKey.name", command.getAppKey()));
        criterionList.add(Restrictions.eq("status", EnableStatus.ENABLE));
        criterionList.add(Restrictions.eq("userType", UserType.AGENT));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("appKey", "appKey");
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return merchantRepository.list(criterionList, orderList, null, null, alias);
    }

    @Override
    public void apiUpdatePassword(UpdatePasswordCommand command) {
        Merchant merchant = this.searchByID(command.getMerchant());
        if (!PasswordHelper.equalsPassword(merchant, command.getOldPassword())) {
            throw new PasswordNotEquals("旧密码错误");
        }
        String password = PasswordHelper.encryptPassword(command.getNewPassword(), merchant.getSalt());
        merchant.changePassword(password);
        merchantRepository.update(merchant);
    }

    /**
     * 获取代理所有下级代理ID
     *
     * @param id
     * @return
     */
    @Override
    public List<String> getChildrenId(List<String> ids, String id) {
        ids.add(id);
        List<Merchant> merchantList = this.searchByParent(id);
        if (merchantList.size() > 0 && null != merchantList) {
            for (Merchant merchant : merchantList) {
                getChildrenId(ids, merchant.getId());
            }
        }
        return ids;
    }

    @Override
    public BigDecimal userMoney(List<String> ids) {
        return merchantRepository.userMoney(ids);
    }
}
