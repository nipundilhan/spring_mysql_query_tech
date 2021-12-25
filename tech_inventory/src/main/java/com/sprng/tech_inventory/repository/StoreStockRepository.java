package com.sprng.tech_inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprng.tech_inventory.entity.Store;
import com.sprng.tech_inventory.entity.StoreStock;

@Repository
public interface StoreStockRepository    extends JpaRepository<StoreStock, Long> {
	
	List<StoreStock> findAllByStoreAndProductId(Store store,Long productId);

}
