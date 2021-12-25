package com.sprng.tech.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sprng.comn_comman.core.resource.response.sucess.PagingResponseDto;
import com.sprng.tech.dao.entity.table.Address;
import com.sprng.tech.dao.entity.table.ContactInfo;
import com.sprng.tech.dao.entity.table.Person;
import com.sprng.tech.dao.entity.table.PersonAddress;
import com.sprng.tech.dao.entity.table.PersonContact;
import com.sprng.tech.dao.entity.table.meta.MstArea;
import com.sprng.tech.dao.entity.table.meta.MstCivilStatus;
import com.sprng.tech.dao.entity.table.meta.MstProfession;
import com.sprng.tech.resource.request.PersonSearchRequest;
import com.sprng.tech.resource.response.success.PersonFinalResponse;
import com.sprng.tech.resource.response.success.PersonResponse;
import com.sprng.tech.util.DateUtil;
import com.sprng.tech.util.PaginationUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class PersonSearchDaoImpl {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private PaginationUtil paginationUtil;


	
	public List<PersonResponse> personSearch1(PersonSearchRequest request) {
		
		
		
		
		Date startDateFormatted = null;
		Date endDateFormatted = null;

		if (request.getFromDate() != null) {
			startDateFormatted = dateUtil.convertStringToStartDateWithTime(request.getFromDate());
		}
		if (request.getToDate() != null) {
			endDateFormatted = dateUtil.convertStringToEndDateWithTime(request.getToDate());
		}
		
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();

		Root<Person> root = query.from(Person.class);
		
		Path<String> name = root.get("firstName");
		
		Join<Person, MstProfession> mstProfessionJoin = root.join("mstProfession", JoinType.INNER);
		Join<Person, MstCivilStatus> mstCivilStatusJoin = root.join("mstCivilStatus", JoinType.LEFT);
		
		Join<Person, PersonContact> personContactJoin = root.join("personContacts", JoinType.LEFT);
		Join<PersonContact, ContactInfo> personCntctContactInfoJoin = personContactJoin.join("contactId",JoinType.INNER);
		
		

		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(criteriaBuilder.and(criteriaBuilder.equal(personContactJoin.get("isMainContact"), Boolean.TRUE)));
		
		
		if (request.getAreaId() != null) {
			
			Join<Person, PersonAddress> personAddressJoin = root.join("personAddressList", JoinType.LEFT);
			Join<PersonAddress, Address> personAdrsAddressJoin = personAddressJoin.join("address",JoinType.INNER);
			Join<Address, MstArea> addressMstAreaJoin = personAdrsAddressJoin.join("mstArea",JoinType.INNER);
			
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.equal(addressMstAreaJoin.get("id"), request.getAreaId())));
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.equal(personAddressJoin.get("isMainAddress"), Boolean.TRUE)));
		}	
		if (request.getName() != null) {
			
			Predicate checkWithFirstName = criteriaBuilder.like(root.get("firstName"), "%"+request.getName().trim()+"%");
			Predicate checkWithLastName =criteriaBuilder.like(root.get("lastName"), "%"+request.getName().trim()+"%");
			

			predicates.add(criteriaBuilder.or(checkWithFirstName,checkWithLastName));	
		}
		if (request.getContactValue() != null) {
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.equal(personCntctContactInfoJoin.get("value"), request.getContactValue())));	
		}
		if ((request.getProfeesionIdList() != null) && (!request.getProfeesionIdList().isEmpty())) {

			predicates.add(criteriaBuilder.and(
					criteriaBuilder.in(mstProfessionJoin.get("id")).value(request.getProfeesionIdList())));
		}
		if (request.getCivilStatusCode() != null) {
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.equal(mstCivilStatusJoin.get("code"), request.getCivilStatusCode())));
		}		
		if (request.getFromDate() != null && request.getToDate() != null) {
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.between(root.get("dob"), startDateFormatted, endDateFormatted)));

		} else if ((request.getFromDate() != null)) {
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dob"), startDateFormatted)));
		} else if ((request.getToDate() != null)) {
			predicates.add(
					criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dob"), endDateFormatted)));
		}
		
		
		
		query.orderBy(criteriaBuilder.desc(root.get("id")));

		//area realated details not added
		query.multiselect(name,mstProfessionJoin, mstCivilStatusJoin, personContactJoin, personCntctContactInfoJoin)
				.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		List<Tuple> tuples = entityManager.createQuery(query).getResultList();

		List<PersonResponse> personList =  new ArrayList<>();
		for (Tuple tuple : tuples) {
			
			String civilStatus = tuple.get(mstCivilStatusJoin) != null ? tuple.get(mstCivilStatusJoin).getName() : " ____ ";
			String profession = tuple.get(mstProfessionJoin) != null ? tuple.get(mstProfessionJoin).getName() :  " ____ ";
			String contact = tuple.get(personCntctContactInfoJoin) != null ? tuple.get(personCntctContactInfoJoin).getValue() :  " ____ ";
			
			PersonResponse resp = new PersonResponse();
			resp.setFName(tuple.get(name));
			resp.setContactValue(contact);
			resp.setProfesion(profession);
			resp.setCivilStatus(civilStatus);
			
			
			personList.add(resp);

		}
		
		return personList;
		
		
		
	}

	
	public PersonFinalResponse personSearchPagination1(PersonSearchRequest request, Pageable pageable) {
			
		Date startDateFormatted = null;
		Date endDateFormatted = null;

		if (request.getFromDate() != null) {
			startDateFormatted = dateUtil.convertStringToStartDateWithTime(request.getFromDate());
		}
		if (request.getToDate() != null) {
			endDateFormatted = dateUtil.convertStringToEndDateWithTime(request.getToDate());
		}
		
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
		Root<Person> root = query.from(Person.class);	
		
		
		

        
        


		
		Path<String> name = root.get("firstName");
		
		Join<Person, MstProfession> mstProfessionJoin = root.join("mstProfession", JoinType.INNER);
		Join<Person, MstCivilStatus> mstCivilStatusJoin = root.join("mstCivilStatus", JoinType.LEFT);
		
		Join<Person, PersonContact> personContactJoin = root.join("personContacts", JoinType.LEFT);
		Join<PersonContact, ContactInfo> personCntctContactInfoJoin = personContactJoin.join("contactId",JoinType.INNER);
		
		

		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(criteriaBuilder.and(criteriaBuilder.equal(personContactJoin.get("isMainContact"), Boolean.TRUE)));
		
		
		if (request.getAreaId() != null) {
			
			Join<Person, PersonAddress> personAddressJoin = root.join("personAddressList", JoinType.LEFT);
			Join<PersonAddress, Address> personAdrsAddressJoin = personAddressJoin.join("address",JoinType.INNER);
			Join<Address, MstArea> addressMstAreaJoin = personAdrsAddressJoin.join("mstArea",JoinType.INNER);
			
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.equal(addressMstAreaJoin.get("id"), request.getAreaId())));
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.equal(personAddressJoin.get("isMainAddress"), Boolean.TRUE)));
		}	
		if (request.getName() != null) {
			
			Predicate checkWithFirstName = criteriaBuilder.like(root.get("firstName"), "%"+request.getName().trim()+"%");
			Predicate checkWithLastName =criteriaBuilder.like(root.get("lastName"), "%"+request.getName().trim()+"%");
			

			predicates.add(criteriaBuilder.or(checkWithFirstName,checkWithLastName));	
		}
		if (request.getContactValue() != null) {
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.equal(personCntctContactInfoJoin.get("value"), request.getContactValue())));	
		}
//		if (request.getProfeesionId() != null) {
//			predicates.add(criteriaBuilder.and(
//					criteriaBuilder.equal(mstProfessionJoin.get("id"), request.getProfeesionId())));
//		}
		if (request.getCivilStatusCode() != null) {
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.equal(mstCivilStatusJoin.get("code"), request.getCivilStatusCode())));
		}		
		if (request.getFromDate() != null && request.getToDate() != null) {
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.between(root.get("dob"), startDateFormatted, endDateFormatted)));

		} else if ((request.getFromDate() != null)) {
			predicates.add(criteriaBuilder
					.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dob"), startDateFormatted)));
		} else if ((request.getToDate() != null)) {
			predicates.add(
					criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dob"), endDateFormatted)));
		}
		
		
		
		query.orderBy(criteriaBuilder.desc(root.get("id")));

		//area realated details not added
		query.multiselect(name,mstProfessionJoin, mstCivilStatusJoin, personContactJoin, personCntctContactInfoJoin)
				.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		int totalSize = entityManager.createQuery(query).getResultList().size();
		
		
		
       query.distinct(true);
		List<Tuple> tuples = entityManager.createQuery(query).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();

		List<PersonResponse> personList =  new ArrayList<>();
		for (Tuple tuple : tuples) {
			
			String civilStatus = tuple.get(mstCivilStatusJoin) != null ? tuple.get(mstCivilStatusJoin).getName() : " ____ ";
			String profession = tuple.get(mstProfessionJoin) != null ? tuple.get(mstProfessionJoin).getName() :  " ____ ";
			String contact = tuple.get(personCntctContactInfoJoin) != null ? tuple.get(personCntctContactInfoJoin).getValue() :  " ____ ";
			
			PersonResponse resp = new PersonResponse();
			resp.setFName(tuple.get(name));
			resp.setContactValue(contact);
			resp.setProfesion(profession);
			resp.setCivilStatus(civilStatus);
			
			
			personList.add(resp);
		}
		
		
		PagingResponseDto pagingResponseDto =  new PagingResponseDto();
		pagingResponseDto.setPageNumber((int) pageable.getOffset());
		pagingResponseDto.setPageSize(pageable.getPageSize());
		pagingResponseDto.setTotalRecords(totalSize);
		
		
		PersonFinalResponse finalResponse = new PersonFinalResponse();
		finalResponse.setPersonList(personList);
		finalResponse.setPagingResponseDto(pagingResponseDto);
		
		return finalResponse;
		
		
	}
	

	
	
	
	
}
