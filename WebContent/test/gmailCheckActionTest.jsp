<%@page import="java.io.PrintWriter"%>
<%@page import="com.cos.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  	//code 값 받기
    String code = request.getParameter("code");
	
	//DB에서 id값으로 email과 salt 가져오기(가정) 
	
   	//return code 값이랑 send code 값을 비교해서 동일하면
    boolean rightCode = SHA256.getEncrypt("sori4739@naver.com", "cos").equals(code)? true:false;
	PrintWriter script = response.getWriter();
	if(rightCode){
		//DB에 칼럼 emailCheck(Number) 1 = 인증, 0 = 미인증 1을 update 해준다.
		script.println("<script>");
		script.println("alert('이메일 인증 성공')");
		script.println("location.href='blog/user/login.jsp'");
		script.println("history.back();");
		script.println("</script>");
		
	}else{
		script.println("<script>");
		script.println("alert('이메일 인증 오류')");
		script.println("location.href='blog/user/error.jsp'");
		script.println("history.back();");
		script.println("</script>");
		
	}
   			
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>