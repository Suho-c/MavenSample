package com.smhrd.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JDBC {
	//전역변수 공간
		Connection conn=null;
		PreparedStatement psmt =null;
		ResultSet rs=null;
		String sql = "";
		int cnt = 0;
		Member m_vo=null;
		Member selectDTO=null;
		int cnt2=0;
		int cnt3=0;
		//DB 연결하는 메소드
		public void getConn() {
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//3. DB 경로, ID/PW 인증
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
				String dbid = "hr";
				String dbpw ="hr";
				conn = DriverManager.getConnection(url, dbid, dbpw);
				if(conn!=null){
					System.out.print("연결성공");
				}
				else{
					System.out.print("연결실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//-- getConn 끝
		//DB 연결 종료하는 메소드
		public void getclose() {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}//-- getclose 끝
		
		//회원가입기능
		public int join(Member m) {
			try {
				//db 연결
				getConn();
				sql= "insert into member_web values(?,?,?)";
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getPw());
				psmt.setString(3, m.getNick());
				//5. sql 명령문 실행
				//executeQuery() -> select문 -테이블 변하지 않는 경우
				//executeUpdate() -> insert, update, delete - 테이블 구조 변화가 있는 경우
				cnt = psmt.executeUpdate();//실행 성공한 row값 int 형태로 반환
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//db연결 종료
				getclose();
			}
			return cnt;
		}// ------join 끝
		
		public Member login(Member m) {
			
			try{
				getConn();
				//4. sql 명령문 준비
				//? : 바인드변수 -> 사용자의 값을 sql로 전달할 수 있는 통로 역할
				String sql= "select * from member_web where id = ? and pw=?";
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getPw());
				//5. sql 명령문 실행
				//executeQuery() -> select문 -테이블 변하지 않는 경우
				//executeUpdate() -> insert, update, delete - 테이블 구조 변화가 있는 경우
				rs = psmt.executeQuery();
				//rs.next() 커서를 한칸 움직인후 커서 가리키는 행에 값이 있는지 없는지 판다
				//반환타입 : boolean
				if(rs.next()){
					String uid=rs.getString("id");
					String upw=rs.getString("pw");
					String unick=rs.getString("nick");
					m_vo =new Member(uid,upw,unick);
					
				}
				else{
					System.out.print("로그인 실패했습니다!!");
					//out.print("<a href='login.html'>로그인 다시하기</a>");
					
				}
					
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				//finally문은 무조건 실행 된다!
				//6. 연결 종료
				getclose();
			}return m_vo;
		}

}
