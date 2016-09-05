package phoneisure.interfaces.merchant.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phoneisure.application.account.representation.AccountRepresentation;
import phoneisure.application.merchant.IMerchantAppService;
import phoneisure.application.merchant.command.ChangeAgentCommand;
import phoneisure.application.merchant.command.CreateAgentCommand;
import phoneisure.application.merchant.command.ListMerchantCommand;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.commons.Constants;
import phoneisure.core.exception.*;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;
import phoneisure.interfaces.shared.web.AlertMessage;
import phoneisure.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/4/22.
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMerchantAppService merchantAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListMerchantCommand command) {
        return new ModelAndView("/merchant/list", "pagination", merchantAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/agency_pagination")
    public ModelAndView paginationAgency(ListMerchantCommand command, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            AccountRepresentation account = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
            command.setAgent(account.getId());
        }
        return new ModelAndView("merchant/agency_list", "pagination", merchantAppService.paginationAgency(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/wait_agency_pagination")
    public ModelAndView waitAgencyPagination(ListMerchantCommand command, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            AccountRepresentation account = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
            command.setAgent(account.getId());
        }
        return new ModelAndView("/merchant/waitAgency_list", "pagination", merchantAppService.paginationWaitAgency(command));
    }

    @RequestMapping(value = "/info_wait_agency/{id}")
    public ModelAndView infoWaitAgency(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MerchantRepresentation merchant;
        try {
            merchant = merchantAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/agencyWaitInfo");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/waitAgency_info", "merchant", merchant);
    }

    @RequestMapping(value = "/merchant_wait_pagination")
    public ModelAndView pagination1(ListMerchantCommand command, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            command.setAgent(CoreHttpUtils.getSessionAccount(session).getId());
        }
        return new ModelAndView("/merchant/waitMerchant", "pagination", merchantAppService.paginationWaitMerchant(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/merchant_pagination")
    public ModelAndView pagination2(ListMerchantCommand command, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            AccountRepresentation account = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
            command.setAgent(account.getUserName());
        }
        return new ModelAndView("/merchant/merchant", "pagination", merchantAppService.paginationMerchant(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MerchantRepresentation merchant;
        try {
            merchant = merchantAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/info", "merchant", merchant);
    }

    @RequestMapping(value = "/info_wait_merchant/{id}")
    public ModelAndView infoWaitMerchant(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MerchantRepresentation merchant;
        try {
            merchant = merchantAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/merchant_wait_pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/inFoWaitMerchant", "merchant", merchant);
    }

    @RequestMapping(value = "/info_merchant/{id}")
    public ModelAndView infoMerchant(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MerchantRepresentation merchant;
        try {
            merchant = merchantAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/merchant_pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/inFoMerchant", "merchant", merchant);
    }

    @RequestMapping(value = "/info_agent_merchant/{id}")
    public ModelAndView info_agent_merchant(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MerchantRepresentation merchant;
        try {
            merchant = merchantAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/merchant_agent_pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/inFo_agent_merchant", "merchant", merchant);
    }

    @RequestMapping(value = "/info_agency/{id}")
    public ModelAndView infoAgency(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MerchantRepresentation merchant;
        try {
            merchant = merchantAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/agency_info", "merchant", merchant);
    }

    @RequestMapping(value = "/auth_agency")
    public ModelAndView authAgency(SharedCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            merchantAppService.auth(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/wait_agency_pagination");
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/merchant/wait_agency_pagination");
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/merchant/wait_agency_pagination");
    }

    @RequestMapping(value = "/agency")
    public ModelAndView agency(SharedCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            merchantAppService.agency(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/agency_pagination");
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/merchant/agency_pagination");
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/merchant/agency_pagination");
    }

    @RequestMapping(value = "/updateMerchant")
    public ModelAndView updateMerchant(SharedCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            merchantAppService.auth(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/merchant_wait_pagination");
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/merchant/merchant_wait_pagination");
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/merchant/merchant_wait_pagination");
    }

    @RequestMapping(value = "/merchant_agent_pagination")
    public ModelAndView paginationByAgent(ListMerchantCommand command, HttpSession session, Locale locale) {
        AlertMessage alertMessage;
        Pagination<MerchantRepresentation> pagination;
        try {
            AccountRepresentation account = CoreHttpUtils.getSessionAccount(session);
            command.setAgent(account.getId());
            pagination = merchantAppService.paginationByAgent(command);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/merchant/merchantByAgent", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/agent_list")
    @ResponseBody
    public List<MerchantRepresentation> agentList() {
        return merchantAppService.agentList();
    }

    @RequestMapping(value = "/change_agent", method = RequestMethod.POST)
    public ModelAndView changeAgent(ChangeAgentCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        if (null == command || CoreStringUtils.isEmpty(command.getAgent()) || CoreStringUtils.isEmpty(command.getMerchant())) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.edit.failure.messages", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/merchant_pagination");
        }
        try {
            merchantAppService.changeAgent(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.edit.failure.messages", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/merchant/merchant_pagination");
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/merchant/merchant_pagination");
    }

    @RequestMapping(value = "/create_agent", method = RequestMethod.GET)
    public ModelAndView createAgent(@ModelAttribute("command") CreateAgentCommand command) {
        return new ModelAndView("/merchant/create_agent", "command", command);
    }

    @RequestMapping(value = "/create_agent", method = RequestMethod.POST)
    public ModelAndView createAgent(@ModelAttribute("command") CreateAgentCommand command, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, HttpSession session, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/merchant/create_agent", "command", command);
        }

        AccountRepresentation user = CoreHttpUtils.getSessionAccount(session);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;

        try {
            command.setAgent(user.getId());
            merchantAppService.createAgent(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage((AlertMessage.MessageType.WARNING),
                    this.getMessage("default.data.no.found.message", null, locale));
            return new ModelAndView("/merchant/create_agent", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("account.userName.Exist.messages", new Object[]{command.getUserName()}, locale));
            return new ModelAndView("/merchant/create_agent", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NotCreateException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("merchant.not.create.agent.message", new Object[]{command.getUserName()}, locale));
            return new ModelAndView("/merchant/create_agent", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("[" + user.getUserName() + "]创建下级代理[" + command.getUserName() + "]信息成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/merchant/agency_pagination");
    }
}
