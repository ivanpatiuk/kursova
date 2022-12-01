package lpnu.service.impl;

import lpnu.entity.Accountant;
import lpnu.entity.Worker;
import lpnu.exceptions.InternalException;
import lpnu.repository.AccountantRepository;
import lpnu.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

// Клас для проходження атворизації
@Component
public class Authorization {
    private final WorkerRepository workerRepository;
    private final AccountantRepository accountantRepository;

    @Autowired
    public Authorization(WorkerRepository workerRepository, AccountantRepository accountantRepository) {
        this.workerRepository = workerRepository;
        this.accountantRepository = accountantRepository;
    }

    // Авторизація працівника
    public Worker authorizeWorker(final Long workedId, final String sitePassword) {
        final Worker worker = workerRepository.getWorkerById(workedId);
        if (Objects.nonNull(worker)) {
            if (worker.getSitePassword().equals(sitePassword)) {
                return worker;
            }
            throw new InternalException(400, "Access denied.");
        }
        throw new InternalException(400, "Worker with workedId '" + workedId + "'doesn't exist in DB.");
    }

    // Авторизація бухгалтера
    public Accountant authorizeAccountant(final Long accountId, final String sitePassword) {
        final Accountant accountant = accountantRepository.getAccountantById(accountId);
        if (Objects.nonNull(accountant)) {
            if (accountant.getSitePassword().equals(sitePassword)) {
                return accountant;
            }
            throw new InternalException(400, "Access denied.");
        }
        throw new InternalException(400, "Accountant with accountId '" + accountId + "'doesn't exist in DB.");
    }
}
//************************************************

