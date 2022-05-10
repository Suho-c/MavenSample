package com.smhrd.controller;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        
	        try {
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
	                    "hr","hr");
	            
	            File f = new File("C:/Users/smhrd/Desktop/1.jpg");    
	            FileInputStream fis = new FileInputStream(f);
	            
	            stmt = con.prepareStatement(
	                    "insert into test4 values(?,?)");
	            stmt.setString(1, "2.png");
	            stmt.setBinaryStream(2, fis,(int)f.length());
	            int rownum = stmt.executeUpdate();
	            
	            if(rownum >0){
	                System.out.println("삽입성공");
	            }else
	            {
	                System.out.println("실패");
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	                //사용한 객체 close
	                try {
	                    if(con != null) con.close();
	                    if(stmt != null) stmt.close();
	                } catch (Exception e) {
	                    
	                }
	            
	        }
	

	}

}
