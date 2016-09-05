package phoneisure.domain.service.permission;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.permission.command.CreatePermissionCommand;
import phoneisure.application.permission.command.EditPermissionCommand;
import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.permission.IPermissionRepository;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.service.appkey.IAppKeyService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("permissionService")
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository<Permission, String> permissionRepository;

    @Autowired
    private IAppKeyService appKeyService;

    @Override
    public Pagination<Permission> pagination(ListPermissionCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (null != command.getIds() && command.getIds().size() > 0) {
            criterionList.add(Restrictions.in("id", command.getIds()));
        }
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getAppKey())) {
            criterionList.add(Restrictions.eq("appKey.id", command.getAppKey()));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return permissionRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<Permission> list(ListPermissionCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> alias = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getAppKey())) {
            criterionList.add(Restrictions.eq("appKey.name", command.getAppKey()));
            alias.put("appKey", "appKey");
        }
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return permissionRepository.list(criterionList, orderList, null, null, alias);
    }

    @Override
    public List<Permission> searchByIDs(List<String> ids) {
        List<Permission> permissionList = null;
        if (null != ids && ids.size() > 0) {
            permissionList = new ArrayList<Permission>();
            for (String item : ids) {
                Permission permission = this.searchByID(item);
                permissionList.add(permission);
            }
        }
        return permissionList;
    }

    @Override
    public Permission searchByID(String id) {
        Permission permission = permissionRepository.getById(id);
        if (null == permission) {
            throw new NoFoundException("没有找到ID[" + id + "]的Permission数据");
        }
        return permission;
    }

    @Override
    public Permission searchByName(String name, String appKey) {
        return permissionRepository.searchByName(name, appKey);
    }

    @Override
    public Permission create(CreatePermissionCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        if (null != this.searchByName(command.getName(), appKey.getId())) {
            throw new ExistException("权限名[" + command.getName() + "]已存在");
        }
        Permission permission = new Permission(command.getName(), command.getDescription(),
                command.getValue(), appKey, command.getStatus());
        permissionRepository.save(permission);
        return permission;
    }

    @Override
    public Permission edit(EditPermissionCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        Permission permission = this.searchByID(command.getId());
        permission.fainWhenConcurrencyViolation(command.getVersion());
        if (!permission.getName().equals(command.getName())) {
            if (null != this.searchByName(command.getName(), appKey.getId())) {
                throw new ExistException("权限名[" + command.getName() + "]已存在");
            }
        }
        permission.changeName(command.getName());
        permission.changeDescription(command.getDescription());
        permission.changeValue(command.getValue());
        permission.changeAppKey(appKey);
        permissionRepository.update(permission);
        return permission;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Permission permission = this.searchByID(command.getId());
        permission.fainWhenConcurrencyViolation(command.getVersion());
        if (permission.getStatus() == EnableStatus.DISABLE) {
            permission.changeStatus(EnableStatus.ENABLE);
        } else {
            permission.changeStatus(EnableStatus.DISABLE);
        }
        permissionRepository.update(permission);
    }
}
