package phoneisure.application.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.permission.command.CreatePermissionCommand;
import phoneisure.application.permission.command.EditPermissionCommand;
import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.application.permission.representation.PermissionRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.service.permission.IPermissionService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("permissionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PermissionAppService implements IPermissionAppService {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<PermissionRepresentation> pagination(ListPermissionCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Permission> pagination = permissionService.pagination(command);
        List<PermissionRepresentation> data = mappingService.mapAsList(pagination.getData(), PermissionRepresentation.class);
        return new Pagination<PermissionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<PermissionRepresentation> paginationJSON(ListPermissionCommand command) {
        command.verifyPage();
        command.setName(command.getPermissionName());
        Pagination<Permission> pagination = permissionService.pagination(command);
        List<PermissionRepresentation> data = mappingService.mapAsList(pagination.getData(), PermissionRepresentation.class);
        return new Pagination<PermissionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionRepresentation> list(ListPermissionCommand command) {
        return mappingService.mapAsList(permissionService.list(command), PermissionRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionRepresentation searchByID(String id) {
        return mappingService.map(permissionService.searchByID(id), PermissionRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionRepresentation searchByName(String name, String appKey) {
        return mappingService.map(permissionService.searchByName(name, appKey), PermissionRepresentation.class, false);
    }

    @Override
    public PermissionRepresentation create(CreatePermissionCommand command) {
        PermissionRepresentation permission = mappingService.map(permissionService.create(command), PermissionRepresentation.class, false);
        return permission;
    }

    @Override
    public PermissionRepresentation edit(EditPermissionCommand command) {
        PermissionRepresentation permission = mappingService.map(permissionService.edit(command), PermissionRepresentation.class, false);
        return permission;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        permissionService.updateStatus(command);
    }
}
