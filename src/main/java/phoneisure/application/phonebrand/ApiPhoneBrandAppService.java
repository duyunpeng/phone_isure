package phoneisure.application.phonebrand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.phonebrand.command.ListPhoneBrandCommand;
import phoneisure.application.phonebrand.representation.PhoneBrandRepresentation;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.domain.service.phonebrand.IPhoneBrandService;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("apiPhoneBrandAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiPhoneBrandAppService implements IApiPhoneBrandAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IPhoneBrandService phoneBrandService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list() {
        ListPhoneBrandCommand command = new ListPhoneBrandCommand();
        command.setStatus(EnableStatus.ENABLE);
        List<PhoneBrandRepresentation> data = mappingService.mapAsList(phoneBrandService.list(command), PhoneBrandRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
