package phoneisure.application.idtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.idtype.command.CreateIdTypeCommand;
import phoneisure.application.idtype.command.EditIdTypeCommand;
import phoneisure.application.idtype.command.ListIdTypeCommand;
import phoneisure.application.idtype.representation.IdTypeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.domain.service.idtype.IIdTypeService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/4/23.
 */
@Service("idTypeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class IdTypeAppService implements IIdTypeAppService{

    @Autowired
    private IIdTypeService idTypeService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public IdTypeRepresentation create(CreateIdTypeCommand command) {
        return mappingService.map(idTypeService.create(command),IdTypeRepresentation.class,false);
    }

    @Override
    public Pagination<IdTypeRepresentation> pagination(ListIdTypeCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<IdType> pagination = idTypeService.pagination(command);
        List<IdTypeRepresentation> data = mappingService.mapAsList(pagination.getData(),IdTypeRepresentation.class);
        return new Pagination<IdTypeRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    public IdTypeRepresentation searchByID(String id) {
        return mappingService.map(idTypeService.searchByID(id),IdTypeRepresentation.class,false);
    }

    @Override
    public IdTypeRepresentation edit(EditIdTypeCommand command) {
        return mappingService.map(idTypeService.edit(command),IdTypeRepresentation.class,false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        idTypeService.updateStatus(command);
    }

    @Override
    public List<IdTypeRepresentation> listJSON(ListIdTypeCommand command) {
        return mappingService.mapAsList(idTypeService.list(command),IdTypeRepresentation.class);
    }

}
