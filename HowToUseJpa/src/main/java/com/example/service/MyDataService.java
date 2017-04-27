package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;
import com.example.repository.MyDataRepository;

@Service
@Transactional
public class MyDataService {
	
	@Autowired
	MyDataRepository MyDataRepository;
	
	// 【Crud】--------------------------------------------
	
	// 【cRud】--------------------------------------------
	public MyDataBean findByid(MyDataBean MyDataBean) {
		
		MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
		
		if (MyData == null) return MyDataBean;
		
		BeanUtils.copyProperties(MyData, MyDataBean);
		
		return MyDataBean;
	}
	
	public  List<MyDataBean> find_All() {
		
		List<MyData> MyDatalList = MyDataRepository.findAll();
		List<MyDataBean> MyDataBeanList = from_MyDatalList_To_MyDataBean(MyDatalList);
		
        return  MyDataBeanList;
	}
	
	private List<MyDataBean> from_MyDatalList_To_MyDataBean(List<MyData> MyDatalList){

		List<MyDataBean> MyDataBeanList =  new ArrayList<>();
		MyDatalList.stream()
				   .forEach(MyData -> {
					   MyDataBean MyDataBean = new MyDataBean();
					   BeanUtils.copyProperties(MyData, MyDataBean);
					   MyDataBeanList.add(MyDataBean);
					});
		return MyDataBeanList;

	}
		
	// 【crUd】--------------------------------------------
	public void update(MyDataBean MyDataBean) {
				
				MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
				if (MyData != null) {
					BeanUtils.copyProperties(MyDataBean, MyData);
					MyDataRepository.saveAndFlush(MyData);
				}
			}
	// 【cruD】--------------------------------------------
}
