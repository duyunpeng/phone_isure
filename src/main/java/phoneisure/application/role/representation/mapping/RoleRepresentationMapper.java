package phoneisure.application.role.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.appkey.representation.AppKeyRepresentation;
import phoneisure.application.permission.representation.PermissionRepresentation;
import phoneisure.application.role.representation.RoleRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.role.Role;

import java.util.List;

/**
 * Created by YJH on 2016/3/30 0030.
 */
@Component
public class RoleRepresentationMapper extends CustomMapper<Role, RoleRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Role role, RoleRepresentation representation, MappingContext context) {
        if (null != role.getAppKey()) {
            AppKeyRepresentation data = mappingService.map(role.getAppKey(), AppKeyRepresentation.class, false);
            representation.setAppKey(data);
        }
        if (null != role.getPermissions()) {
            List<PermissionRepresentation> data = mappingService.mapAsList(role.getPermissions(), PermissionRepresentation.class);
            representation.setPermissions(data);
        }
    }
}
