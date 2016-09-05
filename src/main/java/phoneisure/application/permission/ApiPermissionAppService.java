package phoneisure.application.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.application.permission.representation.PermissionRepresentation;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.mapping.IMappingService;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.service.permission.IPermissionService;

import java.util.List;

/**
 * Created by YJH on 2016/4/23.
 */
@Service("apiPermissionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiPermissionAppService implements IApiPermissionAppService {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ApiResponse apiList(ListPermissionCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getAppKey())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "appKey字段不能为空", null);
            }
            List<PermissionRepresentation> data = mappingService.mapAsList(permissionService.list(command), PermissionRepresentation.class);
            return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
        } else {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT);
        }
    }

}
