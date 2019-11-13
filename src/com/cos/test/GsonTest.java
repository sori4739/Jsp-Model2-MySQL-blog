package com.cos.test;

import org.junit.Test;

import com.cos.model.Comment;
import com.google.gson.Gson;

public class GsonTest {
	
	@Test
	public void gsonTest1() {
		Comment comment = new Comment();
		comment.setId(1);
		comment.setUserId(5);
		comment.setBoardId(10);
		comment.setContent("댓글입니다.");
		comment.setCreateDate(null);
		comment.getResponseData().setStatus("ok");
		comment.getUser().setUsername("밀양손씨33대손");
		
		Gson gson = new Gson();
		String commentJson = gson.toJson(comment);
		System.out.println(commentJson);
	}
}
