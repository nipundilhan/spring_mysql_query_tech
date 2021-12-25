package com.sprng.tech.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprng.tech.dao.entity.order.OrderProduct;

@Repository
public interface OrderProductRepository    extends JpaRepository<OrderProduct, Long> {

}
