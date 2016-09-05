package phoneisure.domain.service.area;



import phoneisure.application.area.command.CreateAreaCommand;
import phoneisure.application.area.command.EditAreaCommand;
import phoneisure.application.area.command.ListAreaCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.area.Area;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
public interface IAreaService {
    Pagination<Area> pagination(ListAreaCommand command);

    Area searchByID(String id);

    Area create(CreateAreaCommand command);

    Area edit(EditAreaCommand command);

    void updateStatus(SharedCommand command);

    List<Area> list(ListAreaCommand command);
}
