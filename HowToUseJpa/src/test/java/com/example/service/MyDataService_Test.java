package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bean.MyDataBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyDataService_Test {
	
	@Autowired
	MyDataService MyDataService;
	
	@Test
    public void Test_FindByid() throws Exception {
    	
    	// Set Up
		MyDataBean expected = new MyDataBean();
		expected.setId(1);
		expected.setName("Akira");
		expected.setAge(28);
		expected.setMessage("今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(1);
    	
    	// execute
    	MyDataBean actual = MyDataService.findByid(MyDataBean);
    	
    	assertThat(actual.getName(), is(expected.getName()));
    	assertThat(actual.getAge(), is(expected.getAge()));
    	assertThat(actual.getMessage(), is(expected.getMessage()));
    	
    }
	
    @Test
    public void Test_FindAll() throws Exception {
    	
    	// Set Up
    	MyDataBean expected = new MyDataBean();
    	expected.setName("Akira");
    	expected.setAge(20);
    	expected.setMessage("アイウエオ");
    	
    	// execute
    	List<MyDataBean> actual = MyDataService.find_All();
    	
    	assertThat(actual.size(), is(1));
    	
    }

}
