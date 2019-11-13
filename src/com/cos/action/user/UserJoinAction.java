package com.cos.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserJoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아이디
		if(request.getParameter("username")==null || request.getParameter("username").equals("")) {
			return ;
		}
		
		//비밀번호
		if(request.getParameter("password")==null || request.getParameter("password").equals("")) {
			return ;
		}
		
		//이메일
		if(request.getParameter("email")==null || request.getParameter("email").equals("")) {
			return ;
		}
		
		
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String password = SHA256.getEncrypt(rawPassword, "cos");
		String address = request.getParameter("address");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);//Encryption(암호화)해야됨
		user.setEmail(email);
		user.setAddress(address);
		
		UserDao dao = UserDao.getInstance();
		int result = dao.save(user);
		
		if(result == 1) {
			request.setAttribute("email", email);
			RequestDispatcher dis = request.getRequestDispatcher("/user/gmailSendAction.jsp");
			dis.forward(request, response);

		}else {
			Script.back(response);
		}

	}

}
