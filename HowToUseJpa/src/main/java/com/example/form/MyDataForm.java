package com.example.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.bean.MyDataBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyDataForm {
	
	// 画面上の情報を取得---------------
	private Integer id;
	
    private String name;
    
    private String age;
    
    private String message;
    
    // 画面上で表示するList---------------
    List<MyDataBean> mydatalist;
    
    @NotNull
    @Pattern(regexp = "Repository|JPQL|Criteria API")
    private String jpa;
    
    @NotNull
    @Pattern(regexp = "create|read|update|delete")
    private String sql;
    

}
