package com.cos.action.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.dao.CommentDao;
import com.cos.util.Script;

public class CommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			BufferedReader in = request.getReader();
			String temp = in.readLine();
			int commentId = Integer.parseInt(temp);
			
			System.out.println("commentId : "+commentId);
			
			CommentDao dao = CommentDao.getInstance();
			int result = dao.delete(commentId);
			
			if(result == 1) {
				PrintWriter out = response.getWriter();
				out.print("ok");//println을 쓰면 \n이 함께 날라간다 ex) ok\n
				out.flush();
			}else {
				PrintWriter out = response.getWriter();
				out.print("imposible");
				out.flush();
			}

	}

}
