package com.javaguides.Banking.service.impl;

import com.javaguides.Banking.dto.Accountdto;
import com.javaguides.Banking.entity.Account;
import com.javaguides.Banking.mapper.AccountMapper;
import com.javaguides.Banking.repository.AccountRepository;
import com.javaguides.Banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceimpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceimpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Accountdto createAccount(Accountdto accountdto) {
        Account account= AccountMapper.mapToAccount(accountdto);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public Accountdto getAccountById(Long id) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not Exist !!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public Accountdto deposit(Long id, Double amount) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not Exist !!"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedaccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedaccount);
    }

    @Override
    public Accountdto withdraw(Long id, Double amount) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not Exist !!"));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient Funds");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedaccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedaccount);
    }

    @Override
    public List<Accountdto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteacc(Long id) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not Exist !!"));

        accountRepository.deleteById(id);
    }
}
