package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {
	public static void main(String[] args) {
		//insert into author(author_id, author_name , author_desc)
		//values (seq_author_id.nextval, '�迵��' ,'�˾�����');
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
		    // 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL�� �غ� / ���ε� / ����
			String query = "insert into book values(seq_author_id.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "�����λ��");
			pstmt.setString(2, "���е���");
			pstmt.setString(3, "2017-05-04");
			pstmt.setInt(4, 6);
			
			int count = pstmt.executeUpdate();
			
			
		    // 4.���ó��
			System.out.println(count + "���� ����Ǿ����ϴ�.");
		} catch (ClassNotFoundException e) {
		    System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. �ڿ�����
		    try {
		        //if (rs != null) {
		           // rs.close();
		        //}                
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

	}
}
