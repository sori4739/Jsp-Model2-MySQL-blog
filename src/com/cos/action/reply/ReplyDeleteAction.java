package com.cos.action.reply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.dao.ReplyDao;
import com.cos.util.Script;

public class ReplyDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader in = request.getReader();
		int replyId = Integer.parseInt(in.readLine());

		System.out.println("replyId : " + replyId);

		ReplyDao dao = ReplyDao.getInstance();
		int result = dao.delete(replyId);

		if (result == 1) {
			PrintWriter out = response.getWriter();
			out.print("ok");// println을 쓰면 \n이 함께 날라간다 ex) ok\n
			out.flush();
		} else {
			Script.back(response);
		}

	}

}
