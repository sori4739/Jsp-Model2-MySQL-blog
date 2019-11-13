package com.cos.action.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.GapContent;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.model.Comment;
import com.cos.util.Script;
import com.google.gson.Gson;

public class CommentWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			String content = request.getParameter("content");
			
			Comment comment = new Comment();
			comment.setUserId(userId);
			comment.setBoardId(boardId);
			comment.setContent(content);
			
			CommentDao dao = CommentDao.getInstance();
			int result = dao.save(comment);
			
			
			if(result == 1) {
				//Comment 테이블에 가장 마지막에 만들어진 Comment의 튜플 필요
				Comment cmt = dao.findByMaxId();
				cmt.getResponseData().setStatusCode(1);
				cmt.getResponseData().setStatus("ok");
				cmt.getResponseData().setStatusMessage("Write was completed");
				//Gson 이용해서 Json으로 변환
				Gson gson = new Gson();
				String commentJson = gson.toJson(cmt);
				
				System.out.println("(1)commentJson : "+commentJson);
				//데이터 응답
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(commentJson);
				out.flush();
				
			}else{
				Script.back(response);
			}
		}

	}
