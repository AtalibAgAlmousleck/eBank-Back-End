package com.almousleck.ebankbackend.web;

import com.almousleck.ebankbackend.dtos.AccountOperationDTO;
import com.almousleck.ebankbackend.dtos.BankAccountDTO;
import com.almousleck.ebankbackend.exceptions.BankAccountNotFoundException;
import com.almousleck.ebankbackend.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/accounts")
public class BankAccountRestApi {

    public BankAccountService bankAccountService;

    @GetMapping("/get/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/list")
    public List<BankAccountDTO> accountDTOList() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/operation/{accountId}")
    public List<AccountOperationDTO> getAccountHistory(@PathVariable String accountId) {
        return bankAccountService.accountOperationHistory(accountId);
    }
}
