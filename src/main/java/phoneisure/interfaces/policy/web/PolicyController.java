package phoneisure.interfaces.policy.web;

import org.apache.commons.io.FileUtils;
import org.apache.http.ParseException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Mode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phoneisure.application.account.representation.AccountRepresentation;
import phoneisure.application.policy.IPolicyAppService;
import phoneisure.application.policy.command.HandlePolicyCommand;
import phoneisure.application.policy.command.ListPolicyCommand;
import phoneisure.application.policy.command.UpdatePolicyPicCommand;
import phoneisure.application.policy.command.UpdatePolicyStatusCommand;
import phoneisure.application.policy.representation.PolicyCountRepresentation;
import phoneisure.application.policy.representation.PolicyRepresentation;
import phoneisure.core.exception.ConcurrencyException;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.exception.NoLoginException;
import phoneisure.core.util.CoreDateUtils;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;
import phoneisure.interfaces.shared.web.AlertMessage;
import phoneisure.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Locale;

/**
 * Created by LvDi on 2016/4/22.
 */
@Controller
@RequestMapping("/policy")
public class PolicyController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPolicyAppService policyAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListPolicyCommand command) {
        return new ModelAndView("/policy/list", "pagination", policyAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/charge_back_pagination")
    public ModelAndView chargeBackPagination(ListPolicyCommand command, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            command.setAgent(CoreHttpUtils.getSessionAccount(session).getId());
        }
        return new ModelAndView("/policy/chargeBackList", "pagination", policyAppService.chargeBackPagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/claim_for_compensation_pagination")
    public ModelAndView claimForCompensation(ListPolicyCommand command) {
        return new ModelAndView("/policy/claimForCompensationList", "pagination", policyAppService.claimForCompensationPagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/agent_pagination")
    public ModelAndView agentPagination(ListPolicyCommand command, HttpSession session, Locale locale) {
        AlertMessage alertMessage;
        Pagination<PolicyRepresentation> pagination;
        try {
            AccountRepresentation account = CoreHttpUtils.getSessionAccount(session);
            command.setAgent(account.getId());
            pagination = policyAppService.agentPagination(command);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/policy/agencyList", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        PolicyRepresentation policy;
        Subject subject = SecurityUtils.getSubject();
        String url = request.getHeader("Referer");
        if (url.indexOf("/agent_pagination") != -1) {
            url = "/policy/agent_pagination";
        } else if (url.indexOf("/charge_back_pagination") != -1) {
            url = "/policy/charge_back_pagination";
        } else if (url.indexOf("/claim_for_compensation_pagination") != -1) {
            url = "/policy/claim_for_compensation_pagination";
        } else {
            url = "/policy/pagination";
        }
        try {
            policy = policyAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("policyFee.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/policy/info", "policy", policy).addObject("url", url);
    }

    @RequestMapping(value = "/info_agency/{id}")
    public ModelAndView infoAgency(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        PolicyRepresentation policy;

        try {
            policy = policyAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("policyFee.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/policy/agencyInfo", "policy", policy);
    }

    @RequestMapping(value = "/update_policyStatus")
    public ModelAndView updatePolicyStatus(UpdatePolicyStatusCommand command, HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        String url = request.getHeader("Referer");
        if (url.indexOf("/agent_pagination") != -1) {
            url = "/policy/agent_pagination";
        } else if (url.indexOf("/charge_back_pagination") != -1) {
            url = "/policy/charge_back_pagination";
        } else {
            url = "/policy/pagination";
        }
        try {
            policyAppService.updatePolicyStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.edit.failure.messages", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "/handle_policy/{id}")
    public ModelAndView handlePolicy(@PathVariable String id, HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        PolicyRepresentation policy;
        Subject subject = SecurityUtils.getSubject();
        String url = subject.hasRole("agent") ? "/policy/agent_pagination" : "/policy/pagination";
        try {
            policy = policyAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("policy.id.not.found.message", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/list");
        }
        return new ModelAndView("/policy/handlePolicy", "policy", policy).addObject("url", url);
    }

    @RequestMapping(value = "/handle_policy", method = RequestMethod.POST)
    public ModelAndView handlePolicy(@Valid @ModelAttribute("command") HandlePolicyCommand command, BindingResult bindingResult, HttpSession session,
                                     RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/policy/handlePolicy", "command", command);
        }
        AlertMessage alertMessage;
        Subject subject = SecurityUtils.getSubject();
        String url = subject.hasRole("agent") ? "/policy/agent_pagination" : "/policy/pagination";
        try {
            policyAppService.handlePolicy(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("policy.id.not.found.message", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("policy.data.error.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER, this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", command.getId());
        return new ModelAndView("redirect:/policy/info/{id}");
    }

    @RequestMapping(value = "/success_claim/{id}")
    public ModelAndView success_claim(@PathVariable String id, HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale) {
        Subject subject = SecurityUtils.getSubject();
        String url = request.getHeader("Referer");
        if (url.indexOf("/agent_pagination") != -1) {
            url = "/policy/agent_pagination";
        } else if (url.indexOf("/claim_for_compensation_pagination") != -1) {
            url = "/policy/claim_for_compensation_pagination";
        } else {
            url = "/policy/pagination";
        }
        AlertMessage alertMessage;
        try {
            policyAppService.successClaim(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("policy.id.not.found.message", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("policy.data.error.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER, this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:" + url);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public PolicyCountRepresentation policyCount(HttpSession session) {
        PolicyCountRepresentation policyCount;
        try {
            AccountRepresentation account = CoreHttpUtils.getSessionAccount(session);
            policyCount = policyAppService.policyCount(account.getId());
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return policyCount;
    }

    @RequestMapping(value = "/external_pagination")
    public ModelAndView externalPagination(ListPolicyCommand command) {
        return new ModelAndView("/policy/externalList", "pagination", policyAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/external/{id}")
    public ModelAndView external(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        PolicyRepresentation policy;
        try {
            policy = policyAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("policyFee.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/policy/externalInfo", "policy", policy);
    }

    @RequestMapping(value = "/export_excel")
    public ResponseEntity<byte[]> exportExcel(ListPolicyCommand command, HttpSession session) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("agent")) {
            command.setAgent(CoreHttpUtils.getSessionAccount(session).getId());
        }

        List<PolicyRepresentation> policyList = policyAppService.exportExcel(command);

        String fileName = "";
        if (!CoreStringUtils.isEmpty(command.getBeginDate()) && !CoreStringUtils.isEmpty(command.getEndDate())) {
            fileName = command.getBeginDate() + "到" + command.getEndDate() + "的保单数据";
        } else if (!CoreStringUtils.isEmpty(command.getBeginDate())) {
            fileName = command.getBeginDate() + "之后的保单数据";
        } else if (!CoreStringUtils.isEmpty(command.getEndDate())) {
            fileName = command.getEndDate() + "之前的保单数据";
        } else {
            fileName = "全部的保单数据";
        }
        if (null != command.getPolicyStatus()) {
            fileName += "-" + command.getPolicyStatus().getName();
        }
        fileName = fileName.replaceAll("/", "-");
        fileName = fileName.replaceAll(":", "：");
        File temp = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache");
        headers.setPragma("no-cache");
        headers.setExpires(-1);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
        try {
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();

            //创建行
            HSSFRow rowTitle = sheet.createRow(0);

            //创建列
            HSSFCell cellTitle = rowTitle.createCell(0);
            cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle.setCellValue("保单号");

            HSSFCell cellTitle_1 = rowTitle.createCell(1);
            cellTitle_1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_1.setCellValue("下单时间");

            HSSFCell cellTitle_2 = rowTitle.createCell(2);
            cellTitle_2.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_2.setCellValue("下单商户");

            HSSFCell cellTitle_3 = rowTitle.createCell(3);
            cellTitle_3.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_3.setCellValue("手机型号");

            HSSFCell cellTitle_4 = rowTitle.createCell(4);
            cellTitle_4.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_4.setCellValue("保单费用");

            HSSFCell cellTitle_5 = rowTitle.createCell(5);
            cellTitle_5.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_5.setCellValue("保单金额");

            HSSFCell cellTitle_6 = rowTitle.createCell(6);
            cellTitle_6.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_6.setCellValue("参保人姓名");

            HSSFCell cellTitle_7 = rowTitle.createCell(7);
            cellTitle_7.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_7.setCellValue("参保人手机号码");

            HSSFCell cellTitle_8 = rowTitle.createCell(8);
            cellTitle_8.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_8.setCellValue("证件类型");

            HSSFCell cellTitle_9 = rowTitle.createCell(9);
            cellTitle_9.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_9.setCellValue("证件号码");

            HSSFCell cellTitle_10 = rowTitle.createCell(10);
            cellTitle_10.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_10.setCellValue("参保开始时间");

            HSSFCell cellTitle_11 = rowTitle.createCell(11);
            cellTitle_11.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_11.setCellValue("参保结束时间");

            HSSFCell cellTitle_12 = rowTitle.createCell(12);
            cellTitle_12.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_12.setCellValue("保单状态");

            HSSFCell cellTitle_13 = rowTitle.createCell(13);
            cellTitle_13.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_13.setCellValue("手机IMEI串号");

            HSSFCell cellTitle_14 = rowTitle.createCell(14);
            cellTitle_14.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle_14.setCellValue("上级代理");

            for (int i = 0; i < policyList.size(); i++) {
                PolicyRepresentation policy = policyList.get(i);
                //创建行
                HSSFRow row = sheet.createRow(i + 1);

                //创建列
                HSSFCell cell = row.createCell(0);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(null == policy.getPolicyNo() ? null : policy.getPolicyNo());

                HSSFCell cell_1 = row.createCell(1);
                cell_1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_1.setCellValue(CoreDateUtils.formatDate(policy.getCreateDate(), "yyyy年MM月dd号HH时mm分ss秒"));

                HSSFCell cell_2 = row.createCell(2);
                cell_2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_2.setCellValue(policy.getMerchant().getMerchantName() + "--" + policy.getMerchant().getUserName());

                HSSFCell cell_3 = row.createCell(3);
                cell_3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_3.setCellValue(null == policy.getPhoneModel() ? null : policy.getPhoneModel());

                HSSFCell cell_4 = row.createCell(4);
                cell_4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell_4.setCellValue(null == policy.getPolicyFee() ? 0 : Double.parseDouble(policy.getPolicyFee().toString()));

                HSSFCell cell_5 = row.createCell(5);
                cell_5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell_5.setCellValue(null == policy.getPolicyMoney() ? 0 : Double.parseDouble(policy.getPolicyMoney().toString()));

                HSSFCell cell_6 = row.createCell(6);
                cell_6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_6.setCellValue(null == policy.getInsuredName() ? null : policy.getInsuredName());

                HSSFCell cell_7 = row.createCell(7);
                cell_7.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_7.setCellValue(null == policy.getInsuredPhone() ? null : policy.getInsuredPhone());

                HSSFCell cell_8 = row.createCell(8);
                cell_8.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_8.setCellValue(null == policy.getIdType() ? null : policy.getIdType().getName());

                HSSFCell cell_9 = row.createCell(9);
                cell_9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_9.setCellValue(null == policy.getIdNumber() ? null : policy.getIdNumber());

                HSSFCell cell_10 = row.createCell(10);
                cell_10.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_10.setCellValue(CoreDateUtils.formatDate(policy.getInsuredBeginDate(), "yyyy年MM月dd号HH时mm分ss秒"));

                HSSFCell cell_11 = row.createCell(11);
                cell_11.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_11.setCellValue(CoreDateUtils.formatDate(policy.getInsuredEndDate(), "yyyy年MM月dd号HH时mm分ss秒"));

                HSSFCell cell_12 = row.createCell(12);
                cell_12.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_12.setCellValue(null == policy.getPolicyStatus() ? null : policy.getPolicyStatus().getName());

                HSSFCell cell_13 = row.createCell(13);
                cell_13.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_13.setCellValue(null == policy.getIMEI() ? null : policy.getIMEI());

                HSSFCell cell_14 = row.createCell(14);
                cell_14.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell_14.setCellValue(null == policy.getMerchant().getAgent() ? null : policy.getMerchant().getAgent().getUserName() + "--" + policy.getMerchant().getAgent().getMerchantName());
            }

            String path = "/temp/excel/";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            temp = new File(path + fileName + ".xls");
            FileOutputStream fout = new FileOutputStream(temp);
            workbook.write(fout);
            fout.close();
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(temp), headers, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>("导出失败".getBytes(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>("导出失败".getBytes(), headers, HttpStatus.OK);
        } finally {
            if (null != temp && temp.exists()) {
                temp.delete();
            } else {
                return new ResponseEntity<byte[]>("导出失败".getBytes(), headers, HttpStatus.OK);
            }
        }
    }

    @RequestMapping(value = "/return_policy")
    public ModelAndView adminReturnPolicy(UpdatePolicyStatusCommand command, RedirectAttributes redirectAttributes,
                                          Locale locale) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("admin")) {
            return new ModelAndView("redirect:/denied");
        }

        AlertMessage alertMessage;
        try {
            policyAppService.adminReturnPolicy(command);
        } catch (NoFoundException e) {

        } catch (ConcurrencyException e) {

        } catch (Exception e) {

        }
        alertMessage = new AlertMessage(AlertMessage.MessageType.SUCCESS,
                this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/policy/pagination");
    }

    @RequestMapping(value = "/update_pic/{id}")
    public ModelAndView updatePic(@PathVariable String id, UpdatePolicyPicCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        PolicyRepresentation policy;
        try {
            policy = policyAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("policy.id.not.found.message", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/policy/updatePic", "policy", policy).addObject("command", command);
    }

    @RequestMapping(value = "/update_pic", method = RequestMethod.POST)
    public ModelAndView updatePic(@Valid @ModelAttribute("command") UpdatePolicyPicCommand command, BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("policy.insuredAfterPicture.NotNull.message", null, locale)));
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/policy/update_pic/{id}");
        }

        try {
            policyAppService.updatePic(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("policy.id.not.found.message", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改【" + command.getId() + "】理赔照片成功!");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", new Object[]{command.getId()}, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/policy/pagination");
    }
}
