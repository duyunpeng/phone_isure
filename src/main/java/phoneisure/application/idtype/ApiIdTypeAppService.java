package phoneisure.application.idtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.idtype.command.ListIdTypeCommand;
import phoneisure.application.idtype.representation.IdTypeRepresentation;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.service.idtype.IIdTypeService;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("apiIdTypeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiIdTypeAppService implements IApiIdTypeAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IIdTypeService idTypeService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list() {
        ListIdTypeCommand command = new ListIdTypeCommand();
        command.setStatus(EnableStatus.ENABLE);
        List<IdTypeRepresentation> data = mappingService.mapAsList(idTypeService.list(command), IdTypeRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS,ApiReturnCode.SUCCESS.getName(),data);
    }

}
