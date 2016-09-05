package phoneisure.application.policyfee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.application.policyfee.representation.PolicyFeeRepresentation;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.service.policyfee.IPolicyFeeService;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("apiPolicyFeeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiPolicyFeeAppService implements IApiPolicyFeeAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IPolicyFeeService policyFeeService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list(ListPolicyFeeCommand command) {
        command.setStatus(EnableStatus.ENABLE);
        List<PolicyFeeRepresentation> data = mappingService.mapAsList(policyFeeService.list(command), PolicyFeeRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
