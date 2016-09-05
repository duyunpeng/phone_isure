package phoneisure.domain.service.phonebrand;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.phonebrand.command.CreatePhoneBrandCommand;
import phoneisure.application.phonebrand.command.EditPhoneBrandCommand;
import phoneisure.application.phonebrand.command.ListPhoneBrandCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.phonebrand.IPhoneBrandRepository;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LvDi on 2016/4/22.
 */
@Service("phoneBrandService")
public class PhoneBrandService implements IPhoneBrandService {

    @Autowired
    private IPhoneBrandRepository<PhoneBrand, String> phoneBrandRepository;

    @Override
    public Pagination<PhoneBrand> pagination(ListPhoneBrandCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return phoneBrandRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList, null);
    }

    @Override
    public PhoneBrand searchByID(String id) {
        PhoneBrand phoneBrand = phoneBrandRepository.getById(id);
        if (null == phoneBrand) {
            throw new NoFoundException("没有找到ID[" + id + "]的PhoneBrand数据");
        }
        return phoneBrand;
    }

    @Override
    public PhoneBrand searchByName(String name) {
        return phoneBrandRepository.getByName(name);
    }

    @Override
    public PhoneBrand create(CreatePhoneBrandCommand command) {

        if (null != phoneBrandRepository.getByName(command.getName())) {
            throw new ExistException("appVersion[" + command.getName() + "]的PhoneBrand数据已存在");
        }
        PhoneBrand phoneBrand = new PhoneBrand(command.getName(), command.getSort(), command.getStatus());

        phoneBrandRepository.save(phoneBrand);

        return phoneBrand;
    }

    @Override
    public PhoneBrand edit(EditPhoneBrandCommand command) {
        PhoneBrand phoneBrand = this.searchByID(command.getId());
        if (null == phoneBrand) {
            throw new NoFoundException("没有找到ID[" + command.getId() + "]的PhoneBrand手机品牌");
        }
        phoneBrand.changeName(command.getName());
        phoneBrand.changeSort(command.getSort());
        phoneBrand.changeStatus(command.getStatus());
        phoneBrandRepository.update(phoneBrand);
        return phoneBrand;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        PhoneBrand phoneBrand = this.searchByID(command.getId());
        phoneBrand.fainWhenConcurrencyViolation(command.getVersion());
        if (phoneBrand.getStatus() == EnableStatus.DISABLE) {
            phoneBrand.changeStatus(EnableStatus.ENABLE);
        } else {
            phoneBrand.changeStatus(EnableStatus.DISABLE);
        }
        phoneBrandRepository.update(phoneBrand);

    }

    @Override
    public List<PhoneBrand> list(ListPhoneBrandCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("sort"));
        return phoneBrandRepository.list(criterionList, null);
    }
}
