package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.MyData;

public interface MyDataRepository  extends JpaRepository<MyData, Integer> {
	
	public MyData findByid(Integer id);
	
	public List<MyData> findByNameLike(String Name);
	public List<MyData> findByIdIsNotNullOrderByIdDesc();
	public List<MyData> findByAgeGreaterThan(int age);
	public List<MyData> findByAgeBetween(int age1, int age2);
	
}
