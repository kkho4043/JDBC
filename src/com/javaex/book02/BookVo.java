package com.javaex.book02;

public class BookVo {
	// 필드
	public int bookId;
	public String bookTitle;
	public String bookPubs;
	public String bookPubDate;
	public int bookauthorId;
	public String authorname;
	

	public BookVo(String bookTitle, String bookPubs, String bookPubDate, int bookauthorId) {
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.bookauthorId = bookauthorId;
	}

	public BookVo(int bookId, String bookTitle, String bookPubs, String bookPubDate, int bookauthorId) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.bookauthorId = bookauthorId;
	}
	
	public BookVo(int bookId, String bookTitle, String bookPubs, String bookPubDate,String authorname) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.authorname = authorname;
	}

	// 메소드 getset
	
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookPubs() {
		return bookPubs;
	}

	public void setBookPubs(String bookPubs) {
		this.bookPubs = bookPubs;
	}

	public String getBookPubDate() {
		return bookPubDate;
	}

	public void setBookPubDate(String bookPubDate) {
		this.bookPubDate = bookPubDate;
	}

	public int getBookauthorId() {
		return bookauthorId;
	}

	public void setBookauthorId(int bookauthorId) {
		this.bookauthorId = bookauthorId;
	}

	public String getauthorname() {
		return authorname;
	}

	public void setBookname(String authorname) {
		this.authorname = authorname;
	}
	
	
	
	
	
	
}
