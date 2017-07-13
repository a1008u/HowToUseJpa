package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.example.form.MyDataForm;

@Service
public class MyDataService_Factory {
	
	@Autowired
	MyDataService_repository MyDataServiceRepository;
	
	@Autowired
	MyDataService_JPQL MyDataServiceJPQL;
	
	@Autowired
	MyDataService_CriteriaAPI MyDataServiceCriteriaAPI;
	
	// Strategyパターン
	MyDataService MyDataService;
	
	public MyDataService create(MyDataForm MyDataForm) {
		
		// TODO: Stateパターンに変更
		if ("Repository".equals(MyDataForm.getJpa()) || StringUtils.isEmpty(MyDataForm.getJpa())) {
			
			MyDataService = MyDataServiceRepository;
			
		} else if("JPQL".equals(MyDataForm.getJpa())) {
			
			MyDataService = MyDataServiceJPQL;
			
		} else if("Criteria API".equals(MyDataForm.getJpa())) {
			
			MyDataService = MyDataServiceCriteriaAPI;
		}
		
		return MyDataService;
		
	}

}
