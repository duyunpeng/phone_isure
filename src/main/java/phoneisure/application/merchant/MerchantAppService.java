package phoneisure.application.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.merchant.command.ChangeAgentCommand;
import phoneisure.application.merchant.command.CreateAgentCommand;
import phoneisure.application.merchant.command.ListMerchantCommand;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.service.merchant.IMerchantService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("merchantAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class MerchantAppService implements IMerchantAppService {

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<MerchantRepresentation> paginationByAgent(ListMerchantCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Merchant> pagination = merchantService.paginationByAgent(command);
        List<MerchantRepresentation> data = mappingService.mapAsList(pagination.getData(), MerchantRepresentation.class);
        return new Pagination<MerchantRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<MerchantRepresentation> pagination(ListMerchantCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Merchant> pagination = merchantService.pagination(command);
        List<MerchantRepresentation> data = mappingService.mapAsList(pagination.getData(), MerchantRepresentation.class);
        return new Pagination<MerchantRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<MerchantRepresentation> paginationWaitMerchant(ListMerchantCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Merchant> pagination = merchantService.paginationWaitMerchant(command);
        List<MerchantRepresentation> data = mappingService.mapAsList(pagination.getData(), MerchantRepresentation.class);
        return new Pagination<MerchantRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<MerchantRepresentation> paginationAgency(ListMerchantCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Merchant> pagination = merchantService.paginationAgency(command);
        List<MerchantRepresentation> data = mappingService.mapAsList(pagination.getData(), MerchantRepresentation.class);
        return new Pagination<MerchantRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<MerchantRepresentation> paginationWaitAgency(ListMerchantCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Merchant> pagination = merchantService.paginationWaitAgency(command);
        List<MerchantRepresentation> data = mappingService.mapAsList(pagination.getData(), MerchantRepresentation.class);
        return new Pagination<MerchantRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public MerchantRepresentation searchByID(String id) {
        return mappingService.map(merchantService.searchByID(id), MerchantRepresentation.class, false);
    }

    @Override
    public void auth(SharedCommand command) {
        merchantService.auth(command);
    }

    @Override
    public void agency(SharedCommand command) {
        merchantService.agency(command);
    }

    @Override
    public List<MerchantRepresentation> agentList() {
        return mappingService.mapAsList(merchantService.agentList(), MerchantRepresentation.class);
    }

    @Override
    public void changeAgent(ChangeAgentCommand command) {
        merchantService.changeAgent(command);
    }

    @Override
    public MerchantRepresentation createAgent(CreateAgentCommand command) {
        return mappingService.map(merchantService.createAgent(command), MerchantRepresentation.class, false);
    }

    @Override
    public Pagination<MerchantRepresentation> paginationMerchant(ListMerchantCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Merchant> pagination = merchantService.paginationMerchant(command);
        List<MerchantRepresentation> data = mappingService.mapAsList(pagination.getData(), MerchantRepresentation.class);
        return new Pagination<MerchantRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

}
