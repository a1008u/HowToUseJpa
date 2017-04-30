package com.example.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

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
	// 【cRud】--------------------------------------------
	@SuppressWarnings("unchecked")
	public List<MyData> find_many(MyDataBean MyDataBean) {
		
		List<MyData> MyDataList = null;
		Map<String,String> parameterMap = new HashMap<String,String>();
		StringBuilder buf = new StringBuilder();
		int condCnt = 0;
		
		buf.append("from MyData");
		buf.append(" where");
		
		if (MyDataBean.getName() != null) {
			buf.append(((condCnt++ == 0)?"":" and"));
			buf.append(" name = :name");
			parameterMap.put("name", MyDataBean.getName());
		}
		
		if (!(MyDataBean.getAge() == null || "".equals(MyDataBean.getAge()))) {
			buf.append(((condCnt++ == 0)?"":" and"));
			buf.append(" age = :age");
			parameterMap.put("age", MyDataBean.getAge());
		}
		
		if (MyDataBean.getMessage() != null) {
			buf.append(((condCnt++ == 0)?"":" and"));
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
		
		return MyDataList;
		
	}
	
	// 【crUd】--------------------------------------------

	// 【cruD】--------------------------------------------
	public void delete(MyData MyData) {
		String qstr = "delete from MyData where id= :id";
					
		Query query = entityManager.createQuery(qstr).setParameter("id", MyData.getId());
		query.executeUpdate();
	}
}
