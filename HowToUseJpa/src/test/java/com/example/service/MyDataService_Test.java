package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

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
public class MyDataService_Test {
	
	@Autowired
	MyDataService_repository MyDataServiceRepository;
	
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
    	MyDataServiceRepository.create(expected);
    	
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(id);
    	MyDataBean actual = MyDataServiceRepository.findByid(MyDataBean);
    	expected.setId(id);
    	
    	// Verify
    	assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
    	
    }
	
	// 【cRud】--------------------------------------------
	@Test
    public void Test2_1_FindByid() throws Exception {
    	
    	// Set Up
		MyDataBean expected = new MyDataBean(1,"Akira","28","今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(1);
    	
    	// execute
    	MyDataBean actual = MyDataServiceRepository.findByid(MyDataBean);
    	
    	// Verify
    	assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
    }
	
	@Test
    public void Test2_1_FindByname() throws Exception {
    	
    	// Set Up
		MyDataBean expected = new MyDataBean(1,"Akira","28","今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setName("Akira");
    	
    	// execute
    	MyDataBean actual = MyDataServiceRepository.findByname(MyDataBean);
    	
    	// Verify
    	assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
    }
	
	@Test
    public void Test2_1_FindByage() throws Exception {
    	
    	// Set Up
		MyDataBean expected = new MyDataBean(1,"Akira","28","今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setAge("28");
    	
    	// execute
    	MyDataBean actual = MyDataServiceRepository.findByage(MyDataBean);
    	
    	// Verify
    	assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
    }
	
	@Test
    public void Test2_1_FindBymessage() throws Exception {
    	
    	// Set Up
		MyDataBean expected = new MyDataBean(1,"Akira","28","今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setMessage("今日食べた定食は、ラーメンチャーハン餃子セット");
    	
    	// execute
    	MyDataBean actual = MyDataServiceRepository.findBymessage(MyDataBean);
    	
    	// Verify
    	assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
    }
	
    @Test
    public void Test2_2_FindAll() throws Exception {
    	
    	// Set Up
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceRepository.find_All();
    	
    	// Verify
    	assertThat(actual.size(), is(4));
    	
    }
    
    @Test
    public void Test2_3_Find_many() throws Exception {
    	
    	// Set Up
    	MyDataBean expected = new MyDataBean();
		// expected.setId(1);
		expected.setName("AAA");
		expected.setAge("26");
		// expected.setMessage("今日食べた定食は、ラーメンチャーハン餃子セット");
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceRepository.find_many(expected);
    	
    	// Verify
    	assertThat(actual.size(), is(1));
    	
    }
    
    @Test
    public void Test2_4_Find_and_many() throws Exception {
    	
    	// Set Up
    	MyDataBean expected = new MyDataBean();
		// expected.setId(1);
	    expected.setName("AAA");
		// expected.setAge(26);
		expected.setMessage("今日食べた定食は、お子様ランチセット");
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceRepository.find_and_many(expected);
    	
    	// Verify
    	assertThat(actual.size(), is(1));
    	
    }
    
    // 【crUd】--------------------------------------------
    @Test
    public void Test3_1_Update() throws Exception {
    	
    	// Set Up
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(1);
    	MyDataBean expectedMyDataBean = MyDataServiceRepository.findByid(MyDataBean);
    	expectedMyDataBean.setName("Akira");
    	expectedMyDataBean.setAge("28");
    	expectedMyDataBean.setMessage("今日食べた定食は、ラーメンチャーハン餃子セット");
		
    	// execute
		MyDataServiceRepository.update(expectedMyDataBean);
    	
		MyDataBean MyDataBean2 = new MyDataBean();
		MyDataBean2.setId(1);
    	MyDataBean actualMyDataBean = MyDataServiceRepository.findByid(MyDataBean2);
		
    	// Verify
    	assertThat(actualMyDataBean, is(SamePropertyValuesAs.samePropertyValuesAs(expectedMyDataBean)));
    	
    }
    
    // 【cruD】--------------------------------------------
    @Test
    public void Test4_1_delete() throws Exception {
    	
    	// Set Up
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(1);

    	// execute
    	MyDataServiceRepository.delete(MyDataBean);
    	
		MyDataBean MyDataBean2 = new MyDataBean();
		MyDataBean2.setId(1);
    	MyDataBean actualMyDataBean = MyDataServiceRepository.findByid(MyDataBean2);
		
    	// Verify
    	assertThat(actualMyDataBean.getName(), nullValue());
    	
    }
    
    @Test
    public void Test4_2_delete_find() throws Exception {
    	
    	// Set Up
    	MyDataBean MyDataBean = new MyDataBean();
    	MyDataBean.setId(1);
    	MyDataServiceRepository.delete(MyDataBean);
    	
    	// execute
    	List<MyDataBean> actual = MyDataServiceRepository.find_All();
    	
    	// Verify
    	assertThat(actual.size(), is(3));
    	
    }

}
