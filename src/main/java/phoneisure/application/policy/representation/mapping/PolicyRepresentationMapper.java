package phoneisure.application.policy.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.policy.representation.PolicyRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.policy.Policy;

/**
 * Created by LvDi on 2016/4/22.
 */
@Component
public class PolicyRepresentationMapper extends CustomMapper<Policy,PolicyRepresentation> {

    @Autowired
    private IMappingService mappingService;
    public void mapAtoB(Policy policy, PolicyRepresentation representation, MappingContext context) {

        if(null!=policy.getMerchant()){
            MerchantRepresentation data=mappingService.map(policy.getMerchant(),MerchantRepresentation.class,false);
            representation.setMerchant(data);
        }

    }

}
