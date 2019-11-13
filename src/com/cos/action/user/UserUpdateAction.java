package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String password = SHA256.getEncrypt(rawPassword, "cos");
		String address = request.getParameter("address");
		
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);//Encryption(암호화)해야됨
		user.setEmail(email);
		user.setAddress(address);
		
		UserDao dao = UserDao.getInstance();
		int result = dao.update(user);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		}else {
			Script.back(response);
		}

	}

}
