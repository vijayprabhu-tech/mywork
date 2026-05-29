package com.bnk.ledger.dao;

import java.util.Date;

public interface ClientDao {

  void createClient(String clientRef, Date creationDate);
}
