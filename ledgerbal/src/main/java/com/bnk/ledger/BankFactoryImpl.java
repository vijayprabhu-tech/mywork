package com.bnk.ledger;

import com.bnk.ledger.dao.AccountDao;
import com.bnk.ledger.dao.ClientDao;
import com.bnk.ledger.dao.TransactionDao;
import com.bnk.ledger.exception.InfrastructureException;
import com.bnk.ledger.service.AccountService;
import com.bnk.ledger.service.TransferService;
import com.bnk.ledger.util.BankContextUtil;

import java.util.Date;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class BankFactoryImpl implements BankFactory {

  @Override
  public AccountService getAccountService() {
    return BankContextUtil.getBean("accountService");
  }

  @Override
  public TransferService getTransferService() {
    return BankContextUtil.getBean("transferService");
  }

  @Override
  public void configureDataSource(ConnectionOptions options) {
    try {
      DriverManagerDataSource dataSource = BankContextUtil.getBean("dataSource");
      dataSource.setDriverClassName(options.driverClassName());
      dataSource.setUrl(options.url());
      dataSource.setUsername(options.username());
      dataSource.setPassword(options.password());

      ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
      databasePopulator.setContinueOnError(true);
      databasePopulator.addScript(new ClassPathResource("db/" + options.schema()));

      DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    } catch (Exception e) {
      throw new InfrastructureException(e);
    }
  }

  @Override
  public void setupInitialData(ConnectionOptions options) {
    configureDataSource(options);

    String clientRef = "Client_" + System.currentTimeMillis();

    ClientDao clientDao = BankContextUtil.getBean("clientDao");
    clientDao.createClient(clientRef, new Date());

    AccountDao accountDao = BankContextUtil.getBean("accountDao");
    accountDao.setClientRef(clientRef);

    TransactionDao transactionDao = BankContextUtil.getBean("transactionDao");
    transactionDao.setClientRef(clientRef);
  }

}