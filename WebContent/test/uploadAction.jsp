<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String path = application.getRealPath("media");
	
	MultipartRequest multi = new MultipartRequest(
			request,
			path,
			1024*1024*2,
			"UTF-8",
			new DefaultFileRenamePolicy()
			);
	String username = multi.getParameter("username");
	String filename = multi.getFilesystemName("userProfile");//정책에 의해서 변경된 이름
	String originFilename = multi.getOriginalFileName("userProfile");
	String contextPath = getServletContext().getContextPath();
	String downloadPath = getServletContext().getRealPath("media");
	String filepath = contextPath+"/media/"+filename;
%>

path : <%= path %> <br/>
username : <%= username %> <br/>
filename : <%= filename %> <br/>
originFilename : <%= originFilename %> <br/>
contextPath : <%= contextPath %><br/>
downloadPath :	<%= downloadPath %><br/>

<img src = <%= filepath %> width = "300px" height="300px" />

</body>
</html>