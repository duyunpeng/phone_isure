package phoneisure.application.permission.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.appkey.representation.AppKeyRepresentation;
import phoneisure.application.permission.representation.PermissionRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.permission.Permission;

/**
 * Created by YJH on 2016/3/30 0030.
 */
@Component
public class PermissionRepresentationMapper extends CustomMapper<Permission, PermissionRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Permission permission, PermissionRepresentation representation, MappingContext com) {
        if (null != permission.getAppKey()) {
            AppKeyRepresentation data = mappingService.map(permission.getAppKey(), AppKeyRepresentation.class, false);
            representation.setAppKey(data);
        }
    }

}
