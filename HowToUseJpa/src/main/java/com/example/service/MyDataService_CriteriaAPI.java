package com.example.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;
import com.example.repository.MyDataRepository_CriteriaAPI;

@Service
@Transactional
public class MyDataService_CriteriaAPI extends MyDataService{

	MyDataRepository_CriteriaAPI MyDataRepositoryCriteriaAPI;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@PostConstruct
	public void init() {
		MyDataRepositoryCriteriaAPI = new MyDataRepository_CriteriaAPI(entityManager);
	}
	
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
			MyDataRepositoryCriteriaAPI.create(MyData);
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
			
			MyData MyData = MyDataRepositoryCriteriaAPI.findByid(MyDataBean);
			BeanUtils.copyProperties(MyData, MyDataBean);
			
			return MyDataBean;
		}
		
		/**
		 * 全件検索
		 * @param MyDataBean Veiw上のデータ
		 * @return MyDataBean Veiwに表示する検索結果
		 * 
		 */
		@Override
		public List<MyDataBean> find_All() {
			
			List<MyData> MyDatalList = MyDataRepositoryCriteriaAPI.find_All();
			List<MyDataBean> MyDataBeanList = from_MyDatalList_To_MyDataBeanList(MyDatalList);
			
			return MyDataBeanList;
		}
		
		/**
		 * 検索(動的に検索条件を設定)
		 * @param MyDataBean Veiw上のデータ
		 * @return MyDataBeanList Veiwに表示する検索結果
		 * 
		 */
		@Override
		public List<MyDataBean> find_many(MyDataBean MyDataBean) {
			
			List<MyData> MyDatalList = MyDataRepositoryCriteriaAPI.find_many(MyDataBean);
			List<MyDataBean> MyDataBeanList = from_MyDatalList_To_MyDataBeanList(MyDatalList);
			
			return  MyDataBeanList;
		}
		

			
		// 【crUd】--------------------------------------------
		/**
		 * 更新(id利用)
		 * @param MyDataBean Veiw上のデータ
		 * 
		 */
		@Override
		public void update(MyDataBean MyDataBean) {
		
			MyData MyData = MyDataRepositoryCriteriaAPI.findByid(MyDataBean);
			if (MyData.getId() != null) {
				BeanUtils.copyProperties(MyDataBean, MyData);
				MyDataRepositoryCriteriaAPI.update(MyData);
			}
		}
		
		// 【cruD】--------------------------------------------
		/**
		 * 削除
		 * @param MyDataBean Veiw上のデータ(PKとPK以外で挙動が変わるよ)
		 * 
		 */
		@Override
		public void delete(MyDataBean MyDataBean) {
			
			List<MyData> MyDatalList = MyDataRepositoryCriteriaAPI.find_many(MyDataBean);
			if (MyDatalList.size() != 0) {
				for (MyData MyData: MyDatalList) {
					MyDataRepositoryCriteriaAPI.delete(MyData);
				}	
			}
		}
}
