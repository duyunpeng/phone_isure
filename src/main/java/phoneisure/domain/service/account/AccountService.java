package phoneisure.domain.service.account;


import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.account.command.*;
import phoneisure.application.auth.command.LoginCommand;
import phoneisure.application.picture.command.CreatePictureCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.commons.Constants;
import phoneisure.core.commons.PasswordHelper;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.shiro.UsernamePasswordAppKeyToken;
import phoneisure.core.upload.IFileUploadService;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.account.IAccountRepository;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.picture.Picture;
import phoneisure.domain.model.role.Role;
import phoneisure.domain.service.appkey.IAppKeyService;
import phoneisure.domain.service.picture.IPictureService;
import phoneisure.domain.service.role.IRoleService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.io.File;
import java.util.*;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("accountService")
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository<Account, String> accountRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAppKeyService appKeyService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Pagination<Account> pagination(ListAccountCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getAppKey())) {
            criterionList.add(Restrictions.eq("appKey.id", command.getAppKey()));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return accountRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<Account> list(ListAccountCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return accountRepository.list(criterionList, orderList);
    }

    @Override
    public Account searchByID(String id) {
        Account account = accountRepository.getById(id);
        if (null == account) {
            throw new NoFoundException("没有找到ID[" + id + "]的Account数据");
        }
        return account;
    }

    @Override
    public Account searchByAccountName(String userName, String appKey) {
        return accountRepository.searchByAccountName(userName, appKey);
    }

    @Override
    public Account searchByAccountName(String userName) {
        return accountRepository.searchByAccountName(userName);
    }

    @Override
    public Account create(CreateAccountCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        List<Role> roleList = roleService.searchByIDs(command.getRoles());
        if (null != this.searchByAccountName(command.getUserName(), command.getAppKey())) {
            throw new ExistException("userName[" + command.getUserName() + "]的Account数据已存在");
        }
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);
        Account account = new Account(command.getUserName(), password, salt, null, null, null, roleList, command.getEmail(),
                appKey, command.getStatus(), null);
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account edit(EditAccountCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeEmail(command.getEmail());
        accountRepository.update(account);
        return account;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        if (account.getStatus() == EnableStatus.DISABLE) {
            account.changeStatus(EnableStatus.ENABLE);
        } else {
            account.changeStatus(EnableStatus.DISABLE);
        }
        accountRepository.update(account);
    }

    @Override
    public void resetPassword(ResetPasswordCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        String password = PasswordHelper.encryptPassword(command.getPassword(), account.getSalt());
        account.changePassword(password);
        accountRepository.update(account);
    }

    @Override
    public void authorized(AuthorizeAccountCommand command) {
        List<Role> roleList = roleService.searchByIDs(command.getRoles());
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeRoles(roleList);
        accountRepository.update(account);
    }

    @Override
    public void updateAppKey(UpdateUserAppKeyCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeAppKey(appKey);
        accountRepository.update(account);
    }

    @Override
    public Account login(LoginCommand command) {
        Account account = this.searchByAccountName(command.getUserName(), Constants.APP_KRY);
        if (null == account) {
            throw new UnknownAccountException();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordAppKeyToken token = new UsernamePasswordAppKeyToken(
                command.getUserName(),
                command.getPassword(),
                Constants.APP_KRY);
        subject.login(token);

        account.changeLastLoginIP(command.getLoginIP());
        account.changeLastLoginPlatform(command.getLoginPlatform());
        account.changeLastLoginDate(new Date());
        accountRepository.update(account);

        return account;
    }

    @Override
    public List<Account> searchByIDs(List<String> ids) {
        List<Account> accountList = new ArrayList<Account>();
        for (String item : ids) {
            accountList.add(this.searchByID(item));
        }
        return accountList;
    }

    @Override
    public List<Account> searchByRoleIDs(List<String> ids) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.in("role.id", ids));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("roles", "role");
        return accountRepository.list(criterionList, null, null, null, alias);
    }

    @Override
    public void updateHeadPic(UpdateHeadPicCommand command) {
        Account account = this.searchByID(command.getId());
        Picture oldHeadPic = account.getHeadPic();
        File imgFile = fileUploadService.moveToImg(command.getHandPic());
        CreatePictureCommand picCommand = new CreatePictureCommand();
        picCommand.setName(imgFile.getName());
        picCommand.setPicPath(fileUploadService.getHttpPath("img") + "/" + imgFile.getName());
        picCommand.setMiniPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMiniImgFile(imgFile.getName()).getName());
        picCommand.setMediumPicPath(fileUploadService.getHttpPath("img") + "/" + fileUploadService.getMediumImgFile(imgFile.getName()).getName());
        picCommand.setSize((double) FileUtils.sizeOf(imgFile) / 1024 / 1024);//单位MB
        Picture newHeadPic = pictureService.create(picCommand);
        if (null != oldHeadPic) {
            fileUploadService.deleteImg(oldHeadPic.getName());
            pictureService.delete(oldHeadPic.getId());
        }
        account.changeHeadPic(newHeadPic);
        accountRepository.update(account);
    }

    @Override
    public void delete(String id) {
        Account account = this.searchByID(id);
        accountRepository.delete(account);
    }

    @Override
    public Account apiUpdateLogin(UpdateLoginCommand command) {
        Account account = this.searchByID(command.getId());

        account.changeLastLoginIP(command.getLoginIP());
        account.changeLastLoginPlatform(command.getLoginPlatform());
        account.changeLastLoginDate(new Date());
        accountRepository.update(account);
        return account;
    }

    @Override
    public void apiResetPassword(ResetPasswordCommand command) {
        Account account = this.searchByAccountName(command.getUserName(), command.getAppKey());
        if (null == account) {
            throw new NoFoundException();
        }
        String password = PasswordHelper.encryptPassword(command.getPassword(), account.getSalt());
        account.changePassword(password);
        accountRepository.update(account);
    }
}
