package phoneisure.application.appkey;



import phoneisure.application.appkey.command.CreateAppKeyCommand;
import phoneisure.application.appkey.command.EditAppKeyCommand;
import phoneisure.application.appkey.command.ListAppKeyCommand;
import phoneisure.application.appkey.representation.AppKeyRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAppKeyAppService {

    List<AppKeyRepresentation> allList();

    List<AppKeyRepresentation> list(ListAppKeyCommand command);

    Pagination<AppKeyRepresentation> pagination(ListAppKeyCommand command);

    Pagination<AppKeyRepresentation> paginationJSON(ListAppKeyCommand command);

    AppKeyRepresentation searchByID(String id);

    AppKeyRepresentation searchByName(String name);

    AppKeyRepresentation create(CreateAppKeyCommand command);

    AppKeyRepresentation edit(EditAppKeyCommand command);

    void updateStatus(SharedCommand command);

}
