package com.yedam.client;


import java.util.List;
import java.util.Scanner;

public class ClientService {
	
	Scanner sc = new Scanner(System.in);
	public static Client clientInfo = null;
	
	
	// 2. 회원 가입
	public void insertClient() {
		System.out.println("===== 회원가입 =====");
		Client client = new Client();
		
		System.out.println("ID 입력>");
		client.setClientId(sc.nextLine());
		
		System.out.println("PW 입력>");
		client.setClientPw(sc.nextLine());
		
		System.out.println("NAME 입력>");
		client.setClientName(sc.nextLine());
		int result = ClientDAO.getInstance().insertClient(client);
		if(result > 0) {
			System.out.println("회원 가입 성공!");
		}else {
			System.out.println("회원 가입 실패ㅠ");
		}
	}
	// 1. 로그인 
		public void login() {
			System.out.println("===== login =====");
			System.out.println("ID 입력>");
			String id = sc.nextLine();
			System.out.println("PW 입력>");
			String pw = sc.nextLine();
			Client client = ClientDAO.getInstance().login(id);
			if(client != null) {
				if(client.getClientPw().equals(pw)) {
					System.out.println("로그인 성공!");
					clientInfo = client;
				}else {
					System.out.println("비밀번호 불일치!");
				}
			}else {
				System.out.println("ID 불일치!");
			}
		}
	// 전체조회
		public void getClientList() {
			List<Client> list = ClientDAO.getInstance().getClientList();
			for(Client client : list) {
				System.out.println("==================================");
				System.out.println("회원 ID : " + client.getClientId());
				System.out.println("회원 PW : " + client.getClientPw());
				System.out.println("회원 NAME : " + client.getClientName());
				System.out.println("회원 AUTH : " + (client.getClientAuth().equals("N") ? "일반사용자" : "관리자"));
			}
		}
		// 내 정보 보기 
		public void getClientInfo() {
			System.out.println("내 정보 조회");
			Client cl = new Client();
			cl = ClientDAO.getInstance().getClient(ClientService.clientInfo.getClientId());
			if(cl != null) {
				System.out.println("회원 ID : " + cl.getClientId());
				System.out.println("회원 PW : " + cl.getClientPw());
				System.out.println("회원 NAME : " + cl.getClientName());
				System.out.println("회원 등급 : " + (cl.getClientAuth().equals("N") ? "일반 사용자" : "관리자"));
			}
		}
		// 비밀번호 수정
		public void updatePw() {
			System.out.println("비밀번호 변경");
			int result = 0;
			System.out.println("기존 비밀번호 입력>");
			String oldPw = sc.nextLine();
			
			if(ClientService.clientInfo.getClientPw().equals(oldPw)) {
				System.out.println("확인 되었습니다.");
				System.out.println("새로운 비밀번호 입력 ");
				String newPw = sc.nextLine();
				result = ClientDAO.getInstance().updatePw(clientInfo.getClientId(),newPw);
				if(result > 0) {
					System.out.println("비밀번호 변경 완료!");
					clientInfo.setClientPw(newPw); 
				}else {
					System.out.println("비밀번호 변경 실패ㅠ");
				}
			}else{
				System.out.println("기존 비밀번호가 다릅니다.");
			}	
		}
		
		
		
		
		// 회원 삭제
	public void deleteClient() {
		System.out.println("회원 삭제");
		int result = 0;
		System.out.println("기존 비밀번호 입력>");
		String oldPw = sc.nextLine();
		
		if(ClientService.clientInfo.getClientPw().equals(oldPw)) {
			System.out.println("확인 되었습니다.");
			System.out.println("회원 탈퇴 하시겠습니까?");
			System.out.println("1. 예 | 2. 아니요 ");
			int really = Integer.parseInt(sc.nextLine());
			if(really == 1) {
				result = ClientDAO.getInstance().deleteClient(ClientService.clientInfo.getClientId());
				if(result > 0) {
					System.out.println("회원 탈퇴 완료");
					ClientService.clientInfo = null;
				}else {
					System.out.println("회원 탈퇴 실패 ㅠ");
				}
			}
		}else {
			System.out.println("비밀번호가 틀립니다.");
		}
	}
}
