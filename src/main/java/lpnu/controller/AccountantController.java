package lpnu.controller;

import lpnu.dto.AccountantDTO;
import lpnu.entity.Accountant;
import lpnu.entity.Payout;
import lpnu.service.AccountantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/accountant-panel/")
@RestController

public class AccountantController {

    private final AccountantService accountantService;

    @Autowired
    public AccountantController(AccountantService accountantService) {
        this.accountantService = accountantService;
    }

    @GetMapping("get-accountant-by-id/{id}")
    @ResponseBody
    public AccountantDTO getAccountantById(@RequestParam final Long id) {
        return accountantService.getAccountantById(id);
    }

    @GetMapping("get-accountants-by-ids")
    @ResponseBody
    public Iterable<AccountantDTO> getWorkersByIds(@RequestBody final List<Long> idsList) {
        return accountantService.getAccountantsByIds(idsList);
    }

    @PostMapping("create-accountant")
    @ResponseBody
    public AccountantDTO createUser(@Validated @RequestBody final Accountant accountant) {
        return accountantService.createAccountant(accountant);
    }

    @PostMapping("execute-payout-on-card")
    @ResponseBody
    public Payout executePayoutOnCard(
            @Validated @RequestParam("accountant-id") final Long accountantId,
            @Validated @RequestParam("worker-id") final Long workerId,
            @RequestHeader("site-password") final String sitePassword) {
        return accountantService.executePayoutOnCard(accountantId, workerId, sitePassword);
    }

    @PostMapping("execute-payout-in-paydesk")
    @ResponseBody
    public Payout executePayoutInBank(
            @Validated @RequestParam("accountant-id") final Long accountantId,
            @Validated @RequestParam("worker-id") final Long workerId,
            @RequestHeader("site-password") final String sitePassword) {
        return accountantService.executePayoutInPaydesk(accountantId, workerId, sitePassword);
    }

    @PostMapping("close-payout")
    @ResponseBody
    public Payout closePayout(
            @Validated @RequestParam("accountant-id") final Long accountantId,
            @Validated @RequestParam("worker-id") final Long workerId,
            @RequestHeader("site-password") final String sitePassword) {
        return accountantService.closePayout(accountantId, workerId, sitePassword);
    }
}
