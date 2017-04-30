package com.example.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.bean.MyDataBean;
import com.example.domain.MyData;

@Repository
public class SpecificationsDetail_MyData {
	
	@Autowired
	MyDataRepository MyDataRepository;
	
	public List<MyData> find_many(MyDataBean MyDataBean){
		
		return MyDataRepository.findAll(Specifications.where(idContains(MyDataBean.getId()))
															   .or(nameContains(MyDataBean.getName()))
															   .or(ageContains(MyDataBean.getAge()))
															   .or(messageContains(MyDataBean.getMessage())));
	}
	
	private Specification<MyData> idContains(Integer id) {
        return StringUtils.isEmpty(id) ? null : (root, query, cb) -> {
            return cb.equal(root.get("id"), id);
        };
    }

	private Specification<MyData> nameContains(String name) {
        return StringUtils.isEmpty(name) ? null : new Specification<MyData>() {
            @Override
            public Predicate toPredicate(Root<MyData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        };
    }
	
	private Specification<MyData> ageContains(String age) {
        return StringUtils.isEmpty(age) ? null : (root, query, cb) -> {
            return cb.equal(root.get("age"), age);
        };
    }
	
	private Specification<MyData> messageContains(String message) {
        return StringUtils.isEmpty(message) ? null : (root, query, cb) -> {
            return cb.equal(root.get("message"), message);
        };
    }


}
