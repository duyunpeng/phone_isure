package phoneisure.application.area;


import phoneisure.application.area.command.ListAreaCommand;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/17 0017.
 */
public interface IApiAreaAppService {
    ApiResponse searchByParent(ListAreaCommand command);
}
