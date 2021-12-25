package com.sprng.tech.dao.repository;

import org.springframework.stereotype.Repository;
import com.sprng.tech.dao.entity.table.Person;
import com.sprng.tech.resource.response.success.PersonDto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long> {
	
	
	@Query(   " SELECT p from Person p "
            + "     JOIN MstProfession mp ON mp.id = p.mstProfession.id "
            + "     LEFT JOIN MstCivilStatus mcs ON mcs.id = p.mstCivilStatus.id "
            + "	  WHERE  (:Nm is null or p.firstName =:Nm) "
            + " 	AND (:cvlStsCode is null or mcs.code =:cvlStsCode) ")	
	List<Person> personSearch1(@Param("Nm") String Nm,
            @Param("cvlStsCode") String civilStatusCode);
	
	
	@Query(   " SELECT new com.sprng.tech.resource.response.success.PersonDto(p.id ,p.firstName ,p.dob,  mcs.name) " 
			+ "	  FROM Person p "
            + "     JOIN MstProfession mp ON mp.id = p.mstProfession.id "
            + "     LEFT JOIN MstCivilStatus mcs ON mcs.id = p.mstCivilStatus.id "
            + "	  WHERE  (:Nm is null or p.firstName =:Nm) "
            + " 	AND (:cvlStsCode is null or mcs.code =:cvlStsCode) ")	
	List<PersonDto> personSearch2(@Param("Nm") String Nm,
            @Param("cvlStsCode") String civilStatusCode);
	
	@Query(   " SELECT new com.sprng.tech.resource.response.success.PersonDto(p.id ,p.firstName ,p.dob, mcs.name) " 
			+ "	  FROM Person p "
            + "     JOIN MstProfession mp ON mp.id = p.mstProfession.id "
            + "     LEFT JOIN MstCivilStatus mcs ON mcs.id = p.mstCivilStatus.id "
            + "	  WHERE  (:Nm is null or p.firstName =:Nm) "
            + " 	AND (:cvlStsCode is null or mcs.code =:cvlStsCode) ")	
	Page<PersonDto> personSearchPage1(@Param("Nm") String Nm,
            @Param("cvlStsCode") String civilStatusCode,Pageable page);
	
	
	@Query(   " SELECT p FROM Person p "
            + "	  WHERE   p.mstProfession.id IN (:professionIdList)  ")	
	List<Person> personSearchInProfession( @Param("professionIdList") List<Long> professionIdList);
	
	
	@Query(   " SELECT DISTINCT new com.sprng.tech.resource.response.success.PersonDto(p.id ,p.firstName ,p.dob,  mcs.name) " 
			+ "	  FROM Person p "
            + "     JOIN MstProfession mp ON mp.id = p.mstProfession.id "
            + "     JOIN MstProfessionArea mpa ON mpa.id = mp.mstProfessionArea.id "
            + "     LEFT JOIN MstCivilStatus mcs ON mcs.id = p.mstCivilStatus.id  "
			+ "     LEFT JOIN PersonContact pc ON pc.person.id = p.id  "
			+ "     JOIN ContactInfo ci ON ci.id = pc.contactId.id  "
			+ "     LEFT JOIN PersonAddress pa ON pa.person.id = p.id  "
			+ "     JOIN Address add ON add.id = pa.address.id  "
            + "	  WHERE  "
            + "		(:Nm IS NULL OR ( (p.firstName LIKE '%' || :Nm || '%') OR  ( upper(p.lastName) LIKE '%' || upper(:Nm) || '%')   )   ) "
            + " 	AND (:cvlStsCode IS NULL OR mcs.code =:cvlStsCode) "
            + " 	AND (:cntctVl IS NULL OR ci.value =:cntctVl) "	
            + " 	AND (:bmi IS NULL OR p.bmi <=:bmi) "
            + " 	AND (:arId IS NULL OR add.mstArea.id =:arId) "            
            + " 	AND (:frDt IS NULL OR p.dob >=:frDt) "	
            + " 	AND (:tDt IS NULL OR p.dob <=:tDt) "	
    		+ " 	AND p.mstProfession.id IN (:professionIdList) ")
	Page<PersonDto> personSearchJoin(
			@Param("Nm") String Name,
            @Param("cvlStsCode") String civilStatusCode,@Param("professionIdList") List<Long> professionIdList
            ,@Param("cntctVl") String contactVal , @Param("bmi") BigDecimal bmi ,@Param("arId") Long areaId
            ,@Param("frDt") Date fromDate ,@Param("tDt") Date toDate,
			Pageable page);
	
	
	
}
