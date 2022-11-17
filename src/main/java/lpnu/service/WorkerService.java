package lpnu.service;

import lpnu.dto.WorkerDTO;
import lpnu.entity.Payout;
import lpnu.entity.Worker;

import java.util.List;

public interface WorkerService {
    WorkerDTO getWorkerById(final Long workerId);
    Iterable<WorkerDTO> getWorkersByIds(final List<Long> workerIds);
    WorkerDTO createWorker(final Worker worker);
    Payout orderPayoutOnCard(final Long workerId, final String sitePassword);
    Payout orderPayoutInPaydesk(final Long workerId, final String sitePassword);
    Payout approvePayout(final Long workerId, final String sitePassword);
}
