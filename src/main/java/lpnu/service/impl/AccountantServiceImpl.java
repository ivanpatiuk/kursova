package lpnu.service.impl;

import lpnu.dto.AccountantDTO;
import lpnu.entity.Accountant;
import lpnu.entity.Payout;
import lpnu.entity.Worker;
import lpnu.entity.mapper.DTOConvertor;
import lpnu.enums.PayoutState;
import lpnu.enums.WithdrawType;
import lpnu.exceptions.InternalException;
import lpnu.repository.AccountantRepository;
import lpnu.repository.PayoutRepository;
import lpnu.repository.WorkerRepository;
import lpnu.service.AccountantService;
import lpnu.simulation.BankAPI;
import lpnu.simulation.Paydesk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

// Клас який відповідає за сервіс (логіку) бухгалтера
@Service
public class AccountantServiceImpl implements AccountantService {
    private final AccountantRepository accountantRepository;
    private final WorkerRepository workerRepository;
    private final DTOConvertor dtoConvertor;
    private final Authorization authorization;
    private final PayoutRepository payoutRepository;

    @Autowired
    public AccountantServiceImpl(AccountantRepository accountantRepository, WorkerRepository workerRepository, DTOConvertor dtoConvertor, Authorization authorization, PayoutRepository payoutRepository) {
        this.accountantRepository = accountantRepository;
        this.workerRepository = workerRepository;
        this.dtoConvertor = dtoConvertor;
        this.authorization = authorization;
        this.payoutRepository = payoutRepository;
    }

    // Метод для отримання бухгалтера за ідентифікатором
    @Override
    public AccountantDTO getAccountantById(final Long id) {
        final Accountant accountant = accountantRepository.getAccountantById(id);
        return Objects.isNull(accountant) ? null : dtoConvertor.convertToDto(accountant, AccountantDTO.class);
    }

    // Метод для створення бухгалтера
    @Override
    public AccountantDTO createAccountant(final Accountant accountant) {
        return accountantRepository.addAccountant(accountant);
    }

    // Загальний метод для проведення виплати
    private Payout executePayout(final Long accountantId, final Long workerId, final String sitePassword, final WithdrawType withdrawType) {
        authorization.authorizeAccountant(accountantId, sitePassword);
        final Worker worker = workerRepository.getWorkerById(workerId);
        if (Objects.isNull(worker)) {
            throw new InternalException(400, "There is no worker Payout for workerId '" + workerId + "' in DB.");
        }
        Payout payout = payoutRepository.getPayout(worker);
        if (payout.getWithdrawType() == withdrawType) {
            if (withdrawType == WithdrawType.CARD) {
                payout = BankAPI.executePayout(worker, payout);
            } else {
                payout = Paydesk.sendPayoutRequest(worker, payout);
            }
        } else {
            throw new InternalException(400, "There is no Payout for workerId '" + workerId + "' with WithdrawType '" + withdrawType + "' .");
        }
        payout.setPayoutState(PayoutState.PAYIED);
        payoutRepository.updatePayoutStatus(worker, payout);
        return payout;

    }

    // Метод для проведення виплати на картку
    @Override
    public Payout executePayoutOnCard(final Long accountantId, final Long workerId, final String sitePassword) {
        return executePayout(accountantId, workerId, sitePassword, WithdrawType.CARD);
    }

    // Метод для проведення виплати готівкою
    @Override
    public Payout executePayoutInPaydesk(final Long accountantId, final Long workerId, String sitePassword) {
        return executePayout(accountantId, workerId, sitePassword, WithdrawType.PAYDESK);
    }

    // Метод для закриття виплати
    @Override
    public Payout closePayout(final Long accountantId, final Long workerId, final String sitePassword) {
        final Worker worker = workerRepository.getWorkerById(workerId);
        final Payout payout = payoutRepository.getPayout(worker);
        payout.setPayoutState(PayoutState.CLOSED);
        payoutRepository.updatePayoutStatus(worker, payout);
        return payout;
    }
}
//************************************************
