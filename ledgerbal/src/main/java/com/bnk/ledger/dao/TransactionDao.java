package com.bnk.ledger.dao;

import com.bnk.ledger.model.Transaction;

import java.util.List;
import java.util.Set;


public interface TransactionDao {

  void storeTransaction(Transaction transaction);

  Set<String> getTransactionRefsForAccount(String accountRef);

  List<Transaction> getTransactions(List<String> transactionRefs);

  Transaction getTransactionByRef(String transactionRef);

  void setClientRef(String clientRef);

  String getClientRef();
}
