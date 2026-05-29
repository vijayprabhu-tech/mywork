package com.bnk.ledger;


@FunctionalInterface
public interface Initializable {

  void init(String className, ConnectionOptions options) throws Exception;
}
