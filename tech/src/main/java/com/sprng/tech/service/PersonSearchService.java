package com.sprng.tech.service;

import java.util.List;

import com.sprng.tech.dao.entity.view.PersonView;
import com.sprng.tech.resource.request.PersonSearchRequest;

public interface PersonSearchService {
	
	List<PersonView> searchPersonCollectStream1(PersonSearchRequest request,int pageNum,int pageSize);

}
