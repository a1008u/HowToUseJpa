package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;

@Repository
public class MyDataRepository_CriteriaAPI{
	
	private EntityManager entityManager;
	
	public MyDataRepository_CriteriaAPI(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	// 【Crud】--------------------------------------------
	public void create(MyData MyData) {		
		
		// CriteriaAPIにInsertはないのでJPQLを利用
		entityManager.persist(MyData);
		
	}
	
	// 【cRud】--------------------------------------------
	public MyData findByid(MyDataBean MyDataBean) {
		
		// Set Up
		CriteriaBuilder CB = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = CB.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).where(CB.equal(root.get("id"), MyDataBean.getId()));
		
		// Execute
		MyData MyData = entityManager.createQuery(query).getSingleResult();
		
		return MyData;
	}
	
	public List<MyData> find_All() {
		
		// Set Up
		CriteriaBuilder CB = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = CB.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root);
		
		List<MyData> MyDataList = entityManager.createQuery(query).getResultList();
		
		return MyDataList;
	}
	
	// TODO:メタモデルジェネレータで定義
	public List<MyData> find_many(MyDataBean MyDataBean) {
		
		CriteriaBuilder CB = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = CB.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root);
		
		List<Predicate> creteria = new ArrayList<Predicate>();
		
		if (!StringUtils.isEmpty(MyDataBean.getId())) {

			creteria.add(CB.equal(root.get("id"), MyDataBean.getId()));
			
		} else {
			
			if (!StringUtils.isEmpty(MyDataBean.getName())) {
				creteria.add(CB.equal(root.get("name"), MyDataBean.getName()));
			}
			
			if (!StringUtils.isEmpty(MyDataBean.getAge())) {
				creteria.add(CB.equal(root.get("age"), MyDataBean.getAge()));
			}
			
			if (!StringUtils.isEmpty(MyDataBean.getMessage())) {
				creteria.add(CB.equal(root.get("message"), MyDataBean.getMessage()));
			}
		}
		
		if (creteria.size() > 0) {
	        query.where(CB.and(creteria.toArray(new Predicate[]{})));
	    }
		
		List<MyData> MyDataList = entityManager.createQuery(query).getResultList();
		
		return MyDataList;
		
	}
	
	// 【crUd】--------------------------------------------
	public void update(MyData MyData) {
		
		// Set Up
		CriteriaBuilder CB = entityManager.getCriteriaBuilder();
		CriteriaUpdate<MyData> update = CB.createCriteriaUpdate(MyData.class);
		Root<MyData> root = update.from(MyData.class);
		
		update.set("name", MyData.getName())
			  .set("age", MyData.getAge())
			  .set("message", MyData.getMessage())
			  .where(CB.equal(root.get("id"), MyData.getId()));
		
		// Execute
		entityManager.createQuery(update).executeUpdate();
	}
	
	// 【cruD】--------------------------------------------
	public void delete(MyData MyData) {
		
		// Set Up
		CriteriaBuilder CB = entityManager.getCriteriaBuilder();
		CriteriaDelete<MyData> delete = CB.createCriteriaDelete(MyData.class);
		Root<MyData> root = delete.from(MyData.class);
		
		delete.where(CB.equal(root.get("id"), MyData.getId()));
		
		// Execute
		entityManager.createQuery(delete).executeUpdate();
	}

}
