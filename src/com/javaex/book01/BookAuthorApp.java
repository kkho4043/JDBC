package com.javaex.book01;

import java.util.List;
import java.util.Scanner;

public class BookAuthorApp {
	public static void main(String[] args) {
		// �۰� ��� 6��.(author)
		// AuthorDao, AuthorVo �̿��ؼ� ���.
		AuthorDao authorDao = new AuthorDao();

		AuthorVo authorVo1 = new AuthorVo("�蹮��", "��Ͽ���");
		authorDao.authorInsert(authorVo1);

		AuthorVo authorVo2 = new AuthorVo("�ڰ渮", "��󳲵� �뿵");
		authorDao.authorInsert(authorVo2);

		AuthorVo authorVo3 = new AuthorVo("���ù�", "17�뱹ȸ�ǿ�");
		authorDao.authorInsert(authorVo3);

		AuthorVo authorVo4 = new AuthorVo("���84", "��ȵ����� �� 84���");
		authorDao.authorInsert(authorVo4);

		AuthorVo authorVo5 = new AuthorVo("��Ǯ", "�¶��θ�ȭ�� 1����");
		authorDao.authorInsert(authorVo5);

		AuthorVo authorVo6 = new AuthorVo("�迵��", "�˾�����");
		authorDao.authorInsert(authorVo6);

		AuthorVo authorVo7 = new AuthorVo("������", "����������");
		authorDao.authorInsert(authorVo7);

		// ����Ʈ �޾ƿ���
		List<AuthorVo> authorList;
		// ����Ʈ
		authorList = authorDao.getAuthorList();
		System.out.println("---author����Ʈ ���---");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "." + vo.getAuthorName() + "," + vo.getAuthordesc());
		}

		// ����(author)
		AuthorVo authorVo0 = new AuthorVo(7, "������", "����������");
		authorDao.authorUpdate(authorVo0);

		// ����Ʈ(author)
		authorList = authorDao.getAuthorList();
		System.out.println("---������ authour ����Ʈ ���---");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "." + vo.getAuthorName() + "," + vo.getAuthordesc());
		}

		// ����(author)
		authorDao.authorDelete(7);

		// ����Ʈ(author)
		authorList = authorDao.getAuthorList();
		System.out.println("---������ authour ����Ʈ ���---");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "." + vo.getAuthorName() + "," + vo.getAuthordesc());
		}

		// =========================================================================
		// å8�ǵ��
		// bookdao bookvo �̿�
		BookDao bookDao = new BookDao();

		BookVo bookVo1 = new BookVo("�츮���� �ϱ׷��� ����", "�ٸ�", "1998-02-22", 1);
		bookDao.bookInsert(bookVo1);

		BookVo bookVo2 = new BookVo("�ﱹ��", "������", "2002-03-01", 1);
		bookDao.bookInsert(bookVo2);

		BookVo bookVo3 = new BookVo("����", "���δϿ��Ͻ�", "2012-08-15", 2);
		bookDao.bookInsert(bookVo3);

		BookVo bookVo4 = new BookVo("���ù��� �۾��� Ư��", "�����Ǳ�", "2015-04-01", 3);
		bookDao.bookInsert(bookVo4);

		BookVo bookVo5 = new BookVo("�мǿ�", "�߾ӺϽ�(books)", "2012-02-22", 4);
		bookDao.bookInsert(bookVo5);

		BookVo bookVo6 = new BookVo("������ȭ", "�������", "2011-08-03", 5);
		bookDao.bookInsert(bookVo6);

		BookVo bookVo7 = new BookVo("�����λ��", "���е���", "2017-05-04", 6);
		bookDao.bookInsert(bookVo7);

		BookVo bookVo8 = new BookVo("26��", "�������", "2012-02-04", 5);
		bookDao.bookInsert(bookVo8);

		BookVo bookVo9 = new BookVo("������", "����������", "2020-12-29", 1);
		bookDao.bookInsert(bookVo9);

		// ����Ʈ �޾ƿ���(book)
		List<BookVo> bookList;

		bookList = bookDao.getBookList();
		// ����Ʈ(book)
		System.out.println("--- book ����Ʈ ���---");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}

		// ����(book)
		BookVo bookVo0 = new BookVo(9, "������", "����������", "2020-12-29", 2);
		bookDao.bookUpdate(bookVo0);
		// ����Ʈ(book)
		bookList = bookDao.getBookList();
		System.out.println("---������ book ����Ʈ ���---");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}
		// ����(book)
		bookDao.bookDelete(9);

		// ����Ʈ(book)
		bookList = bookDao.getBookList();
		System.out.println("---������ book ����Ʈ ���---");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}
		
		
		List<BookVo> bookallList;
		bookallList = bookDao.getBookallList();
		System.out.println("---book ALL ����Ʈ ���---");
		
		for (int i = 0; i < bookallList.size(); i++) {
			BookVo voa = (BookVo) bookallList.get(i);
			System.out.println(voa.getBookId()+","+voa.getBookTitle()+","+voa.getBookPubs()+","+voa.getBookPubDate()
			+","+voa.getauthorname());
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("�˻��� ���ڸ� �Է��Ͻÿ� .");
		String search = sc.nextLine();
		List<BookVo> booksearchList;
		booksearchList = bookDao.searchList(search);
		
		System.out.println("---�˻��� book ����Ʈ ���---");
		for (int i = 0; i < booksearchList.size(); i++) {
			BookVo vo = booksearchList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}
	}
}
