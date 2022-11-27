package com.almousleck.ebankbackend.web;

import com.almousleck.ebankbackend.dtos.CustomerDTO;
import com.almousleck.ebankbackend.exceptions.CustomerNotFoundException;
import com.almousleck.ebankbackend.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/api/customers")
public class CustomerRestController {
    private final BankAccountService bankAccountService;

    @GetMapping("/all")
    public List<CustomerDTO> customerList() {
        return bankAccountService.listCustomer();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/update/{customerId}")
    public CustomerDTO update(@PathVariable Long customerId,
                              @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }
}
