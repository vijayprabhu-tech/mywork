package com.bnk.ledger.dao;

import com.bnk.ledger.model.Account;
import com.bnk.ledger.model.Money;
import com.bnk.ledger.model.TransactionLeg;

public interface AccountDao {

  boolean accountExists(String accountRef);

  void createAccount(String clientRef, String accountRef, Money initialAmount);

  Account getAccount(String accountRef);

  void updateBalance(TransactionLeg leg);

  void setClientRef(String clientRef);

  String getClientRef();
}
