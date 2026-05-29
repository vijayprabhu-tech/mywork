package com.bnk.ledger;

import com.bnk.ledger.exception.InfrastructureException;
import org.junit.Test;


public class BankFactoryImplTest {

  @Test(expected = InfrastructureException.class)
  public void testConfigureDataSourceWhenDriverIsIllegal() throws Exception {
    BankFactory bankFactory = new BankFactoryImpl();

    ConnectionOptions options = new ConnectionOptions("myDriver")
        .url("myUrl")
        .username("myUsername")
        .password("myPassword")
        .schema("mySchema");

    bankFactory.configureDataSource(options);
  }

}