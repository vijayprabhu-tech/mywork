package com.bnk.ecomproj.repo;

import com.bnk.ecomproj.model.AmountCurrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmountCurrentRepo extends JpaRepository<AmountCurrent, Integer> {

    @Query(value = "SELECT * FROM amount_current WHERE event_id =?1", nativeQuery = true)
    List<AmountCurrent> findByEventId(String eventId);

    @Query(value = "SELECT * FROM amount_current WHERE account_id =?1", nativeQuery = true)
    List<AmountCurrent> findByAccountId(String id);
}
