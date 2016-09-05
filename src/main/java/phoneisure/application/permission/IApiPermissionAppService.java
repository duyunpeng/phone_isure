package phoneisure.application.permission;

import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/23.
 */
public interface IApiPermissionAppService {
    ApiResponse apiList(ListPermissionCommand command);
}
