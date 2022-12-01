package lpnu.controller;

import lpnu.dto.AccountantDTO;
import lpnu.entity.Accountant;
import lpnu.entity.Payout;
import lpnu.service.AccountantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Клас контролер бухгалтера. Відповідає за отримання запиту
// та відправки відповіді
@RequestMapping("/api/v1/accountant-panel/")
@RestController
public class AccountantController {

    private final AccountantService accountantService;

    @Autowired
    public AccountantController(AccountantService accountantService) {
        this.accountantService = accountantService;
    }

    // Метод отримання бухгалтера за ідентифікатором
    @GetMapping("get-accountant-by-id/{id}")
    @ResponseBody
    public AccountantDTO getAccountantById(@RequestParam final Long id) {
        return accountantService.getAccountantById(id);
    }

    // Метод для створення бухгалтера
    @PostMapping("create-accountant")
    @ResponseBody
    public AccountantDTO createAccountant(@Validated @RequestBody final Accountant accountant) {
        return accountantService.createAccountant(accountant);
    }

    // Метод для проведення виплати на карту
    @PostMapping("execute-payout-on-card")
    @ResponseBody
    public Payout executePayoutOnCard(
            @Validated @RequestParam("accountant-id") final Long accountantId,
            @Validated @RequestParam("worker-id") final Long workerId,
            @RequestHeader("site-password") final String sitePassword) {
        return accountantService.executePayoutOnCard(accountantId, workerId, sitePassword);
    }

    // Метод для проведення виплату в касі банку
    @PostMapping("execute-payout-in-paydesk")
    @ResponseBody
    public Payout executePayoutInBank(
            @Validated @RequestParam("accountant-id") final Long accountantId,
            @Validated @RequestParam("worker-id") final Long workerId,
            @RequestHeader("site-password") final String sitePassword) {
        return accountantService.executePayoutInPaydesk(accountantId, workerId, sitePassword);
    }

    // Метод для закриття виплати
    @PostMapping("close-payout")
    @ResponseBody
    public Payout closePayout(
            @Validated @RequestParam("accountant-id") final Long accountantId,
            @Validated @RequestParam("worker-id") final Long workerId,
            @RequestHeader("site-password") final String sitePassword) {
        return accountantService.closePayout(accountantId, workerId, sitePassword);
    }
}
//************************************************