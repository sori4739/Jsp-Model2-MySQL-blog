package com.cos.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String userProfile; // 이미지 경로 (파일 업로드)
	private String username;
	private String password;
	private String email;
	private String address;
	private Timestamp createDate;

}
