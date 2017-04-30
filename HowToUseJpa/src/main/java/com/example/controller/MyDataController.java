package com.example.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.example.service.MyDataService_repository;


@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MyDataController {
	
	@Autowired
	MyDataService_repository MyDataServiceRepository;
	
  final static Map<String, String> RADIO_ITEMS =
		    Collections.unmodifiableMap(new LinkedHashMap<String, String>() { 
		    	private static final long serialVersionUID = 1L;
		    	{
		    		put("create", "create");
		    		put("read", "read");
		    		put("update", "update");
		    		put("delete", "delete");
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
		
		MyDataList_top(MyDataForm, mav);
		return mav;
		
	}

	private void MyDataList_top(MyDataForm MyDataForm, ModelAndView mav) {
		List<MyDataBean> MyDataList = MyDataServiceRepository.find_All();
		
		MyDataForm.setMydatalist(MyDataList);
		mav.addObject("mydataform", MyDataForm);
		mav.addObject("radioItems", RADIO_ITEMS);
		
		mav.setViewName("MyData/MyDataList");
	}
	
	// 3.ルート---------------------------------------------------------
	@PostMapping(value="operate_form")
	ModelAndView operate_form(@Validated @ModelAttribute("mydataform")MyDataForm MyDataForm, BindingResult result, ModelAndView mav) {
		
		if (result.hasErrors()) {
			
			MyDataList_top(MyDataForm, mav);
			
		} else {
			
			/*　TODO : リファクタリング
			 * sqlの利用
			 * 1.repository + Criteria API 進捗：60%
			 * 2.JPQL 進捗：40%
			 * 3.Criteria API 進捗：0%
			 *  
			 */
			
			// Set Up
			List<MyDataBean> MyDataList;
			MyDataBean MyDataBean = new MyDataBean();
			BeanUtils.copyProperties(MyDataForm, MyDataBean);
			
			switch (MyDataForm.getSql()) {
			
			case "create":
				
				MyDataServiceRepository.create(MyDataBean);
				return new ModelAndView("redirect:/");
				
			case "read":
				
				if (!StringUtils.isEmpty(MyDataBean.getId())) {
					/* -------------入力値で取得------------- */
					MyDataList = MyDataServiceRepository.find_many(MyDataBean);
					/* -------------入力値で取得------------- */
				} else {
					/* -------------PKのみで取得------------- */
					MyDataBean = MyDataServiceRepository.findByid(MyDataBean);
					if ("".equals(MyDataBean.getName())) return new ModelAndView("redirect:/");
					MyDataList = new ArrayList<>();
					MyDataList.add(MyDataBean);
					/* -------------PKのみで取得------------- */
				}
				
				MyDataForm.setMydatalist(MyDataList);
				
				mav.addObject("mydataform", MyDataForm);
				break;
				
			case "update":
				MyDataServiceRepository.update(MyDataBean);
				return new ModelAndView("redirect:/");
				
			case "delete":
				MyDataServiceRepository.delete(MyDataBean);
				return new ModelAndView("redirect:/");
			};
		
			mav.addObject("radioItems", RADIO_ITEMS);
			mav.setViewName("MyData/MyDataList");
			
		}
		
		return mav;
		
	}
	
}
