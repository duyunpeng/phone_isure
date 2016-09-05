package phoneisure.domain.service.idtype;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.idtype.command.CreateIdTypeCommand;
import phoneisure.application.idtype.command.EditIdTypeCommand;
import phoneisure.application.idtype.command.ListIdTypeCommand;
import phoneisure.application.idtype.representation.IdTypeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.domain.model.idtype.IIdTypeRepository;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyp on 16-4-22.
 */
@Service("idTypeService")
public class IdTypeService implements IIdTypeService {

    @Autowired
    private IIdTypeRepository<IdType, String> idTypeRepository;


    @Override
    public Pagination<IdType> pagination(ListIdTypeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();


        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return idTypeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public IdType searchByID(String id) {
        IdType idType = idTypeRepository.getById(id);
        if (null == idType) {
            throw new NoFoundException("没有找到ID[" + id + "]的IdType数据");
        }
        return idType;
    }

    @Override
    public IdType searchByName(String name) {
        return idTypeRepository.searchByName(name);
    }

    @Override
    public IdType create(CreateIdTypeCommand command) {
        if (null != this.searchByName(command.getName())) {
            throw new ExistException("name[" + command.getName() + "]的IdType数据已存在");
        }
        IdType idType = new IdType(command.getName(), command.getStatus());
        idTypeRepository.save(idType);
        return idType;
    }

    @Override
    public IdType edit(EditIdTypeCommand command) {

        IdType idType = this.searchByID(command.getId());
        idType.fainWhenConcurrencyViolation(command.getVersion());
        if (idType.getName().equals(command.getName())) {
            if (null != this.searchByName(command.getName())) {
                throw new ExistException("name[" + command.getName() + "]的IdType数据已存在");
            }
        }

        idType.changeName(command.getName());
        idType.changeStatus(command.getStatus());
        idTypeRepository.update(idType);
        return idType;
    }

    @Override
    public void updateStatus(SharedCommand command) {

        IdType idType = this.searchByID(command.getId());
        idType.fainWhenConcurrencyViolation(command.getVersion());
        if (idType.getStatus() == EnableStatus.DISABLE) {
            idType.changeStatus(EnableStatus.ENABLE);
        } else {
            idType.changeStatus(EnableStatus.DISABLE);
        }
        idTypeRepository.update(idType);

    }

    @Override
    public List<IdType> list(ListIdTypeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return idTypeRepository.list(criterionList, orderList);
    }
}
