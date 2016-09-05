package phoneisure.application.recharge.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.recharge.representation.RechargeRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.recharge.Recharge;

/**
 * Created by dyp on 2016/4/22.
 */
@Component
public class RechargeRepresentationMapper extends CustomMapper<Recharge,RechargeRepresentation>{

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Recharge recharge, RechargeRepresentation rechargeRepresentation, MappingContext context){
        if(null != recharge.getMerchant()){
            MerchantRepresentation data = mappingService.map(recharge.getMerchant(),MerchantRepresentation.class,false);
            rechargeRepresentation.setMerchant(data);
        }
    }
}
