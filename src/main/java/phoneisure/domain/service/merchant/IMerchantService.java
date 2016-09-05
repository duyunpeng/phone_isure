package phoneisure.domain.service.merchant;

import phoneisure.application.merchant.command.*;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IMerchantService {
    Pagination<Merchant> pagination(ListMerchantCommand command);

    Pagination<Merchant> paginationAgency(ListMerchantCommand command);

    Pagination<Merchant> paginationWaitAgency(ListMerchantCommand command);

    Merchant searchByID(String id);

    void auth(SharedCommand command);

    void agency(SharedCommand command);

    void create(RegisterMerchantCommand command);

    Merchant searchByUserName(String parentAgent, String appKey);

    Merchant searchByUserName(String parentAgent);

    Merchant searchByArea(String areaId, String appKey);

    Pagination<Merchant> paginationWaitMerchant(ListMerchantCommand command);

    Pagination<Merchant> paginationMerchant(ListMerchantCommand command);

    Pagination<Merchant> paginationByAgent(ListMerchantCommand command);

    List<Merchant> agentList();

    void changeAgent(ChangeAgentCommand command);

    void update(Merchant merchant);

    List<Merchant> searchByParent(String id);

    Merchant createAgent(CreateAgentCommand command);

    //api
    List<Merchant> apiSearchAgent(ListMerchantCommand command);

    void apiUpdatePassword(UpdatePasswordCommand command);

    List<String> getChildrenId(List<String> ids, String id);

    BigDecimal userMoney(List<String> ids);

}
