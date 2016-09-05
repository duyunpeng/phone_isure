package phoneisure.domain.service.appkey;

import phoneisure.application.appkey.command.CreateAppKeyCommand;
import phoneisure.application.appkey.command.EditAppKeyCommand;
import phoneisure.application.appkey.command.ListAppKeyCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAppKeyService {

    List<AppKey> list(ListAppKeyCommand command);

    Pagination<AppKey> pagination(ListAppKeyCommand command);

    AppKey searchByName(String name);

    AppKey create(CreateAppKeyCommand command);

    AppKey searchByID(String id);

    AppKey edit(EditAppKeyCommand command);

    void updateStatus(SharedCommand command);
}
