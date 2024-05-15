package com.javaguides.Banking.service;

import com.javaguides.Banking.dto.Accountdto;

import java.util.List;


public interface AccountService {
    Accountdto createAccount(Accountdto accountdto);

    Accountdto getAccountById(Long id);

    Accountdto deposit(Long id,Double amount);

    Accountdto withdraw(Long id,Double amount);

    List<Accountdto> getAllAccounts();

    void deleteacc(Long id);
}
