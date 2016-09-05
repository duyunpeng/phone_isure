package phoneisure.application.moneydetailed.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.moneydetailed.representation.MoneyDetailedRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.moneydetailed.MoneyDetailed;

/**
 * Created by dyp on 16-4-22.
 */
@Component
public class MoneyDetailedRepresentationMapper extends CustomMapper<MoneyDetailed,MoneyDetailedRepresentation>{

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(MoneyDetailed moneyDetailed, MoneyDetailedRepresentation moneyDetailedRepresentation, MappingContext context){
        if(null != moneyDetailed.getMerchant()){
            MerchantRepresentation data = mappingService.map(moneyDetailed.getMerchant(),MerchantRepresentation.class,false);
            moneyDetailedRepresentation.setMerchant(data);
        }
    }
}
