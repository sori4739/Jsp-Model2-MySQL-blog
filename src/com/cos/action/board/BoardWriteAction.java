package com.cos.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.model.User;
import com.cos.util.Script;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Board board = new Board();
		board.setUserId(user.getId());
		board.setTitle(title);
		board.setContent(content);

		BoardDao dao = BoardDao.getInstance();
		int result = dao.save(board);
		if (result == 1) {
			response.sendRedirect("/blog/index.jsp");
		} else {
			Script.back(response);
		}

	}

}
