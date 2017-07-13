package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.NoResultException;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bean.MyDataBean;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class MyDataService_CriteriaAPI_Test {
	
	@Autowired
	MyDataService_CriteriaAPI MyDataServiceCriteriaAPI;
	
	// 【Crud】--------------------------------------------
	@Test
	public void Test1_Create() throws Exception {
		
    	// Set Up
		int id = 4;
		MyDataBean expected = new MyDataBean();
		expected.setName("testuser");
		expected.setAge("20");
		expected.setMessage("testuserは食べないよ");
    	
    	// execute
    	MyDataServiceCriteriaAPI.create(expected);
    	
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(id);
    	MyDataBean actual = MyDataServiceCriteriaAPI.findByid(MyDataBean);
    	expected.setId(id);
    	
    	// VerifyMyDataService_JPQL
    	assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
    	
    }
	
	// 【cRud】--------------------------------------------
    @Test
    public void Test2_1_Find_many() throws Exception {
    	
    	// Set Up
    	MyDataBean expected = new MyDataBean(1,"Akira","28","今日食べた定食は、ラーメンチャーハン餃子セット");
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceCriteriaAPI.find_many(expected);
    	
    	// Verify
    	assertThat(actual.size(), is(1));
    	
    }
    
    @Test
    public void Test2_2_Find_many() throws Exception {
    	
    	// Set Up
    	MyDataBean expected = new MyDataBean(1,"Akira","","今日食べた定食は、ラーメンチャーハン餃子セット");
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceCriteriaAPI.find_many(expected);
    	
    	// Verify
    	assertThat(actual.size(), is(1));
    	
    }
    
    @Test
    public void Test2_3_Find_many() throws Exception {
    	
    	// Set Up
    	MyDataBean expected = new MyDataBean(1,null,"","今日食べた定食は、ラーメンチャーハン餃子セット");
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceCriteriaAPI.find_many(expected);
    	
    	// Verify
    	assertThat(actual.size(), is(1));
    	
    }
    
    // 【crUd】--------------------------------------------
    @Test
    public void Test3_1_Update() throws Exception {
    	
    	// Set Up
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(1);
    	MyDataBean expectedMyDataBean = MyDataServiceCriteriaAPI.findByid(MyDataBean);
    	expectedMyDataBean.setName("Akira");
    	expectedMyDataBean.setAge("28");
    	expectedMyDataBean.setMessage("今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	// execute
		MyDataServiceCriteriaAPI.update(expectedMyDataBean);
    	
		MyDataBean MyDataBean2 = new MyDataBean();
		MyDataBean2.setId(1);
    	MyDataBean actualMyDataBean = MyDataServiceCriteriaAPI.findByid(MyDataBean2);
		
    	// Verify
    	assertThat(actualMyDataBean, is(SamePropertyValuesAs.samePropertyValuesAs(expectedMyDataBean)));
    	
    }

    // 【cruD】--------------------------------------------
    @Test
    public void Test4_1_delete() throws Exception {
    	
    	// Set Up
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setName("Akira");

    	// execute
    	MyDataServiceCriteriaAPI.delete(MyDataBean);
    	
		MyDataBean MyDataBean2 = new MyDataBean();
		MyDataBean2.setName("Akira");
		List<MyDataBean> actual = MyDataServiceCriteriaAPI.find_many(MyDataBean2);
		
    	// Verify
    	assertThat(actual.size(), is(0));
    	
    }
    
    @Test(expected = NoResultException.class)
    public void Test4_2_delete() throws Exception {
    	
    	// Set Up
    	
    	Integer id= 2;
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(id);
    	MyDataBean = MyDataServiceCriteriaAPI.findByid(MyDataBean);
    	MyDataServiceCriteriaAPI.delete(MyDataBean);
    	
    	// execute
    	MyDataBean actual = new MyDataBean();
    	actual.setId(id);
    	
    	// ここでentityを取得ができない為、エラーとなる
    	actual = MyDataServiceCriteriaAPI.findByid(actual);
    	
    	// Verify
    	assertThat(actual, is(nullValue()));
    	
    }

}
