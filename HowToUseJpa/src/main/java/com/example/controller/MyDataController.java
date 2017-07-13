package com.example.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.MyDataBean;
import com.example.form.MyDataForm;
import com.example.service.MyDataService;
import com.example.service.MyDataService_Factory;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MyDataController {
	
	MyDataService MyDataService;
	
	@Autowired
	MyDataService_Factory MyDataService_Factory;
	
	final static Map<String, String> RADIO_ITEMS_sql =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
				private static final long serialVersionUID = 1L;{
					put("create", "create");
					put("read", "read");
					put("update", "update");
					put("delete", "delete");
				}
			}
	);
	
	final static Map<String, String> RADIO_ITEMS_jpa =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
				private static final long serialVersionUID = 1L;{
					put("Repository", "Repository");
					put("JPQL", "JPQL");
					put("Criteria API", "Criteria API");
				}
			}
	);
	
	
	// 1.リクエスト時に必ず呼び出す(Formの初期化)----------------------------
	@ModelAttribute(value ="mydataform") 
	MyDataForm setUpmydataform() {
        System.out.println("create MyDataForm");
        return new MyDataForm();
    }

	// 2.ルート---------------------------------------------------------
	@GetMapping
    ModelAndView MyDataList(MyDataForm MyDataForm, ModelAndView mav) {
		create_MyDataList(MyDataForm);
		mav_create_to_top(MyDataForm, mav);
		return mav;
		
	}

	private void create_MyDataList(MyDataForm MyDataForm) {
		MyDataService MyDataService = MyDataService_Factory.create(MyDataForm);
		List<MyDataBean> MyDataList = MyDataService.find_All();
		
		MyDataForm.setMydatalist(MyDataList);
	}
	
	private void mav_create_to_top(MyDataForm MyDataForm, ModelAndView mav) {
		mav.addObject("mydataform", MyDataForm);
		mav.addObject("radioItems_sql", RADIO_ITEMS_sql);
		mav.addObject("radioItems_jpa", RADIO_ITEMS_jpa);
		mav.setViewName("MyData/MyDataList");
	}
	
	
	// 3.検索時に利用---------------------------------------------------------
	@PostMapping(value="operate_form")
	ModelAndView operate_form(@Validated @ModelAttribute("mydataform")MyDataForm MyDataForm, BindingResult result, ModelAndView mav) {
		
		// 単項目チェック
		if (result.hasErrors()) {
			create_MyDataList(MyDataForm);
			mav_create_to_top(MyDataForm, mav);
			return mav;
		}
			
		//　TODO：相関チェック
			
		/*　TODO : リファクタリング(Factoryパターン+Strategyパターン+Stateパターンに変更)
		 * sqlの利用
		 * 1.repository + Criteria API 進捗：65%
		 * 2.JPQL 進捗：65%
		 * 3.Criteria API 進捗：65%
		 */
		
		// Set Up
		MyDataService MyDataService = MyDataService_Factory.create(MyDataForm);
		MyDataBean MyDataBean = new MyDataBean();
		BeanUtils.copyProperties(MyDataForm, MyDataBean);
		String CRUD = MyDataForm.getSql();
		
		switch (CRUD) {
		
			case "create":
				MyDataService.create(MyDataBean);
				break;
				
			case "read":
				List<MyDataBean> MyDataList = MyDataService.find_many(MyDataBean);
				MyDataForm.setMydatalist(MyDataList);
				mav_create_to_top(MyDataForm, mav);
				return mav;
				
			case "update":
				MyDataService.update(MyDataBean);
				break;
				
			case "delete":
				MyDataService.delete(MyDataBean);
				break;
		};
		
		return new ModelAndView("redirect:/");
	}	
}
