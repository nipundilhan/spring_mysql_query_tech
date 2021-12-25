package com.sprng.tech.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprng.tech.dao.entity.table.Customer;

@Repository
public interface CustomerRepository     extends JpaRepository<Customer, Long> {

}
