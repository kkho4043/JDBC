package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pstmt = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// DB ����
	public void getConnection() {
		// 1. JDBC ����̹� (Oracle) �ε�
		try {
			Class.forName(driver);

			// 2. Connection ������
			conn = DriverManager.getConnection(url, id, pw);

			System.out.println("[���� ����]");
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	// �ڿ�����
	void close() {
		// 5. �ڿ�����
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
//�˻��ϱ�==================================================================
	public List<BookVo> searchList(String search) {
		// 0. import java.sql.*;

		List<BookVo> BooksearchList = new ArrayList<BookVo>();

		getConnection();
		try {
			String query = "";
			/*
			 * select b.book_id, b.title, b.pubs, TO_CHAR((b.pub_date),'YYYY/MM/DD'),
			 * a.author_name from book b,author a where(b.title LIKE '%��%' or b.pubs LIKE
			 * '%��%' or a.author_name LIKE '%��%') and b.author_id = a.author_id;
			 */
			query += " select  b.book_id, ";
			query += " b.title, ";
			query += " b.pubs, ";
			query += " TO_CHAR((b.pub_date),'YYYY/MM/DD'), ";
			query += " b.author_id, ";
			query += " a.author_name, ";
			query += " a.author_desc ";
			query += " from book b,author a ";
			query += " where(b.title LIKE '%" + search + "%' ";
			query += " or b.pubs LIKE '%" + search + "%' ";
			query += " or a.author_name LIKE '%" + search + "%' )";
			query += " and b.author_id = a.author_id";
			query +=" order by b.book_id asc";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getNString("title");
				String bookpubs = rs.getNString("pubs");
				String bookpubdate = rs.getNString("TO_CHAR((b.pub_date),'YYYY/MM/DD')");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getNString("author_name");
				String authorDesc= rs.getNString("author_desc");
				

				BookVo vo = new BookVo(bookId, bookTitle, bookpubs, bookpubdate,authorId, authorName ,authorDesc);
				BooksearchList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// �ڿ�����
		close();
		return BooksearchList;
	}

//å�� �۰� ��������=================================================================
	public List<BookVo> getBookallList() {
		// 0. import java.sql.*;

		List<BookVo> BookallList = new ArrayList<BookVo>();

		getConnection();
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";

			query += " select  b.book_id, ";
			query += " b.title, ";
			query += " b.pubs, ";
			query += " TO_CHAR((b.pub_date),'YYYY/MM/DD'), ";
			query += " b.author_id, ";
			query += " a.author_name, ";
			query += " a.author_desc ";
			query += " from book b,author a ";
			query += " where b.author_id = a.author_id ";
			query +=" order by b.book_id asc";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getNString("title");
				String bookpubs = rs.getNString("pubs");
				String bookpubdate = rs.getNString("TO_CHAR((b.pub_date),'YYYY/MM/DD')");
				int authorid = rs.getInt("author_id");
				String authorName = rs.getNString("author_name");
				String authorDesc= rs.getNString("author_desc");
				

				BookVo vo = new BookVo(bookId, bookTitle, bookpubs, bookpubdate, authorid,authorName ,authorDesc);
				BookallList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// �ڿ�����
		close();
		return BookallList;

	}

//å ����=================================================================
	public int bookUpdate(BookVo bookVo) {

		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date = ?, ";
			query += "     author_id = ? ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getBookTitle());
			pstmt.setString(2, bookVo.getBookPubs());
			pstmt.setString(3, bookVo.getBookPubDate());
			pstmt.setInt(4, bookVo.getBookauthorId());
			pstmt.setInt(5, bookVo.getBookId());

			count = pstmt.executeUpdate();// �� ����Ǹ� 1��ȯ �ȵǸ�0

			// 4.���ó��
			System.out.println("[DAO - book]" + count + "���� ���� �Ǿ����ϴ�.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// �ڿ�����
		close();
		return count;
	}

//å����=======================================================================
	public int bookDelete(int BookId) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����

			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, BookId);

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[DAO - book]" + count + "���� ���� �Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// �ڿ�����
		close();
		return count;
	}

//å ��������=================================================================
	public List<BookVo> getBookList() {
		// 0. import java.sql.*;

		List<BookVo> BookList = new ArrayList<BookVo>();

		getConnection();
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";

			query += " SELECT book_id, ";
			query += " title, ";
			query += " pubs, ";
			query += " TO_CHAR((pub_date),'YYYY-MM-DD'), ";
			query += " author_id ";
			query += " FROM book";
			query +=" order by book_id asc";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookpubs = rs.getString("pubs");
				String bookpubdate = rs.getString("TO_CHAR((pub_date),'YYYY-MM-DD')");
				int author_id = rs.getInt("author_id");

				BookVo vo = new BookVo(bookId, bookTitle, bookpubs, bookpubdate, author_id);
				BookList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// �ڿ�����
		close();
		return BookList;
	}
	
// å ���=======================================================================
	public int bookInsert(BookVo bookVo) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval,?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getBookTitle());
			pstmt.setString(2, bookVo.getBookPubs());
			pstmt.setString(3, bookVo.getBookPubDate());
			pstmt.setInt(4, bookVo.getBookauthorId());

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[DAO - book]" + count + "���� ����Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// �ڿ�����
		close();

		return count;
	}
}
