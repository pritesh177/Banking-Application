package com.javaguides.Banking.controller;

import com.javaguides.Banking.dto.Accountdto;
import com.javaguides.Banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account REST API
   @PostMapping
    public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto){
        return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
    }

    //get account REST API

    @GetMapping("/{id}")
    public ResponseEntity<Accountdto> getAccountById(@PathVariable Long id){
        Accountdto accountdto= accountService.getAccountById(id);
        return ResponseEntity.ok(accountdto);
    }

    //deposit balance in an account
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Accountdto> deposit(@PathVariable Long id,@RequestBody Map<String,Integer> request){
        double amount=request.get("amount");
        Accountdto accountdto=accountService.deposit(id,amount);
        return ResponseEntity.ok(accountdto);
    }

    // withdraw from account
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Accountdto> withdraw(@PathVariable Long id,@RequestBody Map<String,Integer> request){
        double amount=request.get("amount");
        Accountdto accountdto=accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountdto);
    }

    // List all accounts
    @GetMapping
    public ResponseEntity<List<Accountdto>> getAllAccounts(){
        List<Accountdto> accounts=accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteacc(@PathVariable Long id){
        accountService.deleteacc(id);
        return ResponseEntity.ok("Account "+id+" deleted successfully!");
    }
}
