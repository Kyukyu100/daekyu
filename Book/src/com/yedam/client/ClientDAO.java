package com.yedam.client;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class ClientDAO extends DAO{
	
	
	private static ClientDAO clientDao = null; 
	
	private ClientDAO() {
		
	}
	public static ClientDAO getInstance() {
		if(clientDao == null) {
			clientDao = new ClientDAO();
		}
		return clientDao;
	}
	
	
	// 로그인 
	public Client login(String id) {
		Client client = null;
		try {
			conn();
			String sql = "SELECT * FROM client WHERE client_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				client = new Client();
				client.setClientId(rs.getString("client_id"));
				client.setClientPw(rs.getString("client_pw"));
				client.setClientName(rs.getString("client_name"));
				client.setClientAuth(rs.getString("client_auth"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return client;
	}
	
	// 회원 가입 
	public int insertClient(Client client) {
		int result = 0;
		try {
			conn();
			// 프로그램에서 생성시 일반사용자만 등록되게
			String sql = "INSERT INTO client VALUES(?,?,?,'N',0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, client.getClientId());
			pstmt.setString(2, client.getClientPw());
			pstmt.setString(3, client.getClientName());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	// 회원전체보기
	public List<Client> getClientList(){
		List<Client> list = new ArrayList<>();
		Client client = null;
		try {
			conn();
			String sql = "SELECT * FROM client";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()){
				client = new Client();
				client.setClientId(rs.getString("client_id"));
				client.setClientPw(rs.getString("client_pw"));
				client.setClientName(rs.getString("client_name"));
				client.setClientAuth(rs.getString("client_auth"));
				client.setGameScore(rs.getInt("game_score"));
				list.add(client);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	
	// 내 정보 보기
	public Client getClient(String id) {
		Client client = null;
		try {
			conn();
			String sql = "SELECT * FROM client WHERE client_id = ?";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				client = new Client();
				client.setClientId(rs.getString("client_id"));
				client.setClientPw(rs.getString("client_pw"));
				client.setClientName(rs.getString("client_name"));
				client.setClientAuth(rs.getString("client_auth"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return client;
	}
	
	// 회원 수정(사용자용)(비밀번호)
	public int updatePw(String id, String newPw) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE client SET client_pw = ? WHERE client_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, id);
			pstmt.setString(1, newPw);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	// 회원 수정(관리자용) 
	
	// 회원 삭제
	public int deleteClient(String id) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE client WHERE client_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
}
