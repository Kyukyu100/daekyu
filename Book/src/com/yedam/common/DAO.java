package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	// DAO -> Data(DB) Access Object
	// JDBC를 통해서 JAVA <-> DB가 연결이 된다.
	// protected인 이유 -> 다른 DAO에 부모가 되기 때문에
	// java -> DB 연결할 때 쓰는 객체
	protected Connection conn = null;
	
	// Query문(SQL, 질의)을 가지고 실행하는 객체
	
	protected PreparedStatement pstmt = null;
	
	// Query문(SQL, 질의)을 가지고 실행하는 객체
	protected Statement stmt = null;
	
	// SELECT(조회) 결과 값을 반환받는 객체
	protected ResultSet rs = null;
	
	// DB 접속 정보
	// 인사관리 -> 속성 -> 인증유형 1.사용자이름 2. 비밀번호
	// ****** 접속유형이 더 중요 -> 호스트이름(IP) ************ 
	// 포트(PW) : 1521 -> Oracle서버를 뜻하는 포트번호 
	// SID : DB이름 
	// localhost = 127.0.0.1
	// diver의 문자열이 JDBC
	String driver = "oracle.jdbc.driver.OracleDriver";
	// @localhost -> ip / 1521 -> port / xe -> 서버이름
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "hr";
	String pw = "1234";
	
	// JDBC 연결법 Oracle jar파일 꺼내오고 java project(company) -> Bulid Path -> 
	
	// DB 연결 메소드
	public void conn() {
		try {
			// 드라이버 로딩 
			Class.forName(driver);
			// DB연결
			conn = DriverManager.getConnection(url,id,pw);
			} catch(Exception e) {
				e.printStackTrace();
		}
	}
	
	// DB 연결 해제
	// 연결순서의 반대
	// (1.conn 2.sql실행 3. 결과 4. 종료) 의 역순으로 
	// 연결 해제가 필요한 이유 : DB의 접속자 수는 제한되어 있기 때문에
	// DB 조회, 삭제 , 추가, 수정을 종료시켜주지 않으면 계속 실행되어있기 때문에
	// 접속자 수가 제한된다.
	public void disconn() {
		try {
			// else if안쓰는 이유 -> else if는 위의 if나 else if문중에 하나가 조건이 맞아서 실행되면
			// 뒤의 조건문은 실행이 안되기 때문
			// != null : 사용한적 있다면 닫는다. 
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
