package phoneisure.domain.service.account;

import phoneisure.application.account.command.*;
import phoneisure.application.auth.command.LoginCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.account.Account;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAccountService {

    Pagination<Account> pagination(ListAccountCommand command);

    List<Account> list(ListAccountCommand command);

    Account searchByID(String id);

    Account searchByAccountName(String userName, String appKey);

    Account searchByAccountName(String userName);

    Account create(CreateAccountCommand command);

    Account edit(EditAccountCommand command);

    void updateStatus(SharedCommand command);

    void resetPassword(ResetPasswordCommand command);

    void authorized(AuthorizeAccountCommand command);

    void updateAppKey(UpdateUserAppKeyCommand command);

    Account login(LoginCommand command);

    List<Account> searchByIDs(List<String> ids);

    List<Account> searchByRoleIDs(List<String> ids);

    void updateHeadPic(UpdateHeadPicCommand command);

    void delete(String id);

    //api
    Account apiUpdateLogin(UpdateLoginCommand command);

    void apiResetPassword(ResetPasswordCommand command);


}
