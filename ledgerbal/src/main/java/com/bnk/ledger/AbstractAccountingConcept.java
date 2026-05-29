package com.bnk.ledger;

import static com.bnk.ledger.ConnectionOptions.EMBEDDED_H2_CONNECTION;

import com.bnk.ledger.exception.InfrastructureException;
import com.bnk.ledger.service.AccountService;
import com.bnk.ledger.service.TransferService;


public abstract class AbstractAccountingConcept implements Initializable {

  private TransferService transferService;
  private AccountService accountService;

  public AbstractAccountingConcept(String className) throws InfrastructureException {
    init(className, EMBEDDED_H2_CONNECTION);
  }

  public AbstractAccountingConcept(String className, ConnectionOptions options)
      throws InfrastructureException {
    init(className, options);
  }

  @Override
  public void init(String className, ConnectionOptions options) throws InfrastructureException {
    BankFactory bankFactory;
    try {
      bankFactory = (BankFactory) Class.forName(className).newInstance();
      transferService = bankFactory.getTransferService();
      accountService = bankFactory.getAccountService();
    } catch (Exception e) {
      throw new InfrastructureException(e.getCause());
    }
    bankFactory.setupInitialData(options);
  }

  public TransferService getTransferService() {
    return transferService;
  }

  public AccountService getAccountService() {
    return accountService;
  }
}
