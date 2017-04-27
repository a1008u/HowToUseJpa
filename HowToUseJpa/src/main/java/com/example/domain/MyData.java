package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mydata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyData {
	
	@Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private int age;
    
    @Column
    private String message;

}
