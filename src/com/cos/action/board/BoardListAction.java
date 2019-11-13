package com.cos.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.util.Utils;

public class BoardListAction implements Action {
	private final static String TAG = "BoardListAction : ";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("page")==null) return ; 
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		//page <= 혹은 page > maxNum 버튼 비활성화
		
		if(page <= 0) {
			page = 1;
		}
		
		BoardDao bDao = BoardDao.getInstance();
		List<Board> boards = null;
		List<Board> hotBoards = bDao.findOrderByReadCountDesc();
		int pageCount = 0;
		
		//serch와 목록을 분기
		if(request.getParameter("search")==null || request.getParameter("search").equals("")) {
			pageCount = bDao.findPageCount();
			boards = bDao.findAll(page);
			request.setAttribute("search", null);
		}else {
			String search = request.getParameter("search");
			pageCount = bDao.findPageCount(search);
			boards = bDao.findAll(page,search);//paging하기
			request.setAttribute("search", search);
		}
		
		Utils.setPreviewImg(boards);
		Utils.setPreviewContent(boards);
		Utils.setPreviewImg(hotBoards);
		
		
		request.setAttribute("boards", boards);
		request.setAttribute("hotBoards", hotBoards);
		request.setAttribute("pageCount", pageCount);
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/list.jsp");
		dis.forward(request, response);

	}

}
