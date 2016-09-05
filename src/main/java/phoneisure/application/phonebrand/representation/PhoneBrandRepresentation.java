package phoneisure.application.phonebrand.representation;

import phoneisure.core.enums.EnableStatus;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/22.
 */
public class PhoneBrandRepresentation {

    private String id;
    private Integer version;
    private Date createDate;


    private String name;            //名称
    private Integer sort;           //排序
    private EnableStatus status;    //状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
