package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.cos.model.User;
import com.cos.util.DBClose;

public class UserDao {
	private UserDao() {
	}

	public static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;// 결과를 보관할 커서

	public int update(User user) {
		final String SQL = "UPDATE user SET password = ?, address = ? WHERE id = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getAddress());
			pstmt.setInt(3, user.getId());
		

			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	
	public int profileUpdate(String username , String userProfile) {
		final String SQL = "UPDATE user SET userProfile = ? WHERE username = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, userProfile);
			pstmt.setString(2, username);
			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	
	public int emailCheckUpdate(String email, String username) {
		final String SQL = "UPDATE user SET emailCheck = 1 WHERE email = ? AND username = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, email);
			pstmt.setString(2, username);

			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	
	
	public User findByUsername(String username) {
		final String SQL = "SELECT * FROM user WHERE username = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));//주소추가
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setUserProfile(rs.getString("userProfile"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;

	}

	public int save(User user) {
		final String SQL = "INSERT INTO user(username, password, email, address, createDate) VALUES(?,?,?,?,now())";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());

			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	// 로그인 인증 성공 : 1 , DB오류 : -1 , 미인증 : 0
	public int findByUsernameAndPasswordAndEmailCheck(String username, String password) {
		final String SQL = "SELECT count(id) FROM user WHERE username = ? and password = ? and emailCheck = 1";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int result = rs.getInt(1);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return -1;
	}
	

}
