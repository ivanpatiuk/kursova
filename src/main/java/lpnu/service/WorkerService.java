package lpnu.service;

import lpnu.dto.WorkerDTO;
import lpnu.entity.Payout;
import lpnu.entity.Worker;

// Інтерфейс сервісу працівника
public interface WorkerService {
    WorkerDTO getWorkerById(final Long workerId);
    WorkerDTO createWorker(final Worker worker);
    Payout orderPayoutOnCard(final Long workerId, final String sitePassword);
    Payout orderPayoutInPaydesk(final Long workerId, final String sitePassword);
    Payout approvePayout(final Long workerId, final String sitePassword);
}
//************************************************
