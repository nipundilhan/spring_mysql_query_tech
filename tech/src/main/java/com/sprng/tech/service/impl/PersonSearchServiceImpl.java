package com.sprng.tech.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sprng.tech.dao.entity.view.PersonView;
import com.sprng.tech.dao.repository.PersonViewRepository;
import com.sprng.tech.resource.request.PersonSearchRequest;
import com.sprng.tech.service.PersonSearchService;
import com.sprng.tech.util.DateUtil;


@Service
public class PersonSearchServiceImpl implements PersonSearchService{
	
	
	@Autowired
	private PersonViewRepository personViewRepository;
	
	
	@Autowired
	private DateUtil dateUtil;
	
	public List<PersonView> searchPersonCollectStream1(PersonSearchRequest request,int pageNum,int pageSize) {
		
		List<PersonView> personList = personViewRepository.findAll();
		


		if(request.getName() != null) {
			personList = personList.stream().filter(i -> i.getFirstName().contains(request.getName()) || i.getLastName().contains(request.getName()) ).collect(Collectors.toList());
		}
		if(request.getContactValue() != null) {
			personList = personList.stream().filter(i -> i.getContactValue().equals(request.getContactValue()) ).collect(Collectors.toList());
		}
		if(request.getBmi() != null) {
			personList = personList.stream().filter(i -> (i.getBmi()).compareTo(request.getBmi())>0 ).collect(Collectors.toList());
		}	
		if(request.getProfeesionIdList() != null) {
			personList = personList.stream().filter(i -> (request.getProfeesionIdList()).contains(i.getProfessionId()) ).collect(Collectors.toList());
		}
		if(request.getFromDate()!= null) {
			Date startDateFormatted = dateUtil.convertStringToStartDateWithTime(request.getFromDate());		
			personList = personList.stream().filter(i ->  i.getDob().after(startDateFormatted) ).collect(Collectors.toList());
		}	
		if(request.getToDate() != null) {
			Date endDateFormatted = dateUtil.convertStringToEndDateWithTime(request.getToDate());
			personList = personList.stream().filter(i ->  i.getDob().before(endDateFormatted) ).collect(Collectors.toList());
		}	
		if(request.getCivilStatusCode() != null) {
			personList = personList.stream().filter(i -> i.getCivilStatusCode().equals(request.getCivilStatusCode()) ).collect(Collectors.toList());
		}
		if(request.getAreaId() != null) {
			personList = personList.stream().filter(i -> i.getAreaId().equals(request.getAreaId()) ).collect(Collectors.toList());
		}
		
		personList = personList.stream().skip(pageNum*pageSize).limit(pageSize).collect(Collectors.toList());
		
		
		return personList;
		
		
	}

}
