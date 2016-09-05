package phoneisure.domain.service.policyfee;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.policyfee.command.CreatePolicyFeeCommand;
import phoneisure.application.policyfee.command.EditPolicyFeeCommand;
import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.domain.model.policyfee.IPolicyFeeRepository;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.domain.service.phonebrand.IPhoneBrandService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LvDi on 2016/4/23.
 */
@Service("policyFeeService")
public class PolicyFeeService implements IPolicyFeeService {

    @Autowired
    private IPhoneBrandService phoneBrandService;

    @Autowired
    private IPolicyFeeRepository<PolicyFee, String> policyFeeRepository;

    @Override
    public Pagination<PolicyFee> pagination(ListPolicyFeeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getPhoneBrand())) {
            criterionList.add(Restrictions.like("phoneBrand.name", command.getPhoneBrand(), MatchMode.ANYWHERE));
            aliasMap.put("phoneBrand", "phoneBrand");
        }

        if (!CoreStringUtils.isEmpty(command.getPhoneModel())) {
            criterionList.add(Restrictions.like("phoneModel", command.getPhoneModel(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return policyFeeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public PolicyFee searchByID(String id) {
        PolicyFee policyFee = policyFeeRepository.getById(id);
        if (null == policyFee) {
            throw new NoFoundException("没有找到ID[" + id + "]的PolicyFee数据");
        }
        return policyFee;
    }

    @Override
    public PolicyFee create(CreatePolicyFeeCommand command) {

        PhoneBrand phoneBrand = phoneBrandService.searchByID(command.getPhoneBrand());
        if (null == phoneBrand || phoneBrand.getStatus() == EnableStatus.DISABLE) {
            throw new NoFoundException("没有找到PhoneBrand[" + command.getPhoneBrand() + "]的PhoneBrand数据");
        }
        if (null != policyFeeRepository.getByPhoneModel(command.getPhoneModel())) {
            throw new ExistException("PolicyFee[" + command.getPhoneModel() + "]的PolicyFee数据已存在");
        }
        PolicyFee policyFee = new PolicyFee(phoneBrand, command.getPhoneModel(), command.getPolicyFee(), command.getSort(), command.getStatus(), command.getPolicyMoney());
        policyFeeRepository.save(policyFee);
        return policyFee;
    }

    @Override
    public PolicyFee edit(EditPolicyFeeCommand command) {
        PolicyFee policyFee = this.searchByID(command.getId());

        PhoneBrand phoneBrand = phoneBrandService.searchByID(command.getPhoneBrand());
        if (null == phoneBrand) {
            throw new NoFoundException("没有找到ID[" + command.getId() + "]的PolicyFee手机品牌");
        }
        policyFee.changePhoneBrand(phoneBrand);
        policyFee.changePhoneModel(command.getPhoneModel());
        policyFee.changePolicyFee(command.getPolicyFee());
        policyFee.changePolicyMoney(command.getPolicyMoney());
        policyFee.changeSort(command.getSort());
        policyFee.changeStatus(command.getStatus());
        policyFeeRepository.update(policyFee);
        return policyFee;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        PolicyFee policyFee = this.searchByID(command.getId());
        policyFee.fainWhenConcurrencyViolation(command.getVersion());
        if (policyFee.getStatus() == EnableStatus.DISABLE) {
            policyFee.changeStatus(EnableStatus.ENABLE);
        } else {
            policyFee.changeStatus(EnableStatus.DISABLE);
        }
        policyFeeRepository.update(policyFee);

    }

    @Override
    public List<PolicyFee> list(ListPolicyFeeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        if (!CoreStringUtils.isEmpty(command.getPhoneBrand())) {
            criterionList.add(Restrictions.eq("phoneBrand.id", command.getPhoneBrand()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("sort"));
        return policyFeeRepository.list(criterionList, orderList);
    }
}
