package lpnu.repository;

import lpnu.entity.Payout;
import lpnu.entity.Worker;
import lpnu.exceptions.InternalException;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Репозиторій виплат
@Repository
public class PayoutRepository {
    private final Map<Worker, Payout> payoutRepository = new HashMap<>();

    public Payout addPayout(final Worker worker, final Payout payout) {
        if (payoutRepository.containsKey(worker)) {
            throw new InternalException(400, "The payout is already created.");
        }
        return payoutRepository.put(worker, payout);
    }

    public Payout getPayout(final Worker worker) {
        final Payout payout = payoutRepository.get(worker);
        if (Objects.nonNull(payout)) {
            return payout;
        }
        throw new InternalException(400, "No payout for worker with id '" + worker.getId() + "' in Payout DB.");
    }

    public Payout updatePayoutStatus(final Worker worker, final Payout payout) {
        payoutRepository.put(worker, payout);
        return payoutRepository.get(worker);
    }

    public Payout removePayout(final Worker worker) {
        return payoutRepository.remove(worker);
    }
}
//************************************************
