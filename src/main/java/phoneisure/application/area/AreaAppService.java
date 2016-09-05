package phoneisure.application.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.area.command.CreateAreaCommand;
import phoneisure.application.area.command.EditAreaCommand;
import phoneisure.application.area.command.ListAreaCommand;
import phoneisure.application.area.representation.AreaRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.service.area.IAreaService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
@Service("areaAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AreaAppService implements IAreaAppService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<AreaRepresentation> pagination(ListAreaCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Area> pagination = areaService.pagination(command);
        List<AreaRepresentation> data = mappingService.mapAsList(pagination.getData(), AreaRepresentation.class);
        return new Pagination<AreaRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public AreaRepresentation searchByID(String id) {
        return mappingService.map(areaService.searchByID(id), AreaRepresentation.class, false);
    }

    @Override
    public AreaRepresentation create(CreateAreaCommand command) {
        return mappingService.map(areaService.create(command), AreaRepresentation.class, false);
    }

    @Override
    public AreaRepresentation edit(EditAreaCommand command) {
        return mappingService.map(areaService.edit(command), AreaRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        areaService.updateStatus(command);
    }

    @Override
    public List<AreaRepresentation> listJSON(ListAreaCommand command) {
        return mappingService.mapAsList(areaService.list(command),AreaRepresentation.class);
    }
}
