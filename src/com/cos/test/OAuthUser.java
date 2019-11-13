package com.cos.test;

import com.cos.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthUser {
	private int id;
	
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expire_in;
	
	private User user = new User();
}
