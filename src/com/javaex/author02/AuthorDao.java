package com.javaex.author02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	// 필드
	
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	

	// 생성자
	// 디폴트 생성자
	// 메소드g/s
	// 메소드 일반
	
	// DB 접속
	private void getConnection() {
		// 1. JDBC 드라이버 (Oracle) 로딩
		try {
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			System.out.println("[접속 성공]");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	//자원정리
	private void close() {
		// 5. 자원정리
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
	
	// 수정
	// ------------------------------------------------------------------------------------
	public int authorUpdate(AuthorVo authorVo) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthordesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();// 잘 실행되면 1반환 안되면0

			// 4.결과처리
			System.out.println(count + "건이 변경 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	close();
			

		return count;
	}// 수정 끝 ------------------------------------------------------------------------------------

// 삭제------------------------------------------------------------------------------------
	public int authorDelete(int authorId) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[DAO]-author " + count + "건이 삭제 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}// 삭제끝------------------------------------------------------------------------------------

	// 작가 가져오기
	// ------------------------------------------------------------------------------------
	public List<AuthorVo> getAuthorList() {

		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		getConnection();
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
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

			// 4.결과처리
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
		//자원정리
		close();
		return authorList;
	}// 작가 가져오기 끝
		// ------------------------------------------------------------------------------------

	// 작가 저장 기능
	// ------------------------------------------------------------------------------------
	public int authorInsert(AuthorVo authorVo) {

		getConnection();
		int count = 0;
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval,?,?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthordesc());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[DAO]-author " + count + "건이 저장되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//자원정리
		close();
		

		return count;
	}// 작가 저장기능
}
