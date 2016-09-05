package phoneisure.interfaces.moneydetailed.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import phoneisure.application.account.representation.AccountRepresentation;
import phoneisure.application.moneydetailed.IMoneyDetailedAppService;
import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.application.moneydetailed.representation.MoneyDetailedRepresentation;
import phoneisure.core.commons.Constants;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Created by dyp on 16-4-22.
 */
@Controller
@RequestMapping("/money_detailed")
public class MoneyDetailedController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMoneyDetailedAppService moneyDetailedAppService;

    @RequestMapping(value = "pagination")
    public ModelAndView pagination(ListMoneyDetailedCommand command, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            AccountRepresentation account = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
            command.setAgent(account.getId());
        }
        Pagination<MoneyDetailedRepresentation> pagination = moneyDetailedAppService.pagination(command);
        BigDecimal count = moneyDetailedAppService.count(command);
        BigDecimal userMoney = moneyDetailedAppService.userMoney(command);
        return new ModelAndView("/moneyDetailed/list", "pagination", pagination).addObject("command", command).addObject("count", count)
                .addObject("userMoney", userMoney);
    }

}
