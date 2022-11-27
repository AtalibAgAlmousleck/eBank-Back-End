package com.almousleck.ebankbackend.dtos;

import com.almousleck.ebankbackend.entites.BankAccount;
import com.almousleck.ebankbackend.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {

    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
