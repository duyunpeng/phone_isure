package phoneisure.application.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.merchant.command.ListMerchantCommand;
import phoneisure.application.merchant.command.RegisterMerchantCommand;
import phoneisure.application.merchant.command.UpdatePasswordCommand;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.commons.Constants;
import phoneisure.core.enums.UserType;
import phoneisure.core.mapping.IMappingService;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.service.merchant.IMerchantService;

import java.util.List;

/**
 * Created by YJH on 2016/4/23.
 */
@Service("apiMerchantAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiMerchantAppService implements IApiMerchantAppService {

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ApiResponse register(RegisterMerchantCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "userName字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "password字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getMerchantName())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "merchantName字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getContacts())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "contacts字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getContactsPhone())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "contactsPhone字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getArea())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "area字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getDetailedArea())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "detailedArea字段不能为空", null);
            }
//            if (CoreStringUtils.isEmpty(command.getRemarks())) {
//                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "remarks字段不能为空", null);
//            }
            if (CoreStringUtils.isEmpty(command.getAgent())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "agent字段不能为空", null);
            }
            if (CoreStringUtils.isEmpty(command.getAppKey())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "appKey字段不能为空", null);
            }
            merchantService.create(command);
            return new ApiResponse(ApiReturnCode.SUCCESS);
        } else {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT);
        }
    }

    @Override
    public ApiResponse searchAgent(ListMerchantCommand command) {
        command.setAppKey(Constants.APP_KRY);
        List<MerchantRepresentation> data = mappingService.mapAsList(merchantService.apiSearchAgent(command), MerchantRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse updatePassword(UpdatePasswordCommand command) {
        if (CoreStringUtils.isEmpty(command.getMerchant())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "merchant字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getOldPassword())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "oldPassword字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getNewPassword())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "newPassword字段不能为空", null);
        }
        merchantService.apiUpdatePassword(command);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), null);
    }

    @Override
    public ApiResponse searchByID(SharedCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id字段不能为空", null);
        }
        MerchantRepresentation data = mappingService.map(merchantService.searchByID(command.getId()), MerchantRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
