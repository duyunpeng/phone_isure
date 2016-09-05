package phoneisure.application.merchant.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.area.representation.AreaRepresentation;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.merchant.Merchant;

/**
 * Created by YJH on 2016/4/22.
 */
@Component
public class MerchantRepresentationMapper extends CustomMapper<Merchant, MerchantRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Merchant merchant, MerchantRepresentation representation, MappingContext context) {
        if (null != merchant.getArea()) {
            AreaRepresentation data = mappingService.map(merchant.getArea(), AreaRepresentation.class, false);
            representation.setArea(data);
        }
        if (null != merchant.getAgent()) {
            MerchantRepresentation data = mappingService.map(merchant.getAgent(), MerchantRepresentation.class, false);
            representation.setAgent(data);
        }
    }

}
