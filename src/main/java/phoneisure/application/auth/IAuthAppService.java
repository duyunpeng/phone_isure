package phoneisure.application.auth;

import phoneisure.application.account.representation.AccountRepresentation;
import phoneisure.application.auth.command.LoginCommand;
import phoneisure.application.permission.representation.PermissionRepresentation;

import java.util.List;

/**
 * Created by YJH on 2016/4/5.
 */
public interface IAuthAppService {
    AccountRepresentation searchByAccountName(String userName, String appKey);

    List<PermissionRepresentation> findAllPermission();

    AccountRepresentation login(LoginCommand command);
}
