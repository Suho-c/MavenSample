package com.smhrd.controller;
import sun.misc.*;
import java.sql.*;
import java.io.*;
public class test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			con.setAutoCommit(false);

			File file = new File("C:/Users/smhrd/Desktop/1.jpg");  
			FileInputStream fis = new FileInputStream(file);  

			PreparedStatement ps = con.prepareStatement("insert into test2 (img) values (?)");  
			ps.setBinaryStream(1, fis, (int)file.length());  
			ps.executeUpdate();  

			ps.close();  
			con.close();
			fis.close();  

			} catch(Exception e) {
			System.out.println(e.toString());
			}
	}

}
