package lpnu.service;

import lpnu.dto.AccountantDTO;
import lpnu.entity.Accountant;
import lpnu.entity.Payout;

import java.util.List;

public interface AccountantService {
    AccountantDTO getAccountantById(final Long id);
    Iterable<AccountantDTO> getAccountantsByIds(final List<Long> idsList);
    AccountantDTO createAccountant(final Accountant accountant);
    Payout executePayoutOnCard(final Long accountantId, final Long workerId, final String sitePassword);
    Payout executePayoutInPaydesk(final Long accountantId, final Long workerId, final String sitePassword);
    Payout closePayout(final Long accountantId, final Long workerId, final String sitePassword);
}
