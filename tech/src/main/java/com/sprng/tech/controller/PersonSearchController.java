package com.sprng.tech.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprng.comn_comman.core.LoggerRequest;
import com.sprng.tech.dao.entity.table.Person;
import com.sprng.tech.dao.entity.view.PersonView;
import com.sprng.tech.dao.impl.PersonSearchDaoImpl;
import com.sprng.tech.dao.impl.PersonSearchQueryDaoImpl;
import com.sprng.tech.dao.repository.PersonRepository;
import com.sprng.tech.dao.repository.PersonViewRepository;
import com.sprng.tech.dao.wrapper.PersonDetailRespDto;
import com.sprng.tech.resource.request.PersonSearchRequest;
import com.sprng.tech.resource.response.success.PersonDto;
import com.sprng.tech.resource.response.success.PersonFinalResponse;
import com.sprng.tech.resource.response.success.PersonFullDetailsDto;
import com.sprng.tech.resource.response.success.PersonResponse;
import com.sprng.tech.service.PersonSearchService;
import com.sprng.tech.util.DateUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping(value = "/person-search")
public class PersonSearchController {
	
	@Autowired
	private PersonSearchDaoImpl personSearchDaoImpl;
	
	@Autowired
	private PersonSearchQueryDaoImpl personSearchQueryDaoImpl;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonViewRepository personViewRepository;
	
	@Autowired
	private PersonSearchService personSearchService;
	
	@Autowired
	private DateUtil dateUtil;
	
 	@PostMapping(value = "/person-repo-search-1")
 	public List<Person> getPersonSearchRepo1(@RequestBody  PersonSearchRequest request) {
 				
 		return personRepository.personSearch1(request.getName(),request.getCivilStatusCode());

 	}
	
 	@PostMapping(value = "/search")
 	public List<PersonResponse> getPersonSearch(@RequestBody  PersonSearchRequest request) {
 				
 		return personSearchDaoImpl.personSearch1(request);

 	}
 	
 	@PostMapping(value = "/search-pagination")
 	public PersonFinalResponse getPersonSearchPagination(@RequestBody  PersonSearchRequest request,
 			@RequestParam(value = "page", required = false,defaultValue ="0") int page,
 			@RequestParam(value = "size", required = false,defaultValue ="100") int size) {
 		
 		for(int i=0;i<10;i++) {
 			LoggerRequest.getInstance().logInfo1("----- enter to the search method---------");
 		}
 				
 		return personSearchDaoImpl.personSearchPagination1(request,PageRequest.of(page, size));


 	}
 	
 	
 	@PostMapping(value = "/jpa-search")
 	public Page<PersonDto> jpaSearch(@RequestBody  PersonSearchRequest request,
 			@RequestParam(value = "page", required = false,defaultValue ="0") int page,
 			@RequestParam(value = "size", required = false,defaultValue ="100") int size) {
 		
 		return personRepository.personSearchPage1(request.getName(),request.getCivilStatusCode(),PageRequest.of(page, size));


 	}
 	
 	
	
 	@PostMapping(value = "/search-query1")
 	public List<PersonDetailRespDto>  getPersonSearchQuery(@RequestBody  PersonSearchRequest request) {
 				
 		return  personSearchQueryDaoImpl.search(request);

 	}
 	
 	@PostMapping(value = "/search-profession-list")
 	public List<Person>  getByProfessionList(@RequestBody  List<Long> professionIdList) {
 				
 		List<Long> profList = professionIdList;
 		if(professionIdList.isEmpty()) {
 			profList.add(1l);profList.add(2l);profList.add(3l);profList.add(4l); 			
 		}
 		return  personRepository.personSearchInProfession(profList);

 	}
 	
 	
 	@PostMapping(value = "/search-query2")
 	public List<PersonDetailRespDto>  getPersonSearchQueryPagination(@RequestBody  PersonSearchRequest request) {
 				
 		return  personSearchQueryDaoImpl.sqlQuery1(request);

 	}
 	

 	@GetMapping(value = "/person-view-all")
 	public List<PersonView>  getDetailsByView() {
 				
 		return personViewRepository.findAll();

 	}
 	
 	@PostMapping(value = "/search-view-1")
 	public List<PersonFullDetailsDto>  searchView(@RequestBody  PersonSearchRequest request) {
 		
 		List<Long> profList = request.getProfeesionIdList();
 		if((request.getProfeesionIdList() == null) || (request.getProfeesionIdList().isEmpty())) {
 			profList = new ArrayList<>();
 			profList.add(1l);profList.add(2l);profList.add(3l);profList.add(4l); 			
 		}
 		
		Date startDateFormatted = null;
		Date endDateFormatted = null;

		if (request.getFromDate() != null) {
			startDateFormatted = dateUtil.convertStringToStartDateWithTime(request.getFromDate());
		}
		if (request.getToDate() != null) {
			endDateFormatted = dateUtil.convertStringToEndDateWithTime(request.getToDate());
		}
 		
 		return personViewRepository.personSearch2(request.getName() ,request.getCivilStatusCode() , profList ,request.getContactValue() ,request.getBmi(), request.getAreaId() , startDateFormatted, endDateFormatted) ;

 	}
 	
 	
 	@PostMapping(value = "/jpa-search-join")
 	public Page<PersonDto> jpaSearchJoin(@RequestBody  PersonSearchRequest request,
 			@RequestParam(value = "page", required = false,defaultValue ="0") int page,
 			@RequestParam(value = "size", required = false,defaultValue ="100") int size) {
 		
 		
 		List<Long> profList = request.getProfeesionIdList();
 		if((request.getProfeesionIdList() == null) || (request.getProfeesionIdList().isEmpty())) {
 			profList = new ArrayList<>();
 			profList.add(1l);profList.add(2l);profList.add(3l);profList.add(4l); 			
 		}
 		
		Date startDateFormatted = null;
		Date endDateFormatted = null;

		if (request.getFromDate() != null) {
			startDateFormatted = dateUtil.convertStringToStartDateWithTime(request.getFromDate());
		}
		if (request.getToDate() != null) {
			endDateFormatted = dateUtil.convertStringToEndDateWithTime(request.getToDate());
		}
 		
 		return personRepository.personSearchJoin(request.getName() ,request.getCivilStatusCode() , profList ,request.getContactValue() ,request.getBmi(), request.getAreaId() , startDateFormatted, endDateFormatted,PageRequest.of(page, size));


 	}
 	
 	
 	@PostMapping(value = "/search-collection-stream-1")
 	public List<PersonView> searchCollectionStream1(@RequestBody  PersonSearchRequest request,
 			@RequestParam(value = "page", required = false,defaultValue ="0") int page,
 			@RequestParam(value = "size", required = false,defaultValue ="100") int size) {
 		
 		
 		return  personSearchService.searchPersonCollectStream1(request, 0, 10);

 	}
 	


}
