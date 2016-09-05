package phoneisure.application.policyfee.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.phonebrand.representation.PhoneBrandRepresentation;
import phoneisure.application.policyfee.representation.PolicyFeeRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.policyfee.PolicyFee;

/**
 * Created by Administrator on 2016/4/23.
 */
@Component
public class PolicyFeeRepresentationMapper extends CustomMapper<PolicyFee, PolicyFeeRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(PolicyFee policyFee, PolicyFeeRepresentation representation, MappingContext context) {
        if (null != policyFee.getPhoneBrand()) {
            PhoneBrandRepresentation data = mappingService.map(policyFee.getPhoneBrand(), PhoneBrandRepresentation.class, false);
            representation.setPhoneBrand(data);
        }
    }
}
