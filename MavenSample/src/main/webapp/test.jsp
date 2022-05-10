<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*, java.io.*"%><%
	
	// 데이터베이스 연결
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
	con.setAutoCommit(false);
	
	PreparedStatement ps;
	ResultSet rs;
	
	StringBuffer buf = new StringBuffer();
	
	// 디비에서 읽는다.
	
	ps = con.prepareStatement("select img from test2");  
	rs = ps.executeQuery();
	if(rs.next())
	{
	InputStream is = rs.getBinaryStream(1);
	
	int c;
	while ((c = is.read()) != -1)
	buf.append((char)c);  
	}
	
	con.close();
	rs.close();
	ps.close();
	
	response.setContentType("image/jpeg");
	
	%><%=buf.toString()%>​
</body>
</html>