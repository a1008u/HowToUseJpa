package com.example.controller;

import java.util.ArrayList;
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


@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MyDataController {
	
	@Autowired
	MyDataService MyDataService;
	
  final static Map<String, String> RADIO_ITEMS =
		    Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
		    {
		      put("create", "create");
		      put("read", "read");
		      put("update", "update");
		      put("delete", "delete");
		    }
		  });
	
	
	// 1.リクエスト時に必ず呼び出す(Formの初期化)----------------------------
	@ModelAttribute(value ="mydataform") 
	MyDataForm setUpmydataform() {
        System.out.println("create MyDataForm");
        return new MyDataForm();
    }

	// 2.ルート---------------------------------------------------------
	@GetMapping
    ModelAndView MyDataList(MyDataForm MyDataForm, ModelAndView mav) {
		
		List<MyDataBean> MyDataList = MyDataService.find_All();
		
		MyDataForm.setMydatalist(MyDataList);
		mav.addObject("mydataform", MyDataForm);
		mav.addObject("radioItems", RADIO_ITEMS);
		
		mav.setViewName("MyData/MyDataList");
		return mav;
		
	}
	
	// 2.ルート---------------------------------------------------------
	@PostMapping(value="operate_form")
	ModelAndView operate_form(@Validated @ModelAttribute("mydataform")MyDataForm MyDataForm, BindingResult result, ModelAndView mav) {
		
		if (result.hasErrors()) return new ModelAndView("redirect:/");
		
		MyDataBean MyDataBean = new MyDataBean();
		BeanUtils.copyProperties(MyDataForm, MyDataBean);
		
		/*　TODO : リファクタリング
		 * sqlの利用
		 *  
		 */
		switch (MyDataForm.getSql()) {
			case "create":
				
				break;
			case "read":
				MyDataBean = MyDataService.findByid(MyDataBean);
				
				if ("".equals(MyDataBean.getName())) return new ModelAndView("redirect:/");
				
				List<MyDataBean> MyDataList = new ArrayList<>();
				MyDataList.add(MyDataBean);
				MyDataForm.setMydatalist(MyDataList);
				
				mav.addObject("mydataform", MyDataForm);
				break;
			case "update":
				MyDataService.update(MyDataBean);
				return new ModelAndView("redirect:/");
				
			case "delete":
				
				break;
		};
		
		mav.addObject("radioItems", RADIO_ITEMS);
		mav.setViewName("MyData/MyDataList");
		return mav;
		
	}
	
}
