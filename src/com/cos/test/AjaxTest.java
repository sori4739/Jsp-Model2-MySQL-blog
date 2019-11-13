package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.model.Comment;
import com.google.gson.Gson;


@WebServlet("/test")
public class AjaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
    public AjaxTest() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8"); //MIME 타입
		
		//요청 데이터 처리
		BufferedReader in = request.getReader();
		
		String replyJsonString = in.readLine();
		System.out.println("요청 데이터 : "+replyJsonString);
		
		Gson gson = new Gson();
		//파싱할 때 null 값 오류뜨는지 테스트
		Comment reply = gson.fromJson(replyJsonString, Comment.class);
		
		System.out.println("id : "+reply.getId());
		System.out.println("boardId : "+reply.getBoardId());
		System.out.println("userId : "+reply.getUserId());
		System.out.println("content : "+reply.getContent());
		System.out.println("createDate : "+reply.getCreateDate());
		//응답 데이터 처리(2)
		String jsonDate = "{\"name\" : \"소농민\",\"sal\" : 100}";
		PrintWriter out = response.getWriter();
		out.println(jsonDate);
		out.flush();
	 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
