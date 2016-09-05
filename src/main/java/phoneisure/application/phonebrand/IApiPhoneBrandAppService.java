package phoneisure.application.phonebrand;

import org.springframework.transaction.annotation.Transactional;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/25.
 */
public interface IApiPhoneBrandAppService {
    ApiResponse list();
}
