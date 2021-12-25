package com.sprng.tech_inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprng.tech_inventory.entity.StoreStockHistory;

@Repository
public interface StoreStockHistoryRepository     extends JpaRepository<StoreStockHistory, Long> {


}
