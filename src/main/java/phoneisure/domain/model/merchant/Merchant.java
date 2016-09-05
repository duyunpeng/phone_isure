package phoneisure.domain.model.merchant;

import phoneisure.core.enums.AuthStatus;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.enums.UserType;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.model.picture.Picture;
import phoneisure.domain.model.role.Role;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/4/21.
 */
public class Merchant extends Account {

    private String merchantName;    //商户名称
    private String contacts;        //商户联系人
    private String contactsPhone;   //商户联系人电话
    private Area area;              //地区
    private String detailedArea;    //详细地区
    private String remarks;         //备注
    private BigDecimal money;       //余额
    private AuthStatus authStatus;  //认证状态
    private String authFailureExplain;  //认证失败原因
    private UserType userType;          //用户类型
    private Merchant agent;          //代理

    public void changeAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    public void changeAgent(Merchant agent) {
        this.agent = agent;
    }

    public void changeMoney(BigDecimal money) {
        this.money = money;
    }

    private void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    private void setContacts(String contacts) {
        this.contacts = contacts;
    }

    private void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    private void setArea(Area area) {
        this.area = area;
    }

    private void setDetailedArea(String detailedArea) {
        this.detailedArea = detailedArea;
    }

    private void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private void setMoney(BigDecimal money) {
        this.money = money;
    }

    private void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    private void setAuthFailureExplain(String authFailureExplain) {
        this.authFailureExplain = authFailureExplain;
    }

    private void setUserType(UserType userType) {
        this.userType = userType;
    }

    private void setAgent(Merchant agent) {
        this.agent = agent;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getContacts() {
        return contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public Area getArea() {
        return area;
    }

    public String getDetailedArea() {
        return detailedArea;
    }

    public String getRemarks() {
        return remarks;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public String getAuthFailureExplain() {
        return authFailureExplain;
    }

    public Merchant getAgent() {
        return agent;
    }

    public UserType getUserType() {
        return userType;
    }

    public Merchant() {
        super();
    }

    public Merchant(String merchantName, String contacts, String contactsPhone, Area area, String detailedArea, String remarks, BigDecimal money, AuthStatus authStatus, String authFailureExplain, UserType userType, Merchant agent) {
        this.merchantName = merchantName;
        this.contacts = contacts;
        this.contactsPhone = contactsPhone;
        this.area = area;
        this.detailedArea = detailedArea;
        this.remarks = remarks;
        this.money = money;
        this.authStatus = authStatus;
        this.authFailureExplain = authFailureExplain;
        this.userType = userType;
        this.agent = agent;
    }

    public Merchant(String userName, String password, String salt, String lastLoginIP, Date lastLoginDate, String lastLoginPlatform, List<Role> roles, String email, AppKey appKey, EnableStatus status, Picture headPic, String merchantName, String contacts, String contactsPhone, Area area, String detailedArea, String remarks, BigDecimal money, AuthStatus authStatus, String authFailureExplain, UserType userType, Merchant agent) {
        super(userName, password, salt, lastLoginIP, lastLoginDate, lastLoginPlatform, roles, email, appKey, status, headPic);
        this.merchantName = merchantName;
        this.contacts = contacts;
        this.contactsPhone = contactsPhone;
        this.area = area;
        this.detailedArea = detailedArea;
        this.remarks = remarks;
        this.money = money;
        this.authStatus = authStatus;
        this.authFailureExplain = authFailureExplain;
        this.userType = userType;
        this.agent = agent;
    }
}
