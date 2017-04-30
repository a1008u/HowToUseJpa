package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;
import com.example.repository.MyDataRepository;
import com.example.repository.SpecificationsDetail_MyData;

@Service
@Transactional
public class MyDataService_repository {
	
	@Autowired
	MyDataRepository MyDataRepository;
	
	@Autowired
	SpecificationsDetail_MyData SpecificationsMyData;
	
	// 【Crud】--------------------------------------------
	public void create(MyDataBean MyDataBean) {
		
		MyData MyData = new MyData();
		BeanUtils.copyProperties(MyDataBean, MyData);
		MyDataRepository.saveAndFlush(MyData);
	}
	
	
	// 【cRud】--------------------------------------------
	public MyDataBean findByid(MyDataBean MyDataBean) {
		
		MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
		
		if (MyData == null) return MyDataBean;
		
		BeanUtils.copyProperties(MyData, MyDataBean);
		
		return MyDataBean;
	}
	
	// 利用しない-----------------------------------------------------------------
	public MyDataBean findByname(MyDataBean MyDataBean) {
		
		MyData MyData = MyDataRepository.findByname(MyDataBean.getName());
		
		if (MyData == null) return MyDataBean;
		
		BeanUtils.copyProperties(MyData, MyDataBean);
		
		return MyDataBean;
	}
	
	public MyDataBean findByage(MyDataBean MyDataBean) {
		
		MyData MyData = MyDataRepository.findByage(MyDataBean.getAge());

		if (MyData == null) return MyDataBean;
		
		BeanUtils.copyProperties(MyData, MyDataBean);
		
		return MyDataBean;
	}
	
	public MyDataBean findBymessage(MyDataBean MyDataBean) {
		
		MyData MyData = MyDataRepository.findBymessage(MyDataBean.getMessage());
		
		if (MyData == null) return MyDataBean;
		
		BeanUtils.copyProperties(MyData, MyDataBean);
		
		return MyDataBean;
	}
	// 利用しない-----------------------------------------------------------------
	
	public  List<MyDataBean> find_All() {
		
		List<MyData> MyDatalList = MyDataRepository.findAll();
		List<MyDataBean> MyDataBeanList = from_MyDatalList_To_MyDataBean(MyDatalList);
		
        return  MyDataBeanList;
	}
	
	
	// TODO:要修正
	// 現状はor条件の組み合わせで結果を取得しているが、and条件で結果を取得できるように変更する
	public List<MyDataBean> find_many(MyDataBean MyDataBean) {
		
		List<MyData> MyDatalList = SpecificationsMyData.find_many(MyDataBean);
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
	
		// TODO:MyDataを取得するバリエーションを増やす必要がある
	
		MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
		if (MyData != null) {
			BeanUtils.copyProperties(MyDataBean, MyData);
			MyDataRepository.saveAndFlush(MyData);
		}
	}
	// 【cruD】--------------------------------------------
	public void delete(MyDataBean MyDataBean) {
		
		// TODO:MyDataを取得するバリエーションを増やす必要がある
		
		MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
		if (MyData != null) {
			MyDataRepository.delete(MyData);
		}
	}
	
}
