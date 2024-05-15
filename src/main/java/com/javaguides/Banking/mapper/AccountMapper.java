package com.javaguides.Banking.mapper;

import com.javaguides.Banking.dto.Accountdto;
import com.javaguides.Banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(Accountdto accountdto){
      Account account=new Account(
              accountdto.getId(),
              accountdto.getAccountHolderName(),
              accountdto.getBalance()
      );
      return account;
    };

    public  static  Accountdto mapToAccountDto(Account account){
        Accountdto accountdto=new Accountdto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountdto;
    };
}
