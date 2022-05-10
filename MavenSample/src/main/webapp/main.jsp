<%@page import="com.smhrd.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="join.jsp"><button>회원가입</button></a>

<a href="login.jsp"><button>로그인</button></a>
<%
	Member loginM = (Member)session.getAttribute("loginM");
	if(loginM!=null){
		out.print("<h1>"+loginM.getNick()+"</h1>");
	}
%>

</body>
</html>