package com.javaex.author01;

import java.util.List;

public class AuthorApp {
	public static void main(String[] args) {
		
		 
		 //등록
		 AuthorDao authorDao = new AuthorDao();
		 AuthorVo authorVo1 = new AuthorVo("이문열","경북영양");
		 
		 authorDao.authorInsert(authorVo1);
		 //authorDao.authorInsert("박경리","경상남도 통영");


		 //리스트 받아오기
		 List<AuthorVo>	authorList = authorDao.getAuthorList();
		 
		 //리스트 전체 출력
		 System.out.println("---리스트 출력---");
		 for(int i = 0;i <authorList.size(); i++) {
			 AuthorVo vo = authorList.get(i);
			 System.out.println(vo.getAuthorId()+"."+vo.getAuthorName()+"," +vo.getAuthordesc());
		 }
		 
		 //삭제
		 //authorDao.authorDelete(7);
		 
		 
		 
		 //수정
		 AuthorVo authorVo04 = new AuthorVo(2,"김경리","제주도");
		 authorDao.authorUpdate(authorVo04);
		 
		//리스트 전체 출력
		 System.out.println("---리스트 출력---");
		 for(int i = 0;i <authorList.size(); i++) {
			 AuthorVo vo = authorList.get(i);
			 System.out.println(vo.getAuthorId()+"."+vo.getAuthorName()+"," +vo.getAuthordesc());
		 }
	}
}
