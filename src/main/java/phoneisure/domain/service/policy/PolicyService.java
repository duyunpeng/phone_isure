package phoneisure.domain.service.policy;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.moneydetailed.command.CreateMoneyDetailedCommand;
import phoneisure.application.picture.command.CreatePictureCommand;
import phoneisure.application.policy.command.*;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.commons.Constants;
import phoneisure.core.commons.id.IdFactory;
import phoneisure.core.enums.FlowType;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.MoneyNotEnoughException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.exception.TimeOutException;
import phoneisure.core.upload.IFileUploadService;
import phoneisure.core.util.CoreDateUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.model.picture.Picture;
import phoneisure.domain.model.policy.IPolicyRepository;
import phoneisure.domain.model.policy.Policy;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.domain.service.idtype.IIdTypeService;
import phoneisure.domain.service.merchant.IMerchantService;
import phoneisure.domain.service.moneydetailed.IMoneyDetailedService;
import phoneisure.domain.service.picture.IPictureService;
import phoneisure.domain.service.policyfee.IPolicyFeeService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by LvDi on 2016/4/22.
 */
@Service("policyService")
public class PolicyService implements IPolicyService {

    @Autowired
    private IPolicyRepository<Policy, String> policyRepository;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IIdTypeService idTypeService;

    @Autowired
    private IPolicyFeeService policyFeeService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IdFactory idFactory;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Override
    public Pagination<Policy> pagination(ListPolicyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
            aliasMap.put("merchant", "merchant");
        }
        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            Merchant agent = merchantService.searchByUserName(command.getAgent(), Constants.APP_KRY);
            if (null != agent) {
                List<String> ids = getChildrenId(new ArrayList<String>(), agent.getId());
                criterionList.add(Restrictions.in("merchant.agent.id", ids));
                aliasMap.put("merchant", "merchant");
            }
        }
        if (!CoreStringUtils.isEmpty(command.getPolicyNo())) {
            criterionList.add(Restrictions.like("policyNo", command.getPolicyNo(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getInsuredName())) {
            criterionList.add(Restrictions.like("insuredName", command.getInsuredName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getIMEI())) {
            criterionList.add(Restrictions.like("IMEI", command.getIMEI(), MatchMode.ANYWHERE));
        }
        if (null != command.getPolicyStatus() && command.getPolicyStatus() != PolicyStatus.ALL) {
            criterionList.add(Restrictions.eq("policyStatus", command.getPolicyStatus()));
        }
        if (!CoreStringUtils.isEmpty(command.getStartTime()) && null != CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && null != CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }


        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        return policyRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public Pagination<Policy> chargeBackPagination(ListPolicyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        criterionList.add(Restrictions.eq("policyStatus", PolicyStatus.BACK));
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
            aliasMap.put("merchant", "merchant");
        }
        if (!CoreStringUtils.isEmpty(command.getPolicyNo())) {
            criterionList.add(Restrictions.like("policyNo", command.getPolicyNo(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getInsuredName())) {
            criterionList.add(Restrictions.like("insuredName", command.getInsuredName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getIMEI())) {
            criterionList.add(Restrictions.like("IMEI", command.getIMEI(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getStartTime()) && null != CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && null != CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }

        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            Merchant agent = merchantService.searchByID(command.getAgent());
            if (null != agent) {
                List<String> ids = getChildrenId(new ArrayList<String>(), agent.getId());
                criterionList.add(Restrictions.in("merchant.agent.id", ids));
                aliasMap.put("merchant", "merchant");
            }
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        return policyRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public Pagination<Policy> claimForCompensationPagination(ListPolicyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        criterionList.add(Restrictions.eq("policyStatus", PolicyStatus.NEED_CLAIM));
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
            aliasMap.put("merchant", "merchant");
        }
        if (!CoreStringUtils.isEmpty(command.getPolicyNo())) {
            criterionList.add(Restrictions.like("policyNo", command.getPolicyNo(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getInsuredName())) {
            criterionList.add(Restrictions.like("insuredName", command.getInsuredName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getIMEI())) {
            criterionList.add(Restrictions.like("IMEI", command.getIMEI(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getStartTime()) && null != CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && null != CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }


        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        return policyRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }


    @Override
    public Pagination<Policy> paginationByAgent(ListPolicyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        List<String> ids = getChildrenId(new ArrayList<String>(), command.getAgent());
        criterionList.add(Restrictions.in("merchant.agent.id", ids));
        aliasMap.put("merchant", "merchant");
//        aliasMap.put("merchant.agent", "merchant.agent");
        if (!CoreStringUtils.isEmpty(command.getAgentUserName())) {
            Merchant agent = merchantService.searchByUserName(command.getAgentUserName(), Constants.APP_KRY);
            if (null != agent) {
                ids = getChildrenId(new ArrayList<String>(), agent.getId());
                criterionList.add(Restrictions.in("merchant.agent.id", ids));
                aliasMap.put("merchant", "merchant");
            }
        }
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getPolicyNo())) {
            criterionList.add(Restrictions.like("policyNo", command.getPolicyNo(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getInsuredName())) {
            criterionList.add(Restrictions.like("insuredName", command.getInsuredName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getIMEI())) {
            criterionList.add(Restrictions.like("IMEI", command.getIMEI(), MatchMode.ANYWHERE));
        }
        if (null != command.getPolicyStatus() && command.getPolicyStatus() != PolicyStatus.ALL) {
            criterionList.add(Restrictions.eq("policyStatus", command.getPolicyStatus()));
        }
        if (!CoreStringUtils.isEmpty(command.getStartTime()) && null != CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && null != CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return policyRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public Pagination<Policy> policyCount(List<String> ids, PolicyStatus policyStatus) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        criterionList.add(Restrictions.in("merchant.agent.id", ids));
        aliasMap.put("merchant", "merchant");
        if (policyStatus != PolicyStatus.ALL && null != policyStatus) {
            criterionList.add(Restrictions.eq("policyStatus", policyStatus));
        }
        return policyRepository.pagination(1, 1, criterionList, aliasMap, null, null);
    }

    /**
     * 获取代理所有下级代理ID
     *
     * @param id
     * @return
     */
    public List<String> getChildrenId(List<String> ids, String id) {
        ids.add(id);
        List<Merchant> merchantList = merchantService.searchByParent(id);
        if (merchantList.size() > 0 && null != merchantList) {
            for (Merchant merchant : merchantList) {
                getChildrenId(ids, merchant.getId());
            }
        }
        return ids;
    }

    @Override
    public void updatePolicyStatus(UpdatePolicyStatusCommand command) {
        Policy policy = this.searchByID(command.getId());
        if (policy.getPolicyStatus() == PolicyStatus.BACK) {
            policy.fainWhenConcurrencyViolation(command.getVersion());

            Merchant merchant = merchantService.searchByID(policy.getMerchant().getId());
            merchant.changeMoney(merchant.getMoney().add((policy.getPolicyFee().subtract(new BigDecimal(2)))));
            merchantService.update(merchant);

            CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
            moneyDetailedCommand.setMerchant(merchant.getId());
            moneyDetailedCommand.setMoney(policy.getPolicyFee().subtract(new BigDecimal(2)));
            moneyDetailedCommand.setExplain("退保.保单订单号[" + policy.getPolicyNo() + "]");
            moneyDetailedCommand.setFlowType(FlowType.IN_FLOW);
            moneyDetailedCommand.setInsuredName(policy.getInsuredName());
            moneyDetailedService.create(moneyDetailedCommand);

            policy.changePolicyStatus(PolicyStatus.SUCCESS_BACK);
            policy.changeDate(new Date());
            policyRepository.update(policy);
        } else {
            throw new ExistException("保单数据重复提交");
        }
    }

    @Override
    public void handlePolicy(HandlePolicyCommand command) {
        Policy policy = this.searchByID(command.getId());

        List<Picture> pictureList = new ArrayList<Picture>();
        for (String item : command.getInsuredAfterPicture()) {
            File imgFile = fileUploadService.moveToImg(item);
            CreatePictureCommand picCommand = new CreatePictureCommand();
            picCommand.setName(imgFile.getName());
            picCommand.setPicPath(fileUploadService.getHttpPath("img") + "/" + imgFile.getName());
            picCommand.setMiniPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMiniImgFile(imgFile.getName()).getName());
            picCommand.setMediumPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMediumImgFile(imgFile.getName()).getName());
            picCommand.setSize((double) FileUtils.sizeOf(imgFile) / 1024 / 1024);//单位MB
            pictureList.add(pictureService.create(picCommand));
        }
        policy.changeInsuredAfterPicture(pictureList);
        policy.changeInsuredEndDate(new Date());
        policy.changePolicyStatus(PolicyStatus.NEED_CLAIM);
        policy.changeDate(new Date());
        policyRepository.update(policy);
    }

    @Override
    public void successClaim(String id) {
        Policy policy = this.searchByID(id);
        if (policy.getPolicyStatus() == PolicyStatus.NEED_CLAIM) {
            policy.changePolicyStatus(PolicyStatus.SUCCESS_CLAIM);
            policy.changeDate(new Date());
            policyRepository.update(policy);
        } else {
            throw new ExistException("保单数据重复提交");
        }
    }

    @Override
    public List<Policy> exportExcel(ListPolicyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        HashMap<String, String> aliasMap = new HashMap<String, String>();

        Merchant merchant = merchantService.searchByUserName(command.getAgentUserName(), Constants.APP_KRY);
        if (null != merchant) {
            List<String> ids = getChildrenId(new ArrayList<String>(), merchant.getId());
            criterionList.add(Restrictions.in("merchant.agent.id", ids));
            aliasMap.put("merchant", "merchant");
        }

        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            List<String> ids = getChildrenId(new ArrayList<String>(), command.getAgent());
            criterionList.add(Restrictions.in("merchant.agent.id", ids));
            aliasMap.put("merchant", "merchant");
        }
        if (!CoreStringUtils.isEmpty(command.getBeginDate()) && null != CoreDateUtils.parseDate(command.getBeginDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getBeginDate(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDate()) && null != CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDate(), "yyyy/MM/dd HH:mm")));
        }

        if (null != command.getPolicyStatus() && command.getPolicyStatus() != PolicyStatus.ALL) {
            criterionList.add(Restrictions.eq("policyStatus", command.getPolicyStatus()));
        }

        orderList.add(Order.desc("createDate"));
        return policyRepository.list(criterionList, orderList, null, null, aliasMap);
    }


    @Override
    public Policy searchByID(String id) {
        Policy policy = policyRepository.getById(id);
        if (null == policy) {
            throw new NoFoundException("没有找到ID[" + id + "]的Policy数据");
        }
        return policy;
    }

    @Override
    public List<Policy> searchByIMEI(String imei) {
        return policyRepository.searchByIMEI(imei);
    }

    @Override
    public void adminReturnPolicy(UpdatePolicyStatusCommand command) {
        Policy policy = this.searchByID(command.getId());

        policy.fainWhenConcurrencyViolation(command.getVersion());

        Merchant merchant = merchantService.searchByID(policy.getMerchant().getId());
        merchant.changeMoney(merchant.getMoney().add((policy.getPolicyFee().subtract(new BigDecimal(2)))));
        merchantService.update(merchant);

        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
        moneyDetailedCommand.setMerchant(merchant.getId());
        moneyDetailedCommand.setMoney(policy.getPolicyFee().subtract(new BigDecimal(2)));
        moneyDetailedCommand.setExplain("退保.保单订单号[" + policy.getPolicyNo() + "]");
        moneyDetailedCommand.setFlowType(FlowType.IN_FLOW);
        moneyDetailedCommand.setInsuredName(policy.getInsuredName());
        moneyDetailedService.create(moneyDetailedCommand);

        policy.changePolicyStatus(PolicyStatus.SUCCESS_BACK);
        policy.changeDate(new Date());
        policyRepository.update(policy);

    }

    @Override
    public void updatePic(UpdatePolicyPicCommand command) {
        Policy policy = this.searchByID(command.getId());
        policy.fainWhenConcurrencyViolation(command.getVersion());
        List<Picture> oldPicture = policy.getInsuredAfterPicture();

        List<Picture> pictureList = new ArrayList<>();
        for (String item : command.getPics()) {
            File imgFile = fileUploadService.moveToImg(item);
            if (null != imgFile) {
                CreatePictureCommand picCommand = new CreatePictureCommand();
                picCommand.setName(imgFile.getName());
                picCommand.setPicPath(fileUploadService.getHttpPath("img") + "/" + imgFile.getName());
                picCommand.setMiniPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMiniImgFile(imgFile.getName()).getName());
                picCommand.setMediumPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMediumImgFile(imgFile.getName()).getName());
                picCommand.setSize((double) FileUtils.sizeOf(imgFile) / 1024 / 1024);//单位MB
                pictureList.add(pictureService.create(picCommand));
            } else {
                for (Picture picture : oldPicture) {
                    if (picture.getPicPath().equals(item)) {
                        pictureList.add(picture);
                    }
                }
            }
        }

        policy.changeInsuredAfterPicture(pictureList);
        policyRepository.update(policy);
    }

    @Override
    public void apiCreate(CreatePolicyCommand command) {
        Merchant merchant = merchantService.searchByID(command.getMerchant());
        IdType idType = idTypeService.searchByID(command.getIdType());
        PolicyFee policyFee = policyFeeService.searchByID(command.getPhoneModel());
        List<Picture> pictureList = new ArrayList<Picture>();

        if (merchant.getMoney().compareTo(policyFee.getPolicyFee()) == -1) {
            throw new MoneyNotEnoughException();
        }

        List<Policy> imeiCheck = searchByIMEI(command.getIMEI());
        for (Policy policy : imeiCheck) {
            if (policy.getIMEI().equals(command.getIMEI()) && policy.getPolicyStatus() != PolicyStatus.SUCCESS_BACK)
                throw new ExistException("IMEI号[" + command.getIMEI() + "]已存在");
        }
        String policyNo = idFactory.getNextId();
        for (String item : command.getInsuredBeginPicture()) {
            File imgFile = fileUploadService.moveToImg(item);
            CreatePictureCommand picCommand = new CreatePictureCommand();
            picCommand.setName(imgFile.getName());
            picCommand.setPicPath(fileUploadService.getHttpPath("img") + "/" + imgFile.getName());
            picCommand.setMiniPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMiniImgFile(imgFile.getName()).getName());
            picCommand.setMediumPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMediumImgFile(imgFile.getName()).getName());
            picCommand.setSize((double) FileUtils.sizeOf(imgFile) / 1024 / 1024);//单位MB
            pictureList.add(pictureService.create(picCommand));
        }
        Policy policy = new Policy(policyNo, merchant, policyFee.getPhoneBrand().getName() + " " + policyFee.getPhoneModel(), policyFee.getPolicyFee(), policyFee.getPolicyMoney(),
                command.getInsuredName(), command.getInsuredPhone(), pictureList, null, idType, command.getIdNumber(), new Date(), null, PolicyStatus.NORMAL, command.getIMEI());
        policyRepository.save(policy);
        merchant.changeMoney(merchant.getMoney().subtract(policyFee.getPolicyFee()));
        merchantService.update(merchant);
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
        moneyDetailedCommand.setMerchant(merchant.getId());
        moneyDetailedCommand.setMoney(policyFee.getPolicyFee());
        moneyDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        moneyDetailedCommand.setExplain("添加保单.保单订单号[" + policy.getPolicyNo() + "]");
        moneyDetailedCommand.setInsuredName(policy.getInsuredName());
        moneyDetailedService.create(moneyDetailedCommand);
    }

    @Override
    public Pagination<Policy> apiPagination(ListPolicyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.eq("merchant.id", command.getMerchant()));
        }
        if (null != command.getPolicyStatus()) {
            criterionList.add(Restrictions.eq("policyStatus", command.getPolicyStatus()));
        }
        if (command.isClaim()) {
            criterionList.add(Restrictions.or(Restrictions.eq("policyStatus", PolicyStatus.NEED_CLAIM),
                    Restrictions.eq("policyStatus", PolicyStatus.SUCCESS_CLAIM)));
        }
        if (command.isBack()) {
            criterionList.add(Restrictions.or(Restrictions.eq("policyStatus", PolicyStatus.BACK),
                    Restrictions.eq("policyStatus", PolicyStatus.SUCCESS_BACK)));
        }
        if (!CoreStringUtils.isEmpty(command.getBeginDate()) && null != CoreDateUtils.parseDate(command.getBeginDate(), CoreDateUtils.DATETIME)) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getBeginDate(), CoreDateUtils.DATETIME)));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDate()) && null != CoreDateUtils.parseDate(command.getEndDate(), CoreDateUtils.DATETIME)) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDate(), CoreDateUtils.DATETIME)));
        }
        if (!CoreStringUtils.isEmpty(command.getPolicyNo())) {
            criterionList.add(Restrictions.like("policyNo", command.getPolicyNo(), MatchMode.ANYWHERE));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        return policyRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public void apiReturnPolicy(SharedCommand command) {
        Policy policy = this.searchByID(command.getId());
        if (CoreDateUtils.diffHour(new Date(), policy.getCreateDate()) > 10) {
            throw new TimeOutException("超过操作时间");
        }

        if (policy.getPolicyStatus() == PolicyStatus.NORMAL) {
            policy.changePolicyStatus(PolicyStatus.BACK);
            policy.changeDate(new Date());
        } else {
            throw new ExistException("保单数据重复提交");
        }
        policyRepository.update(policy);
    }

    @Override
    public Pagination<Policy> apiPolicyCount(SharedCommand command, PolicyStatus policyStatus) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("merchant.id", command.getId()));
        if (policyStatus != PolicyStatus.ALL && null != policyStatus) {
            criterionList.add(Restrictions.eq("policyStatus", policyStatus));
        }
        return policyRepository.pagination(1, 1, criterionList, null);
    }
}
