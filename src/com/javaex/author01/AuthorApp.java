package com.javaex.author01;

import java.util.List;

public class AuthorApp {
	public static void main(String[] args) {
		
		 
		 //���
		 AuthorDao authorDao = new AuthorDao();
		 AuthorVo authorVo1 = new AuthorVo("�̹���","��Ͽ���");
		 
		 authorDao.authorInsert(authorVo1);
		 //authorDao.authorInsert("�ڰ渮","��󳲵� �뿵");


		 //����Ʈ �޾ƿ���
		 List<AuthorVo>	authorList = authorDao.getAuthorList();
		 
		 //����Ʈ ��ü ���
		 System.out.println("---����Ʈ ���---");
		 for(int i = 0;i <authorList.size(); i++) {
			 AuthorVo vo = authorList.get(i);
			 System.out.println(vo.getAuthorId()+"."+vo.getAuthorName()+"," +vo.getAuthordesc());
		 }
		 
		 //����
		 //authorDao.authorDelete(7);
		 
		 
		 
		 //����
		 AuthorVo authorVo04 = new AuthorVo(2,"��渮","���ֵ�");
		 authorDao.authorUpdate(authorVo04);
		 
		//����Ʈ ��ü ���
		 System.out.println("---����Ʈ ���---");
		 for(int i = 0;i <authorList.size(); i++) {
			 AuthorVo vo = authorList.get(i);
			 System.out.println(vo.getAuthorId()+"."+vo.getAuthorName()+"," +vo.getAuthordesc());
		 }
	}
}
