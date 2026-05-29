package com.bnk.ledger.service;

import com.bnk.ledger.exception.AccountNotFoundException;
import com.bnk.ledger.exception.InfrastructureException;
import com.bnk.ledger.dao.AccountDao;
import com.bnk.ledger.model.Account;
import com.bnk.ledger.model.Money;

/**
 * Implements the methods of the account service.
 */
public class AccountServiceImpl implements AccountService {

  private AccountDao accountDao;

  @Override
  public void createAccount(String accountRef, Money amount) throws InfrastructureException {
    if (accountDao.accountExists(accountRef)) {
      throw new InfrastructureException("Account already exists: " + accountRef);
    }
    accountDao.createAccount(accountDao.getClientRef(), accountRef, amount);
  }

  @Override
  public Money getAccountBalance(String accountRef) throws AccountNotFoundException {
    Account account = accountDao.getAccount(accountRef);
    if (account.isNullAccount()) {
      throw new AccountNotFoundException(accountRef);
    }
    return account.getBalance();
  }

  public void setAccountDao(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

}
