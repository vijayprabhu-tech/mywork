package com.bnk.ledger.model;

import com.bnk.ledger.AbstractAccountingConcept;
import com.bnk.ledger.exception.InfrastructureException;


public class LedgerMock extends AbstractAccountingConcept {

  public LedgerMock() throws InfrastructureException {
    super("some.fake.package.BankFactoryImpl");
  }
}
