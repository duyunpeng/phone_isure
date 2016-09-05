package phoneisure.domain.model.phonebrand;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class PhoneBrand extends ConcurrencySafeEntity {

    private String name;            //名称
    private Integer sort;           //排序
    private EnableStatus status;    //状态

    public void changeName(String name) {
        this.name = name;
    }

    public void changeSort(Integer sort){this.sort=sort;}

    public void changeStatus(EnableStatus status){this.status=status;}

    private void setName(String name) {
        this.name = name;
    }

    private void setSort(Integer sort) {
        this.sort = sort;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Integer getSort() {
        return sort;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public PhoneBrand() {
        super();
    }

    public PhoneBrand(String name, Integer sort, EnableStatus status) {
        this.name = name;
        this.sort = sort;
        this.status = status;
        this.setCreateDate(new Date());
    }
}
