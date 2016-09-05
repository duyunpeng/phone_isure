package phoneisure.application.appkey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.appkey.command.CreateAppKeyCommand;
import phoneisure.application.appkey.command.EditAppKeyCommand;
import phoneisure.application.appkey.command.ListAppKeyCommand;
import phoneisure.application.appkey.representation.AppKeyRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.service.appkey.IAppKeyService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("appKeyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AppKeyAppService implements IAppKeyAppService {

    @Autowired
    private IAppKeyService appKeyService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public List<AppKeyRepresentation> allList() {
        ListAppKeyCommand command = new ListAppKeyCommand();
        command.setStatus(EnableStatus.ENABLE);
        return mappingService.mapAsList(appKeyService.list(command), AppKeyRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppKeyRepresentation> list(ListAppKeyCommand command) {
        return mappingService.mapAsList(appKeyService.list(command), AppKeyRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<AppKeyRepresentation> pagination(ListAppKeyCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<AppKey> pagination = appKeyService.pagination(command);
        List<AppKeyRepresentation> data = mappingService.mapAsList(pagination.getData(), AppKeyRepresentation.class);
        return new Pagination<AppKeyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<AppKeyRepresentation> paginationJSON(ListAppKeyCommand command) {
        command.verifyPage();
        command.setName(command.getAppKeyName());
        Pagination<AppKey> pagination = appKeyService.pagination(command);
        List<AppKeyRepresentation> data = mappingService.mapAsList(pagination.getData(), AppKeyRepresentation.class);
        return new Pagination<AppKeyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }


    @Override
    @Transactional(readOnly = true)
    public AppKeyRepresentation searchByID(String id) {
        return mappingService.map(appKeyService.searchByID(id), AppKeyRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public AppKeyRepresentation searchByName(String name) {
        return mappingService.map(appKeyService.searchByName(name), AppKeyRepresentation.class, false);
    }

    @Override
    public AppKeyRepresentation create(CreateAppKeyCommand command) {
        return mappingService.map(appKeyService.create(command), AppKeyRepresentation.class, false);
    }

    @Override
    public AppKeyRepresentation edit(EditAppKeyCommand command) {
        return mappingService.map(appKeyService.edit(command), AppKeyRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        appKeyService.updateStatus(command);
    }
}
