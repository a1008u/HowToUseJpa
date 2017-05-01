package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;

@Repository
public class MyDataRepository_JPQL{
	
	private EntityManager entityManager;
	
	public MyDataRepository_JPQL(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	// 【Crud】--------------------------------------------
	public void crate(MyData myData) {		
		
		entityManager.persist(myData);
		
	}
	
	// 【cRud】--------------------------------------------
	public MyData findByid(MyDataBean MyDataBean) {
		
		String qstr = "from MyData where id= :id";
		
		Query query = entityManager.createQuery(qstr).setParameter("id", MyDataBean.getId());
		
		MyData MyData = (MyData) query.getSingleResult();
		return MyData;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MyData> find_many(MyDataBean MyDataBean) {
		
		List<MyData> MyDataList = null;
		Map<String,String> parameterMap = new HashMap<String,String>();
		StringBuilder buf = new StringBuilder();
		int condCnt = 0;
		
		buf.append("from MyData");
		
		if (MyDataBean.getId() != null) {

			MyData MyData = findByid(MyDataBean);
			MyDataList = new ArrayList<>();
			MyDataList.add(MyData);
			
		} else {
			
			if (!StringUtils.isEmpty(MyDataBean.getName())) {
				buf.append(((condCnt++ == 0)?" where":" and"));
				buf.append(" name = :name");
				parameterMap.put("name", MyDataBean.getName());
			}
			
			if (!StringUtils.isEmpty(MyDataBean.getAge())) {
				buf.append(((condCnt++ == 0)?" where":" and"));
				buf.append(" age = :age");
				parameterMap.put("age", MyDataBean.getAge());
			}
			
			if (!StringUtils.isEmpty(MyDataBean.getMessage())) {
				buf.append(((condCnt++ == 0)?" where":" and"));
				buf.append(" message = :message");
				parameterMap.put("message", MyDataBean.getMessage());
			}
			
			Query query = entityManager.createQuery(buf.toString());
			
			if (parameterMap.size() > 0) {
				for(Map.Entry entry : parameterMap.entrySet()) {
					query.setParameter((String) entry.getKey(), entry.getValue());
				}
			}
													
			MyDataList  = query.getResultList();
			
		}
		
		return MyDataList;
		
	}
	
	// 【crUd】--------------------------------------------
	public void update(MyData MyData) {
		String qstr = "update MyData set name = :name, age = :age, message = :message where id= :id";
		
		// TODO:動的にupdateできるようにする
		
		Query query = entityManager.createQuery(qstr).setParameter("name", MyData.getName())
				.setParameter("age", MyData.getAge())
				.setParameter("message", MyData.getMessage())
				.setParameter("id", MyData.getId());
		
		query.executeUpdate();
	}
	
	// 【cruD】--------------------------------------------
	public void delete(MyData MyData) {
		String qstr = "delete from MyData where id= :id";
					
		Query query = entityManager.createQuery(qstr).setParameter("id", MyData.getId());
		query.executeUpdate();
	}

}
