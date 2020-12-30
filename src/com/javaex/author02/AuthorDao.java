package com.javaex.author02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	// �ʵ�
	
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	

	// ������
	// ����Ʈ ������
	// �޼ҵ�g/s
	// �޼ҵ� �Ϲ�
	
	// DB ����
	private void getConnection() {
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
	//�ڿ�����
	private void close() {
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
	
	// ����
	// ------------------------------------------------------------------------------------
	public int authorUpdate(AuthorVo authorVo) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthordesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();// �� ����Ǹ� 1��ȯ �ȵǸ�0

			// 4.���ó��
			System.out.println(count + "���� ���� �Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	close();
			

		return count;
	}// ���� �� ------------------------------------------------------------------------------------

// ����------------------------------------------------------------------------------------
	public int authorDelete(int authorId) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����

			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[DAO]-author " + count + "���� ���� �Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}// ������------------------------------------------------------------------------------------

	// �۰� ��������
	// ------------------------------------------------------------------------------------
	public List<AuthorVo> getAuthorList() {

		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		getConnection();
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			/*
			 * SELECT author_id, author_name, author_desc FROM author;
			 */

			query += " SELECT author_id, ";
			query += " author_name, ";
			query += " author_desc ";
			query += " FROM author";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getNString("author_name");
				String authorDesc = rs.getNString("author_desc");
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		//�ڿ�����
		close();
		return authorList;
	}// �۰� �������� ��
		// ------------------------------------------------------------------------------------

	// �۰� ���� ���
	// ------------------------------------------------------------------------------------
	public int authorInsert(AuthorVo authorVo) {

		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval,?,?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthordesc());

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[DAO]-author " + count + "���� ����Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//�ڿ�����
		close();
		

		return count;
	}// �۰� ������
}
