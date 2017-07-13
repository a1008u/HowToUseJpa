package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;
import com.example.repository.MyDataRepository;
import com.example.repository.SpecificationsDetail_MyData;

@Service
@Transactional
public class MyDataService_repository extends MyDataService {
	
	@Autowired
	MyDataRepository MyDataRepository;
	
	@Autowired
	SpecificationsDetail_MyData SpecificationsMyData;
	
	// 【Crud】--------------------------------------------
	/**
	 * 新規作成
	 * @param MyDataBean Veiw上のデータ
	 * 
	 */
	@Override
	public void create(MyDataBean MyDataBean) {
		
		MyData MyData = new MyData();
		BeanUtils.copyProperties(MyDataBean, MyData);
		MyDataRepository.saveAndFlush(MyData);
	}
	
	
	// 【cRud】--------------------------------------------
	/**
	 * 検索(id利用)
	 * @param MyDataBean Veiw上のデータ
	 * @return MyDataBean Veiwに表示する検索結果
	 * 
	 */
	@Override
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
	/**
	 * 検索(全件抽出)
	 * @param MyDataBean Veiw上のデータ
	 * @return MyDataBeanList Veiwに表示する検索結果
	 * 
	 */
	@Override
	public  List<MyDataBean> find_All() {
		
		List<MyData> MyDatalList = MyDataRepository.findAll();
		List<MyDataBean> MyDataBeanList = from_MyDatalList_To_MyDataBean(MyDatalList);
		
        return  MyDataBeanList;
	}
	
	/**
	 * 検索(動的に検索条件を設定)
	 * @param MyDataBean Veiw上のデータ
	 * @return MyDataBeanList Veiwに表示する検索結果
	 * 
	 */
	@Override
	public List<MyDataBean> find_many(MyDataBean MyDataBean) {
		
		List<MyData> MyDatalList = SpecificationsMyData.find_many(MyDataBean);
		List<MyDataBean> MyDataBeanList = from_MyDatalList_To_MyDataBean(MyDatalList);
		
		return  MyDataBeanList;
	}
		
	// 【crUd】--------------------------------------------
	@Override
	public void update(MyDataBean MyDataBean) {
	
		// TODO:MyDataを取得するバリエーションを増やす必要がある
	
		MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
		if (MyData != null) {
			BeanUtils.copyProperties(MyDataBean, MyData);
			MyDataRepository.saveAndFlush(MyData);
		}
	}
	// 【cruD】--------------------------------------------
	@Override
	public void delete(MyDataBean MyDataBean) {
		
		// TODO:MyDataを取得するバリエーションを増やす必要がある
		
		MyData MyData = MyDataRepository.findByid(MyDataBean.getId());
		if (MyData != null) {
			MyDataRepository.delete(MyData);
		}
	}
	
}
