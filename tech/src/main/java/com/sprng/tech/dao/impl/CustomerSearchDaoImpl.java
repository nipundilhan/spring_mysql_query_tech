package com.sprng.tech.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sprng.tech.dao.entity.table.Company;
import com.sprng.tech.dao.entity.table.Customer;
import com.sprng.tech.dao.entity.table.Person;
import com.sprng.tech.dao.entity.table.meta.MstProfession;
import com.sprng.tech.util.DateUtil;

@Component
public class CustomerSearchDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DateUtil dateUtil;
	
	public void searchCustomer() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();

		Root<Customer> root = query.from(Customer.class);
		
		boolean val = true;
		
		if(val) {
			Join<Customer, Company> companyJoin = root.join("company", JoinType.INNER);
		}else {
			
		}
		
		
		
	}
}
