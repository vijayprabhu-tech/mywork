package com.bnk.ledger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.bnk.ledger.model.Account;
import com.bnk.ledger.model.Money;

import java.util.HashSet;
import java.util.Set;

/**
 * A chart of accounts is a created list of the accounts used to define each class of items for
 * which money or the equivalent is spent or received
*/
public class ChartOfAccounts {

  private final Set<Account> accounts;

  private ChartOfAccounts(Set<Account> accounts) {
    checkNotNull(accounts);
    checkArgument(!accounts.isEmpty());
    this.accounts = accounts;
  }

  public Set<Account> get() {
    return accounts;
  }

  public static class ChartOfAccountsBuilder {

    private Set<Account> accountList = new HashSet<>();

    public ChartOfAccountsBuilder create(String accountRef, String amount, String currency) {
      this.accountList.add(new Account(accountRef, Money.toMoney(amount, currency)));
      return this;
    }

    public ChartOfAccountsBuilder includeExisted(String accountRef) {
      this.accountList.add(new Account(accountRef, Money.NULL_MONEY));
      return this;
    }

    public ChartOfAccounts build() {
      return new ChartOfAccounts(accountList);
    }
  }
}
