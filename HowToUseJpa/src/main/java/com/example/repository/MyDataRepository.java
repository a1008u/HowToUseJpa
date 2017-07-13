package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domain.MyData;

public interface MyDataRepository  extends JpaRepository<MyData, Integer> , JpaSpecificationExecutor<MyData>{
	
	public MyData findByid(Integer id);
	
	public MyData findByname(String name);
	
	public MyData findByage(String age);
	
	public MyData findBymessage(String message);
	
	/* 実行しない(動的に条件を利用できるようにしないといけないため)
	@Query("SELECT d FROM MyData d WHERE d.name = :name and d.age = :age and d.message = :message")
	public List<MyData> find_and_many(@Param("name") String name, @Param("age") String age, @Param("message") String message);
	*/
	
	public List<MyData> findByNameLike(String Name);
	public List<MyData> findByIdIsNotNullOrderByIdDesc();
	public List<MyData> findByAgeGreaterThan(int age);
	public List<MyData> findByAgeBetween(int age1, int age2);
	
}
