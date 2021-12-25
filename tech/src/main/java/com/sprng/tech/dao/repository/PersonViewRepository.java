package com.sprng.tech.dao.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprng.tech.dao.entity.table.Person;
import com.sprng.tech.dao.entity.view.PersonView;
import com.sprng.tech.resource.response.success.PersonDto;
import com.sprng.tech.resource.response.success.PersonFullDetailsDto;

@Repository
public interface PersonViewRepository  extends JpaRepository<PersonView, Long> {
	
	
	/*
    SELECT DISTINCT
    	  ROW_NUMBER() OVER (ORDER BY p.id) row_num,
        p.id AS id,
        p.first_name AS first_name,
        p.last_name AS last_name,       
        p.dob AS dob  ,      
        p.bmi AS bmi  ,       
        p.nic AS nic  ,
        p.mobile AS mobile  ,
        ci.value contact_value,      
        mp.id AS profession_id,
        mp.name AS profession_name,
        mpa.id AS profession_area_id,
        mpa.name AS profession_area_name,             
        mcs.code AS civil_status_code,
        mcs.name AS civil_status_name,
        ma.id AS area_id,
        ma.name AS area_name
    FROM   person p 
    JOIN mst_civil_status mcs ON mcs.id = p.mst_civil_status_id  
    JOIN mst_profession mp ON mp.id = p.mst_prfession_id 
	 JOIN mst_profession_area mpa ON mpa.id = mp.mst_prfession_area_id  
    LEFT JOIN (person_contact pc JOIN contact_info ci ON ci.id =pc.contact_id ) ON pc.person_id = p.id
	 LEFT JOIN (person_address pa JOIN address a ON a.id =pa.address_id JOIN mst_area ma ON ma.id = a.area_id ) ON pa.person_id = p.id  

	 */
	
	
	
	@Query(   " SELECT new com.sprng.tech.resource.response.success.PersonFullDetailsDto(pv.rowNum ,pv.firstName ,pv.dob,  pv.civilStatusName"
			+ "                     ,pv.contactValue , pv.bmi , pv.professionName , pv.areaName) " 
			+ "	  FROM PersonView pv "
            + "	  WHERE  "
            + "		(:Nm IS NULL OR ( (pv.firstName LIKE '%' || :Nm || '%') OR  ( upper(pv.lastName) LIKE '%' || upper(:Nm) || '%')   )   ) "
            + " 	AND (:cvlStsCode IS NULL OR pv.civilStatusCode =:cvlStsCode) "
            + " 	AND (:cntctVl IS NULL OR pv.contactValue =:cntctVl) "	
            + " 	AND (:bmi IS NULL OR pv.bmi <=:bmi) "
            + " 	AND (:arId IS NULL OR pv.areaId =:arId) "            
            + " 	AND (:frDt IS NULL OR pv.dob >=:frDt) "	
            + " 	AND (:tDt IS NULL OR pv.dob <=:tDt) "	
    		+ " 	AND pv.professionId IN (:professionIdList) ")	
	List<PersonFullDetailsDto> personSearch2(@Param("Nm") String Name,
            @Param("cvlStsCode") String civilStatusCode,@Param("professionIdList") List<Long> professionIdList
            ,@Param("cntctVl") String contactVal , @Param("bmi") BigDecimal bmi ,@Param("arId") Long areaId
            ,@Param("frDt") Date fromDate ,@Param("tDt") Date toDate);

}
