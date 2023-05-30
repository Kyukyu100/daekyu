package com.yedam.client;

public class Client {
//	client_id  varchar2(20) primary key,
//	client_pw varchar2(20),
//	client_name varchar2(20));
//	client_auth char
// 	gameScore number
	private String clientId;
	private String clientPw;
	private String clientName;
	private String clientAuth;
	private int gameScore;
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientPw() {
		return clientPw;
	}
	public void setClientPw(String clientPw) {
		this.clientPw = clientPw;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAuth() {
		return clientAuth;
	}
	public void setClientAuth(String clientAuth) {
		this.clientAuth = clientAuth;
	}
	public int getGameScore() {
		return gameScore;
	}
	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}
	
	
	
}
