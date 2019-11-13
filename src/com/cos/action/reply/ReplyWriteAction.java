package com.cos.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.dao.ReplyDao;
import com.cos.model.Comment;
import com.cos.model.Reply;
import com.cos.util.Script;
import com.google.gson.Gson;

public class ReplyWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("content");
		
		Reply reply = new Reply();
		reply.setUserId(userId);
		reply.setCommentId(commentId);
		reply.setContent(content);
		
		ReplyDao dao = ReplyDao.getInstance();
		int result = dao.save(reply);
		
		if(result == 1) {
			System.out.println("-----");
			Reply rply = dao.findByMaxId();
			rply.getResponseData().setStatusCode(1);
			rply.getResponseData().setStatus("ok");
			rply.getResponseData().setStatusMessage("Write was completed");
			
			System.out.println("reply.comment_id : "+reply.getCommentId());
			Gson gson = new Gson();
			String rplyJson = gson.toJson(rply);
			System.out.println(rplyJson);
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(rplyJson);
			out.flush();
		}else {
			Script.back(response);
		}

	}

}
