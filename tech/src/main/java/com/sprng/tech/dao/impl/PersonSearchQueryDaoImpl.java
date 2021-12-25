package com.sprng.tech.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.sprng.comn_comman.core.resource.response.sucess.PagingResponseDto;
import com.sprng.comn_comman.util.DateFormatter;
import com.sprng.tech.dao.wrapper.PersonDetailRespDto;
import com.sprng.tech.resource.request.PersonSearchRequest;
import com.sprng.tech.resource.response.success.PersonFinalResponse;
import com.sprng.tech.util.DateUtil;
import com.sprng.tech.util.PaginationUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class PersonSearchQueryDaoImpl {

	

	@PersistenceContext
	EntityManager manager;

	@Autowired
	private PaginationUtil paginationUtil;
	
	private String stringNativeQueryComman;
	
	@Autowired
	private DateUtil dateUtil;

	@SuppressWarnings("unchecked")
	public List<PersonDetailRespDto> search(PersonSearchRequest request) {

		StringBuilder nativeQuery = new StringBuilder();
		
 
		nativeQuery.append(" SELECT DISTINCT \r\n" + 
				" p.id AS id, " +
				" p.first_name AS firstName, \r\n" + 
				" p.mobile AS mobile, \r\n" + 
				" p.nic AS nic ");

		nativeQuery.append(" FROM person p " );

		nativeQuery.append(" JOIN mst_civil_status mcs ON mcs.id = p.mst_civil_status_id ");
		nativeQuery.append(" JOIN mst_profession mp ON mp.id = p.mst_prfession_id ");   
		nativeQuery.append(" JOIN mst_profession_area mpa ON mpa.id = mp.mst_prfession_id "); 
		nativeQuery.append(" LEFT JOIN (person_contact pc JOIN contact_info ci ON ci.id =pc.contact_id ) ON pc.person_id = p.id "); 
		nativeQuery.append(" LEFT JOIN (person_address pa JOIN address a ON a.id =pa.address_id JOIN mst_area ma ON ma.id = a.area_id ) ON pa.person_id = p.id "); 
		
		List<String> listConditions = new ArrayList<>();
		Map<String, Object> paramMap = new HashMap<>();

		
		Date startDateFormatted = null;
		Date endDateFormatted = null;

		if (request.getFromDate() != null) {
			startDateFormatted = dateUtil.convertStringToStartDateWithTime(request.getFromDate());
		}
		if (request.getToDate() != null) {
			endDateFormatted = dateUtil.convertStringToEndDateWithTime(request.getToDate());
		}
	
		if ((request.getProfeesionIdList() != null) &&  (!request.getProfeesionIdList().isEmpty())) {
			listConditions.add(" p.mst_prfession_id  IN :prfsnList  ");
			paramMap.put("prfsnList", request.getProfeesionIdList());
		}
		if(request.getContactValue() != null) {
			listConditions.add(" ci.value =:contactVal  ");
			paramMap.put("contactVal", request.getContactValue());
		}
		if(request.getName() != null) {
			//prevent from sql injection // need to test more
			if(request.getName().length() < 20) {
				listConditions.add(" ( p.first_name LIKE '%"+request.getName() +"%'  OR p.last_name LIKE '%"+request.getName() +"%'  )  ");
			}
//          not working			
//			listConditions.add(" ( p.first_name LIKE '%:nm%'  OR p.last_name LIKE '%:nm%' )  ");
//			paramMap.put("nm", request.getName());
			
		}
		if(request.getBmi()!= null) {
			listConditions.add(" p.bmi >:bmi  ");
			paramMap.put("bmi", request.getBmi());
		}
		if(request.getAreaId() != null) {
			listConditions.add(" a.area_id =:arId  ");
			paramMap.put("arId", request.getAreaId());
		}
		if(request.getCivilStatusCode() != null) {
			listConditions.add(" mcs.code =:cvlStsCd  ");
			paramMap.put("cvlStsCd", request.getCivilStatusCode());
		}
		
		if (request.getFromDate() != null) {
			listConditions.add(" Date_Format(p.dob,'%Y-%m-%d') >= :from ");
			paramMap.put("from", DateFormatter.convertDateToStringWithTimeZone(startDateFormatted));
		}
		if (request.getToDate() != null) {
			listConditions.add(" Date_Format(p.dob,'%Y-%m-%d') <= :to");
			paramMap.put("to", DateFormatter.convertDateToStringWithTimeZone(endDateFormatted));
		}



		if (!listConditions.isEmpty()) {
			StringBuilder conditions = new StringBuilder();

			for (int i = 0; i < listConditions.size(); i++) {
				String arrayElement = listConditions.get(i);
				if (i != 0) {
					conditions.append(" AND ");
					conditions.append(arrayElement);
				} else {
					conditions.append(" ");
					conditions.append(arrayElement);
				}
			}
			nativeQuery.append(" WHERE ");
			nativeQuery.append(conditions.toString());
		} 

		nativeQuery.append(" ORDER BY p.id DESC  ");
		Query query = this.manager.createNativeQuery(nativeQuery.toString(),"PersonDetailMapping");

		paramMap.forEach((key, value) -> {
			query.setParameter(key, value);
		});



		return query.getResultList();
	}
	
	
	
	
	
	
	public  List<PersonDetailRespDto>  sqlQuery1(PersonSearchRequest request) {
		

		
		StringBuilder nativeQuerySelect = new StringBuilder();
		StringBuilder nativeQueryComman = new StringBuilder();

		
		nativeQuerySelect.append(" SELECT\r\n" + 
							"p.id AS id,\r\n" + 
							"p.first_name AS firstName,\r\n" + 
							"p.mobile AS mobile,\r\n" + 
							"p.nic AS nic ");
		


		nativeQueryComman.append(" FROM person p  "); 
		nativeQueryComman.append(" JOIN mst_civil_status mcs ON mcs.id = p.mst_civil_status_id  "); 
		nativeQueryComman.append(" JOIN mst_profession mp ON mp.id = p.mst_prfession_id  "); 
		

		List<String> listConditions = new ArrayList<>();
		Map<String, Object> paramMap = new HashMap<>();
		
		
//		if(request != null) {
//			listConditions.add("  (emp.epf_no =:epf_no OR emp_intrm.epf_no =:epf_no )  ");
//			paramMap.put("epf_no", request.getAreaId());
//		}
		
		
		if (!listConditions.isEmpty()) {
			StringBuilder conditions = new StringBuilder();

			for (int i = 0; i < listConditions.size(); i++) {
				String arrayElement = listConditions.get(i);
				if (i != 0) {
					conditions.append(" AND ");
					conditions.append(arrayElement);
				} else {
					conditions.append(" ");
					conditions.append(arrayElement);
				}
			}
			nativeQueryComman.append(" WHERE ");
			nativeQueryComman.append(conditions.toString());
		} 
		
		
		
		stringNativeQueryComman = nativeQueryComman.toString();		
		nativeQuerySelect.append(stringNativeQueryComman);
		
		
		StringBuilder nativeQueryCount = new StringBuilder();
		nativeQueryCount.append(" SELECT COUNT(*) ");		
		nativeQueryCount.append(stringNativeQueryComman);
		

		
		Query dataQuery = this.manager.createNativeQuery(nativeQuerySelect.toString(),"PersonDetailMapping");
		Query countQuery = this.manager.createNativeQuery(nativeQueryCount.toString());
		
		paramMap.forEach((key, value) -> {
			dataQuery.setParameter(key, value);
			countQuery.setParameter(key, value);
		});


		List<PersonDetailRespDto> list = null;
		PagingResponseDto pagingResponseDto =  new PagingResponseDto();
		try {
			PageImpl<PersonDetailRespDto> pageResult = paginationUtil.getPageResult(dataQuery, countQuery, 0, 2);
			list = pageResult.getContent();
			

			pagingResponseDto.setPageNumber((int) pageResult.getNumber());
			pagingResponseDto.setPageSize(pageResult.getSize());
			pagingResponseDto.setTotalRecords(pageResult.getTotalElements());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//query.getResultList();

		

		
		

		
		return list;
		
		
		
		
	}
	
	
}
