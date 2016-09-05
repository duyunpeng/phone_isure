package phoneisure.application.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.area.command.ListAreaCommand;
import phoneisure.application.area.representation.AreaRepresentation;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.service.area.IAreaService;

import java.util.List;

/**
 * Created by YJH on 2016/4/17 0017.
 */
@Service("apiAreaAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAreaAppService implements IApiAreaAppService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ApiResponse searchByParent(ListAreaCommand command) {
        List<AreaRepresentation> data = mappingService.mapAsList(areaService.list(command), AreaRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
