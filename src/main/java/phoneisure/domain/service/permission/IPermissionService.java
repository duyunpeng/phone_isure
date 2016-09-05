package phoneisure.domain.service.permission;


import phoneisure.application.permission.command.CreatePermissionCommand;
import phoneisure.application.permission.command.EditPermissionCommand;
import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.permission.Permission;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IPermissionService {

    Pagination<Permission> pagination(ListPermissionCommand command);

    List<Permission> list(ListPermissionCommand command);

    List<Permission> searchByIDs(List<String> ids);

    Permission searchByID(String id);

    Permission searchByName(String name, String appKey);

    Permission create(CreatePermissionCommand command);

    Permission edit(EditPermissionCommand command);

    void updateStatus(SharedCommand command);
}
