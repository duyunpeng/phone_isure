package phoneisure.application.phonebrand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.phonebrand.command.CreatePhoneBrandCommand;
import phoneisure.application.phonebrand.command.EditPhoneBrandCommand;
import phoneisure.application.phonebrand.command.ListPhoneBrandCommand;
import phoneisure.application.phonebrand.representation.PhoneBrandRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.domain.service.phonebrand.IPhoneBrandService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
@Service("phoneBrandAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PhoneBrandAppService implements IPhoneBrandAppService{

    @Autowired
    private IPhoneBrandService phoneBrandService;

    @Autowired
    private IMappingService mappingService;
    @Override
    public Pagination<PhoneBrandRepresentation> pagination(ListPhoneBrandCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<PhoneBrand> pagination=phoneBrandService.pagination(command);
        List<PhoneBrandRepresentation> data=mappingService.mapAsList(pagination.getData(),PhoneBrandRepresentation.class);
        return new Pagination<PhoneBrandRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    public PhoneBrandRepresentation searchByID(String id) {
        return mappingService.map(phoneBrandService.searchByID(id),PhoneBrandRepresentation.class,false);
    }

    @Override
    public PhoneBrandRepresentation create(CreatePhoneBrandCommand command) {
        return mappingService.map(phoneBrandService.create(command),PhoneBrandRepresentation.class,false);
    }

    @Override
    public PhoneBrandRepresentation edit(EditPhoneBrandCommand command) {
        return mappingService.map(phoneBrandService.edit(command),PhoneBrandRepresentation.class,false);
    }

    @Override
    public void updateStatus(SharedCommand command) {

        phoneBrandService.updateStatus(command);

    }

    @Override
    public List<PhoneBrandRepresentation> listJSON(ListPhoneBrandCommand command) {
        return mappingService.mapAsList(phoneBrandService.list(command),PhoneBrandRepresentation.class);
    }
}
