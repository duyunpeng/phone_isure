package phoneisure.application.appkey.representation.mapping;


import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import phoneisure.application.appkey.representation.AppKeyRepresentation;
import phoneisure.domain.model.appkey.AppKey;

/**
 * Created by YJH on 2016/3/30 0030.
 */
@Component
public class AppKeyRepresentationMapper extends CustomMapper<AppKey,AppKeyRepresentation> {
}
