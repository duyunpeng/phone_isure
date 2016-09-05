package phoneisure.domain.service.role;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.role.command.CreateRoleCommand;
import phoneisure.application.role.command.EditRoleCommand;
import phoneisure.application.role.command.ListRoleCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.model.role.IRoleRepository;
import phoneisure.domain.model.role.Role;
import phoneisure.domain.service.appkey.IAppKeyService;
import phoneisure.domain.service.permission.IPermissionService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("roleService")
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository<Role, String> roleRepository;

    @Autowired
    private IAppKeyService appKeyService;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Pagination<Role> pagination(ListRoleCommand command) {
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
        return roleRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<Role> list(ListRoleCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return roleRepository.list(criterionList, orderList);
    }

    @Override
    public Role searchByID(String id) {
        Role role = roleRepository.getById(id);
        if (null == role) {
            throw new NoFoundException("没有找到ID[" + id + "]的Role数据");
        }
        return role;
    }

    @Override
    public Role searchByName(String name, String appKey) {
        return roleRepository.searchByName(name, appKey);
    }

    @Override
    public Role create(CreateRoleCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        if (null != this.searchByName(command.getName(), appKey.getId())) {
            throw new ExistException("name[" + command.getName() + "]的Role数据已存在");
        }
        List<Permission> permissionList = permissionService.searchByIDs(command.getPermissions());
        Role role = new Role(command.getName(),
                command.getDescription(), permissionList, appKey, command.getStatus());
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role edit(EditRoleCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        Role role = this.searchByID(command.getId());
        role.fainWhenConcurrencyViolation(command.getVersion());
        if (!role.getName().equals(command.getName())) {
            if (null != this.searchByName(command.getName(), appKey.getId())) {
                throw new ExistException("name[" + command.getName() + "]的Role数据已存在");
            }
        }
        List<Permission> permissionList = permissionService.searchByIDs(command.getPermissions());
        role.changeName(command.getName());
        role.changeDescription(command.getDescription());
        role.changePermissions(permissionList);
        role.changeAppKey(appKey);
        roleRepository.update(role);
        return role;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Role role = this.searchByID(command.getId());
        role.fainWhenConcurrencyViolation(command.getVersion());
        if (role.getStatus() == EnableStatus.DISABLE) {
            role.changeStatus(EnableStatus.ENABLE);
        } else {
            role.changeStatus(EnableStatus.DISABLE);
        }
        roleRepository.update(role);
    }

    @Override
    public List<Role> searchByIDs(List<String> ids) {
        List<Role> roleList = null;
        if (null != ids && ids.size() > 0) {
            roleList = new ArrayList<Role>();
            for (String item : ids) {
                Role role = this.searchByID(item);
                roleList.add(role);
            }
        }
        return roleList;
    }
}
