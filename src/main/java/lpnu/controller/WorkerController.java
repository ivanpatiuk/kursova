package lpnu.controller;

import lpnu.dto.WorkerDTO;
import lpnu.entity.Payout;
import lpnu.entity.Worker;
import lpnu.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// Клас контролер працівника. Відповідає за отримання запиту
// та відправки відповіді
@RequestMapping("/api/v1/worker-panel/")
@RestController
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    // Метод отримання працівника за ідентифікатором
    @GetMapping("get-worker-by-id/{workerId}")
    @ResponseBody
    public WorkerDTO getWorkerById(@PathVariable final Long workerId) {
        return workerService.getWorkerById(workerId);
    }

    // Метод для створення працівника
    @PostMapping("create-worker")
    @ResponseBody
    public WorkerDTO createWorker(@Validated @RequestBody final Worker worker) {
        return workerService.createWorker(worker);
    }

    // Метод для формування виплати на картку
    @PostMapping("order-payout-on-card/{workerId}")
    @ResponseBody
    public Payout orderPayoutOnCard(@PathVariable final Long workerId, @RequestHeader("site-password") final String sitePassword) {
        return workerService.orderPayoutOnCard(workerId, sitePassword);
    }

    // Метод для формування виплати для отримання в касі банку
    @PostMapping("order-payout-in-paydesk/{workerId}")
    @ResponseBody
    public Payout orderPayoutInBank(@PathVariable final Long workerId, @RequestHeader("site-password") final String sitePassword) {
        return workerService.orderPayoutInPaydesk(workerId, sitePassword);
    }

    // Метод для підтвердження отримання виплати
    @PostMapping("approve-payout/{workerId}")
    @ResponseBody
    public Payout approvePayout(@PathVariable final Long workerId, @RequestHeader("site-password") final String sitePassword) {
        return workerService.approvePayout(workerId, sitePassword);
    }
}
//************************************************
