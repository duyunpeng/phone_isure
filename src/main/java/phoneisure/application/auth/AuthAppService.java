package phoneisure.application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.account.IAccountAppService;
import phoneisure.application.account.representation.AccountRepresentation;
import phoneisure.application.auth.command.LoginCommand;
import phoneisure.application.permission.IPermissionAppService;
import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.application.permission.representation.PermissionRepresentation;
import phoneisure.core.commons.Constants;
import phoneisure.core.enums.EnableStatus;

import java.util.List;

/**
 * Created by YJH on 2016/4/5.
 */
@Service("authAppService")
public class AuthAppService implements IAuthAppService {

    @Autowired
    private IAccountAppService accountAppService;

    @Autowired
    private IPermissionAppService permissionAppService;

    @Override
    public AccountRepresentation searchByAccountName(String userName, String appKey) {
        return accountAppService.searchByAccountName(userName, appKey);
    }

    @Override
    public List<PermissionRepresentation> findAllPermission() {
        ListPermissionCommand command = new ListPermissionCommand();
        command.setStatus(EnableStatus.ENABLE);
        command.setAppKey(Constants.APP_KRY);
        return permissionAppService.list(command);
    }

    @Override
    public AccountRepresentation login(LoginCommand command) {
        return accountAppService.login(command);
    }
}
