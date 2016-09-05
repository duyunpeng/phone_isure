package phoneisure.domain.service.role;

import phoneisure.application.role.command.CreateRoleCommand;
import phoneisure.application.role.command.EditRoleCommand;
import phoneisure.application.role.command.ListRoleCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.role.Role;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IRoleService {

    Pagination<Role> pagination(ListRoleCommand command);

    List<Role> list(ListRoleCommand command);

    Role searchByID(String id);

    Role searchByName(String name, String appKey);

    Role create(CreateRoleCommand command);

    Role edit(EditRoleCommand command);

    void updateStatus(SharedCommand command);

    List<Role> searchByIDs(List<String> ids);
}
