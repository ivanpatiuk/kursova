package lpnu.service;

import lpnu.dto.AccountantDTO;
import lpnu.entity.Accountant;
import lpnu.entity.Payout;

// Інтерфейс сервісу бухгалтера
public interface AccountantService {
    AccountantDTO getAccountantById(final Long id);
    AccountantDTO createAccountant(final Accountant accountant);
    Payout executePayoutOnCard(final Long accountantId, final Long workerId, final String sitePassword);
    Payout executePayoutInPaydesk(final Long accountantId, final Long workerId, final String sitePassword);
    Payout closePayout(final Long accountantId, final Long workerId, final String sitePassword);
}
//************************************************
