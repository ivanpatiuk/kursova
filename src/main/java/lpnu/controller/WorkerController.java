package lpnu.controller;

import lpnu.dto.WorkerDTO;
import lpnu.entity.Payout;
import lpnu.entity.Worker;
import lpnu.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/worker-panel/")
@RestController

public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("get-worker-by-id/{workerId}")
    @ResponseBody
    public WorkerDTO getWorkerById(@PathVariable final Long workerId) {
        return workerService.getWorkerById(workerId);
    }

    @GetMapping("get-workers-by-ids")
    @ResponseBody
    public Iterable<WorkerDTO> getWorkersByIds(@RequestBody final List<Long> workerIds) {
        return workerService.getWorkersByIds(workerIds);
    }

    @PostMapping("create-worker")
    @ResponseBody
    public WorkerDTO createUser(@Validated @RequestBody final Worker worker) {
        return workerService.createWorker(worker);
    }

    @PostMapping("order-payout-on-card/{workerId}")
    @ResponseBody
    public Payout orderPayoutOnCard(@PathVariable final Long workerId, @RequestHeader("site-password") final String sitePassword) {
        return workerService.orderPayoutOnCard(workerId, sitePassword);
    }

    @PostMapping("order-payout-in-paydesk/{workerId}")
    @ResponseBody
    public Payout orderPayoutInBank(@PathVariable final Long workerId, @RequestHeader("site-password") final String sitePassword) {
        return workerService.orderPayoutInPaydesk(workerId, sitePassword);
    }

    @PostMapping("approve-payout/{workerId}")
    @ResponseBody
    public Payout approvePayout(@PathVariable final Long workerId, @RequestHeader("site-password") final String sitePassword) {
        return workerService.approvePayout(workerId, sitePassword);
    }
}
