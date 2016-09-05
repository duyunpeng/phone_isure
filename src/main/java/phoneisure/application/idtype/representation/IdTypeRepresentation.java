package phoneisure.application.idtype.representation;

import phoneisure.core.enums.EnableStatus;

import java.util.Date;

/**
 * Created by dyp on 2016/4/23.
 */
public class IdTypeRepresentation {
    private String id;
    private Integer version;
    private Date createDate;

    private String name;            //证件名称
    private EnableStatus status;    //状态

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
