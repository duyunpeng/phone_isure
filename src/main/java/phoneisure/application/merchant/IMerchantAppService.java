package phoneisure.application.merchant;

import phoneisure.application.merchant.command.ChangeAgentCommand;
import phoneisure.application.merchant.command.CreateAgentCommand;
import phoneisure.application.merchant.command.ListMerchantCommand;
import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;
import sun.corba.SharedSecrets;

import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IMerchantAppService {

    Pagination<MerchantRepresentation> paginationByAgent(ListMerchantCommand command);

    Pagination<MerchantRepresentation> pagination(ListMerchantCommand command);

    Pagination<MerchantRepresentation> paginationWaitMerchant(ListMerchantCommand command);

    Pagination<MerchantRepresentation> paginationAgency(ListMerchantCommand command);

    Pagination<MerchantRepresentation> paginationWaitAgency(ListMerchantCommand command);

    Pagination<MerchantRepresentation> paginationMerchant(ListMerchantCommand command);

    MerchantRepresentation searchByID(String id);

    void auth(SharedCommand command);


    void agency(SharedCommand command);

    List<MerchantRepresentation> agentList();

    void changeAgent(ChangeAgentCommand command);

    MerchantRepresentation createAgent(CreateAgentCommand command);
}
