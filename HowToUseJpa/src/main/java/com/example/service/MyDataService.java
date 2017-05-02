package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;

public abstract class MyDataService {

	// 【Crud】--------------------------------------------
		/**
		 * 新規作成
		 * @param MyDataBean Veiw上のデータ
		 * 
		 */
		public abstract void create(MyDataBean MyDataBean);
		
		
		// 【cRud】--------------------------------------------
		/**
		 * 検索(id利用)
		 * @param MyDataBean Veiw上のデータ
		 * @return MyDataBean Veiwに表示する検索結果
		 * 
		 */
		public abstract MyDataBean findByid(MyDataBean MyDataBean);
		
		/**
		 * 全件検索
		 * @param MyDataBean Veiw上のデータ
		 * @return MyDataBean Veiwに表示する検索結果
		 * 
		 */
		public abstract List<MyDataBean> find_All();
		
		/**
		 * 検索(動的に検索条件を設定)
		 * @param MyDataBean Veiw上のデータ
		 * @return MyDataBeanList Veiwに表示する検索結果
		 * 
		 */
		public abstract List<MyDataBean> find_many(MyDataBean MyDataBean);
			
		// 【crUd】--------------------------------------------
		/**
		 * 更新(id利用)
		 * @param MyDataBean Veiw上のデータ
		 * 
		 */
		public abstract void update(MyDataBean MyDataBean);
		
		// 【cruD】--------------------------------------------
		/**
		 * 削除
		 * @param MyDataBean Veiw上のデータ(PKとPK以外で挙動が変わるよ)
		 * 
		 */
		public abstract void delete(MyDataBean MyDataBean);
		
		public List<MyDataBean> from_MyDatalList_To_MyDataBean(List<MyData> MyDatalList){

			List<MyDataBean> MyDataBeanList =  new ArrayList<>();
			MyDatalList.stream()
					   .forEach(MyData -> {
						   MyDataBean MyDataBean = new MyDataBean();
						   BeanUtils.copyProperties(MyData, MyDataBean);
						   MyDataBeanList.add(MyDataBean);
						});
			return MyDataBeanList;

		}
	
}
