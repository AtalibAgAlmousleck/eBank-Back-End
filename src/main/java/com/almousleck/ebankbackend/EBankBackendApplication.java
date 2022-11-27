package com.almousleck.ebankbackend;

import com.almousleck.ebankbackend.dtos.BankAccountDTO;
import com.almousleck.ebankbackend.dtos.CurrentBankAccountDTO;
import com.almousleck.ebankbackend.dtos.SavingBankAccountDTO;
import com.almousleck.ebankbackend.entites.BankAccount;
import com.almousleck.ebankbackend.dtos.CustomerDTO;
import com.almousleck.ebankbackend.exceptions.BalanceNotSufficientException;
import com.almousleck.ebankbackend.exceptions.BankAccountNotFoundException;
import com.almousleck.ebankbackend.exceptions.CustomerNotFoundException;
import com.almousleck.ebankbackend.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EBankBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Ata-lib", "Usman", "Benji").forEach(names -> {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(names);
                customerDTO.setEmail(names + "@gmail.com");
                bankAccountService.saveCustomer(customerDTO);
            });
            bankAccountService.listCustomer().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());
                    List<BankAccountDTO> bankAccountList = bankAccountService.bankAccountList();
                    for (BankAccountDTO bankAccount : bankAccountList) {
                        for (int i = 0; i < 10; i++) {
                            String accountId;
                            if (bankAccount instanceof SavingBankAccountDTO) {
                                accountId = (((SavingBankAccountDTO) bankAccount).getId());
                            } else {
                                accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                            }
                            bankAccountService.credit(accountId,
                                    10000 + Math.random() * 120000, "Credit");
                            bankAccountService.debit(accountId,
                                    1000 + Math.random() * 9000, "Debit");
                        }
                    }
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
                    e.printStackTrace();
                }
            });
        };
    }
}

//    @Bean
//    CommandLineRunner run(CustomerRepository customerRepository,
//                          BankAccountRepository bankAccountRepository,
//                          AccountOperationRepository accountOperationRepository) {
//        return args -> {
//            // Save the customer
//            Stream.of("Atalib", "Aisha", "Hassan").forEach(name -> {
//                Customer customer = new Customer();
//                customer.setName(name);
//                customer.setEmail(name + "@gmail.com");
//                customerRepository.save(customer);
//            });
//
//            //
//            customerRepository.findAll().forEach(customer -> {
//                CurrentAccount currentAccount = new CurrentAccount();
//                currentAccount.setId(UUID.randomUUID().toString());
//                currentAccount.setBalance(Math.random() * 90000);
//                currentAccount.setCreatedAt(new Date());
//                currentAccount.setStatus(AccountStatus.CREATED);
//                currentAccount.setCustomer(customer);
//                currentAccount.setOverDraft(90000);
//
//                bankAccountRepository.save(currentAccount);
//
//                SavingAccount savingAccount = new SavingAccount();
//                savingAccount.setId(UUID.randomUUID().toString());
//                savingAccount.setBalance(Math.random() * 90000);
//                savingAccount.setCreatedAt(new Date());
//                savingAccount.setStatus(AccountStatus.CREATED);
//                savingAccount.setCustomer(customer);
//                savingAccount.setInterestRate(5.5);
//
//                bankAccountRepository.save(savingAccount);
//            });
//
//            bankAccountRepository.findAll().forEach(bankAccount -> {
//                for (int i = 0; i < 10; i++) {
//                    AccountOperation accountOperation = new AccountOperation();
//                    accountOperation.setOperationDate(new Date());
//                    accountOperation.setAmount(Math.random() * 12000);
//                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
//                    accountOperation.setBankAccount(bankAccount);
//                    accountOperationRepository.save(accountOperation);
//                }
//            });
//        };
//    }
