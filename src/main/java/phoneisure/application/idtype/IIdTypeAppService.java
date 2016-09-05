package phoneisure.application.idtype;

import phoneisure.application.idtype.command.CreateIdTypeCommand;
import phoneisure.application.idtype.command.EditIdTypeCommand;
import phoneisure.application.idtype.command.ListIdTypeCommand;
import phoneisure.application.idtype.representation.IdTypeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/4/23.
 */
public interface IIdTypeAppService {

    Pagination<IdTypeRepresentation> pagination(ListIdTypeCommand command);

    IdTypeRepresentation searchByID(String id);

    IdTypeRepresentation create(CreateIdTypeCommand command);

    IdTypeRepresentation edit(EditIdTypeCommand command);

    void updateStatus(SharedCommand command);

    List<IdTypeRepresentation> listJSON(ListIdTypeCommand command);
}
