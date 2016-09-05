package phoneisure.application.role;

import phoneisure.application.role.command.CreateRoleCommand;
import phoneisure.application.role.command.EditRoleCommand;
import phoneisure.application.role.command.ListRoleCommand;
import phoneisure.application.role.representation.RoleRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IRoleAppService {

    Pagination<RoleRepresentation> paginationJSON(ListRoleCommand command);

    Pagination<RoleRepresentation> pagination(ListRoleCommand command);

    List<RoleRepresentation> list(ListRoleCommand command);

    RoleRepresentation searchByID(String id);

    RoleRepresentation searchByName(String name, String appKey);

    RoleRepresentation create(CreateRoleCommand command);

    RoleRepresentation edit(EditRoleCommand command);

    void updateStatus(SharedCommand command);
}
