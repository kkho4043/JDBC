package com.javaex.book02;

public class AuthorVo {
	//필드
	public int authorId;
	public String authorName;
	public String authordesc;
	//생성자 
	public AuthorVo() {}
	
	public AuthorVo(String authorName, String authordesc) {	
		this.authorName = authorName;
		this.authordesc = authordesc;
	}
	public AuthorVo(int authorId, String authorName, String authordesc) {
		
		this.authorId = authorId;
		this.authorName = authorName;
		this.authordesc = authordesc;
	}

	//메소드 getset
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthordesc() {
		return authordesc;
	}
	public void setAuthordesc(String authordesc) {
		this.authordesc = authordesc;
	}
	
	@Override
	public String toString() {
		return "AuthorVo [authorId=" + authorId + ", authorName=" + authorName + ", authordesc=" + authordesc + "]";
	}
	
	
	//메소드 일반
	
	
	
	
	
}
