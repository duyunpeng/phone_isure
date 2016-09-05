package phoneisure.domain.model.idtype;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class IdType extends ConcurrencySafeEntity {

    private String name;            //证件名称
    private EnableStatus status;    //状态


    public void changeName(String name){this.name=name;}
    public void changeStatus(EnableStatus status){this.status=status;}

    private void setName(String name) {
        this.name = name;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public IdType() {
        super();
    }

    public IdType(String name, EnableStatus status) {
        this.name = name;
        this.status = status;
        this.setCreateDate(new Date());
    }
}
