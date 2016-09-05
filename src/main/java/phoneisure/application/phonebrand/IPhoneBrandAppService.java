package phoneisure.application.phonebrand;

import phoneisure.application.phonebrand.command.CreatePhoneBrandCommand;
import phoneisure.application.phonebrand.command.EditPhoneBrandCommand;
import phoneisure.application.phonebrand.command.ListPhoneBrandCommand;
import phoneisure.application.phonebrand.representation.PhoneBrandRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
public interface IPhoneBrandAppService {

    Pagination<PhoneBrandRepresentation> pagination(ListPhoneBrandCommand command);

    PhoneBrandRepresentation searchByID(String id);

    PhoneBrandRepresentation create(CreatePhoneBrandCommand command);

    PhoneBrandRepresentation edit(EditPhoneBrandCommand command);

    void updateStatus(SharedCommand command);

    List<PhoneBrandRepresentation> listJSON(ListPhoneBrandCommand command);



}
