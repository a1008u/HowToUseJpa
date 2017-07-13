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
		
		// 三項演算子にて、利用する演算子を制御（デフォルトは、MyDataServiceRepository）
		MyDataService = "JPQL".equals(MyDataForm.getJpa())? MyDataServiceJPQL
										: "Criteria API".equals(MyDataForm.getJpa()) ? MyDataServiceCriteriaAPI
									    : MyDataServiceRepository;
		
		return MyDataService;
		
	}

}
