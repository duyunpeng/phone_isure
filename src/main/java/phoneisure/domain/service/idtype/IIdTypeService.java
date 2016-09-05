package phoneisure.domain.service.idtype;

import phoneisure.application.idtype.command.CreateIdTypeCommand;
import phoneisure.application.idtype.command.EditIdTypeCommand;
import phoneisure.application.idtype.command.ListIdTypeCommand;
import phoneisure.application.idtype.representation.IdTypeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 16-4-22.
 */
public interface IIdTypeService {
    Pagination<IdType> pagination(ListIdTypeCommand command);

    IdType searchByID(String id);

    IdType searchByName(String name);

    IdType create(CreateIdTypeCommand command);

    IdType edit(EditIdTypeCommand command);

    void updateStatus(SharedCommand command);

    List<IdType> list(ListIdTypeCommand command);
}
