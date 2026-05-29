package com.bnk.ledger.validation;

import com.bnk.ledger.exception.AccountNotFoundException;
import com.bnk.ledger.exception.InsufficientFundsException;
import com.bnk.ledger.exception.UnbalancedLegsException;
import com.bnk.ledger.model.TransactionLeg;
import com.bnk.ledger.model.TransferRequest;

public interface TransferValidator {

  void validateTransferRequest(TransferRequest transferRequest);

  void transferRequestExists(String transactionRef);

  void isTransactionBalanced(Iterable<TransactionLeg> legs) throws UnbalancedLegsException;

  void currenciesMatch(Iterable<TransactionLeg> legs)
      throws TransferValidationException, AccountNotFoundException;

  void validBalance(Iterable<TransactionLeg> legs) throws InsufficientFundsException;

}
