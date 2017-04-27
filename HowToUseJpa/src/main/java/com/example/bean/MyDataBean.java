package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyDataBean {
    
	private Integer id;
	
    private String name;
    
    private int age;
    
    private String message;

}
