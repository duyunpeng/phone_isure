package phoneisure.application.area;


import phoneisure.application.area.command.CreateAreaCommand;
import phoneisure.application.area.command.EditAreaCommand;
import phoneisure.application.area.command.ListAreaCommand;
import phoneisure.application.area.representation.AreaRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
public interface IAreaAppService {

    Pagination<AreaRepresentation> pagination(ListAreaCommand command);

    AreaRepresentation searchByID(String id);

    AreaRepresentation create(CreateAreaCommand command);

    AreaRepresentation edit(EditAreaCommand command);

    void updateStatus(SharedCommand command);

    List<AreaRepresentation> listJSON(ListAreaCommand command);
}
