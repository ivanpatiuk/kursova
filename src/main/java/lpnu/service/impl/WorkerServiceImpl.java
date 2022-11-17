package lpnu.service.impl;

import lpnu.dto.WorkerDTO;
import lpnu.entity.Payout;
import lpnu.entity.Worker;
import lpnu.entity.mapper.DTOConvertor;
import lpnu.enums.PayoutState;
import lpnu.enums.WithdrawType;
import lpnu.exceptions.InternalException;
import lpnu.repository.PayoutRepository;
import lpnu.repository.WorkerRepository;
import lpnu.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final DTOConvertor dtoConvertor;
    private final Authorization authorization;
    private final PayoutRepository payoutRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, DTOConvertor dtoConvertor, Authorization authorization, PayoutRepository payoutRepository) {
        this.workerRepository = workerRepository;
        this.dtoConvertor = dtoConvertor;
        this.authorization = authorization;
        this.payoutRepository = payoutRepository;
    }

    @Override
    public WorkerDTO getWorkerById(final Long id) {
        final Worker worker = workerRepository.getWorkerById(id);
        return Objects.isNull(worker) ? null : dtoConvertor.convertToDto(worker, WorkerDTO.class);
    }

    @Override
    public Iterable<WorkerDTO> getWorkersByIds(final List<Long> idList) {
        final List<WorkerDTO> workers = new LinkedList<>();
        idList.forEach(id -> {
            final Worker worker = workerRepository.getWorkerById(id);
            if (Objects.nonNull(worker)) {
                workers.add(dtoConvertor.convertToDto(worker, WorkerDTO.class));
            }
        });
        return workers;
    }

    @Override
    public WorkerDTO createWorker(final Worker worker) {
        return workerRepository.addWorker(worker);
    }

    private Payout orderPayout(final Long workerId, final String sitePassword, final WithdrawType withdrawType) {
        final Worker worker = authorization.authorizeWorker(workerId, sitePassword);
        final Payout payout = new Payout(PayoutState.OPEN, withdrawType, worker.getSalary());
        payoutRepository.addPayout(worker, payout);
        return payout;
    }

    @Override
    public Payout orderPayoutOnCard(final Long workerId, final String sitePassword) {
        return orderPayout(workerId, sitePassword, WithdrawType.CARD);
    }

    @Override
    public Payout orderPayoutInPaydesk(final Long workerId, final String sitePassword) {
        return orderPayout(workerId, sitePassword, WithdrawType.PAYDESK);
    }

    @Override
    public Payout approvePayout(final Long workerId, final String sitePassword) {
        final Worker worker = workerRepository.getWorkerById(workerId);
        final Payout payout = payoutRepository.getPayout(worker);
        payout.setPayoutState(PayoutState.APPROVED);
        payoutRepository.updatePayoutStatus(worker, payout);
        return payout;
    }
}
