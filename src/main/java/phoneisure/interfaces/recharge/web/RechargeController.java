package phoneisure.interfaces.recharge.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import phoneisure.application.recharge.IRechargeAppService;
import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.application.recharge.representation.RechargeRepresentation;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;

/**
 * Created by dyp on 2016/4/22.
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRechargeAppService rechargeAppService;

    @RequestMapping(value = "pagination")
    public ModelAndView pagination(ListRechargeCommand command){
        Pagination<RechargeRepresentation> pagination = rechargeAppService.pagination(command);
        BigDecimal count = rechargeAppService.count(command);
        return new ModelAndView("/recharge/list","pagination",pagination).addObject("command",command).addObject("count",count);
    }

}
