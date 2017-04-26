package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.MyData;

public interface MyDataRepository  extends JpaRepository<MyData, Integer> {

}
