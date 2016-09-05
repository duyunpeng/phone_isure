package phoneisure.application.idtype.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import phoneisure.application.idtype.representation.IdTypeRepresentation;
import phoneisure.domain.model.idtype.IdType;

/**
 * Created by dyp on 2016/4/23.
 */
@Component
public class IdTypeRepresentationMapper extends CustomMapper<IdType,IdTypeRepresentation>{
}
