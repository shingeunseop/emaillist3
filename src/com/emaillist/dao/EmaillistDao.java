package com.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistDao {
	
	
	public int insert(EmaillistVo vo) {

		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1.jbdc 드라이브 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3.SQLE 테스트문
			String query = "insert into emaillist values (seq_emaillist_no.nextval,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,vo.getLastName());
			pstmt.setString(2,vo.getFirstName());
			pstmt.setString(3,vo.getEmail());
		
			
			count=pstmt.executeUpdate();
			
			
			// 바인딩을 안줘도 된다.
			// rs.next() =안에 있는 내용을 한칸씩 밀어 주어라
			//4.결과처리
			System.out.println(count+"건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

		return count;
	}

	public List<EmaillistVo> getList() {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<EmaillistVo> list = new ArrayList<EmaillistVo>();

		try {
			// 1.jbdc 드라이브 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3.SQLE 테스트문
			String query = "select no, last_name,first_name, email from emaillist order by no desc";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 바인딩을 안줘도 된다.
			// rs.next() =안에 있는 내용을 한칸씩 밀어 주어라
			while (rs.next()) {
				int no = rs.getInt("no");
				String lastName = rs.getString("last_name");// DB에 있는 필드의 이름으로 있어야 계산시 찾아올수있따.
				String firstName = rs.getString("first_name");
				String email = rs.getString("email");

				EmaillistVo vo = new EmaillistVo(no, lastName, firstName, email);
				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

		return list;
	}

}
