package phoneisure.domain.service.phonebrand;

import phoneisure.application.phonebrand.command.CreatePhoneBrandCommand;
import phoneisure.application.phonebrand.command.EditPhoneBrandCommand;
import phoneisure.application.phonebrand.command.ListPhoneBrandCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
public interface IPhoneBrandService {
    Pagination<PhoneBrand> pagination(ListPhoneBrandCommand command);

    PhoneBrand searchByID(String id);

    PhoneBrand searchByName(String name);

    PhoneBrand create(CreatePhoneBrandCommand command);

    PhoneBrand edit(EditPhoneBrandCommand command);

    void updateStatus(SharedCommand command);

    List<PhoneBrand> list(ListPhoneBrandCommand command);
}
