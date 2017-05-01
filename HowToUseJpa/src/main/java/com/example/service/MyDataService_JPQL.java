package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;
import com.example.repository.MyDataRepository_JPQL;

@Service
@Transactional
public class MyDataService_JPQL {
	
	MyDataRepository_JPQL MyDataRepositoryJPQL;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@PostConstruct
	public void init() {
		MyDataRepositoryJPQL = new MyDataRepository_JPQL(entityManager);
	}
	
	// 【Crud】--------------------------------------------
	/**
	 * 新規作成
	 * @param MyDataBean Veiw上のデータ
	 * 
	 */
	public void create(MyDataBean MyDataBean) {
		
		MyData MyData = new MyData();
		BeanUtils.copyProperties(MyDataBean, MyData);
		MyDataRepositoryJPQL.crate(MyData);
	}
	
	
	// 【cRud】--------------------------------------------
	/**
	 * 検索(id利用)
	 * @param MyDataBean Veiw上のデータ
	 * @return MyDataBean Veiwに表示する検索結果
	 * 
	 */
	public MyDataBean findByid(MyDataBean MyDataBean) {
		
		MyData MyData = MyDataRepositoryJPQL.findByid(MyDataBean);
		BeanUtils.copyProperties(MyData, MyDataBean);
		
		return MyDataBean;
	}
	
	/**
	 * 検索(動的に検索条件を設定)
	 * @param MyDataBean Veiw上のデータ
	 * @return MyDataBeanList Veiwに表示する検索結果
	 * 
	 */
	public List<MyDataBean> find_many(MyDataBean MyDataBean) {
		
		List<MyData> MyDatalList = MyDataRepositoryJPQL.find_many(MyDataBean);
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
	/**
	 * 更新(id利用)
	 * @param MyDataBean Veiw上のデータ
	 * 
	 */
	public void update(MyDataBean MyDataBean) {
	
		MyData MyData = MyDataRepositoryJPQL.findByid(MyDataBean);
		if (MyData.getId() != null) {
			BeanUtils.copyProperties(MyDataBean, MyData);
			MyDataRepositoryJPQL.update(MyData);
		}
	}
	
	// 【cruD】--------------------------------------------
	/**
	 * 削除
	 * @param MyDataBean Veiw上のデータ(PKとPK以外で挙動が変わるよ)
	 * 
	 */
	public void delete(MyDataBean MyDataBean) {
		
		List<MyData> MyDatalList = MyDataRepositoryJPQL.find_many(MyDataBean);
		if (MyDatalList.size() != 0) {
			for (MyData MyData: MyDatalList) {
				MyDataRepositoryJPQL.delete(MyData);
			}	
		}
	}
}
