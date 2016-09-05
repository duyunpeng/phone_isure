package phoneisure.application.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.policy.command.CreatePolicyCommand;
import phoneisure.application.policy.command.ListPolicyCommand;
import phoneisure.application.policy.representation.PolicyCountRepresentation;
import phoneisure.application.policy.representation.PolicyRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.core.mapping.IMappingService;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.policy.Policy;
import phoneisure.domain.service.policy.IPolicyService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("apiPolicyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiPolicyAppService implements IApiPolicyAppService {

    @Autowired
    private IPolicyService policyService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ApiResponse create(CreatePolicyCommand command) {
        if (CoreStringUtils.isEmpty(command.getMerchant())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "merchant字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getPhoneModel())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "phoneModel字段不能为空", null);
        }
//        if (null == command.getPolicyFee()) {
//            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "policyFee字段不能为空", null);
//        }
        if (CoreStringUtils.isEmpty(command.getInsuredName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "insuredName字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getInsuredPhone())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "insuredPhone字段不能为空", null);
        }
        if (null == command.getInsuredBeginPicture() || command.getInsuredBeginPicture().size() == 0) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "insuredBeginPicture字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getIdType())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "idType字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getIdNumber())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "idNumber字段不能为空", null);
        }
        if(CoreStringUtils.isEmpty(command.getIMEI())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "IMEI字段不能为空", null);
        }
        policyService.apiCreate(command);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), null);
    }

    @Override
    public ApiResponse list(ListPolicyCommand command) {
        if (CoreStringUtils.isEmpty(command.getMerchant())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "merchant字段不能为空", null);
        }
        command.verifyPage();
        command.verifyPageSize(10);
        Pagination<Policy> pagination = policyService.apiPagination(command);
        List<PolicyRepresentation> data = mappingService.mapAsList(pagination.getData(), PolicyRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<PolicyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }

    @Override
    public ApiResponse returnPolicy(SharedCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id字段不能为空", null);
        }
        policyService.apiReturnPolicy(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse policyCount(SharedCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id字段不能为空", null);
        }
        Integer all = policyService.apiPolicyCount(command, PolicyStatus.ALL).getCount();
        Integer back = policyService.apiPolicyCount(command, PolicyStatus.SUCCESS_BACK).getCount();
        Integer claim = policyService.apiPolicyCount(command, PolicyStatus.SUCCESS_CLAIM).getCount();
        PolicyCountRepresentation data = new PolicyCountRepresentation(all, back, claim);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }
}
