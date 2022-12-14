/*
 * DB에 데이터 접근을 위한 class
 */
package server.userdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;

public class UserDAO {

	private String driver = "com.mysql.cj.jdbc.Driver";

	private String jdbcurl = "jdbc:mysql://localhost/main";

	private Connection conn;

	private PreparedStatement pstmt, pstmt2;

	public String username = null;

	//jdbc를 이용해 connection을 만듬.
	public void connect() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(jdbcurl, "root", "Spring030!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//DB와의 연결 해제 
	public void disconnect() {

		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//회원 정보를 DB에 insert 
	//성공시 true 실패시 false를 반환.
	public boolean insertDB(User user) {

		connect();
		String sql = "insert into member_table1 values(?,?,?,?,?,?)";

		boolean isInsert = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getUnickname());
			pstmt.setString(5, user.getUemail());
			pstmt.setString(6, user.getUbirth());
			pstmt.executeUpdate();

			isInsert = true;

		} catch (SQLException e) {
			isInsert = false;
		}
		disconnect();

		return isInsert;

	}

	//친구 릴레이션에 친구의 아이디를 저장. 서로 친구를 맺을 수 있도록 구성.
	public boolean addFriendDB(String friendid) {

		String userid = findUserId();
		String fid = friendid;
		connect();
		String sql = "insert into friendList1(userid, friendid) values (?,?)";

		boolean isAdd = false;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, fid);
			pstmt.executeUpdate();

			isAdd = true;

		} catch (SQLException e) {
			isAdd = false;
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fid);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
			
			isAdd = true;
		} catch (SQLException e) {
			isAdd = false;
		}

		disconnect();

		return isAdd;

	}

	// 해당 아이디를 가지고 있는 user가 있는지 없는지 확인.
	public int findUserInfo(String userid) {

		connect();
		String sql = "select * from member_table1 where uid = ?";
		String id = userid;

		int result = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1; // 존재함
			} else {
				result = 0; // 존재하지않음
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	//아이디와 비밀번호가 일치하는 user의 이름을 반환.
	public String findUser(ArrayList<JTextField> userInfos) {

		connect();
		String sql = "select uname from member_table1 where uid =? and password=?";
		String uid = userInfos.get(0).getText();
		String password = userInfos.get(1).getText();

		String uname = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				uname = rs.getString("uname");
			}

			username = uname;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect();

		return username;
	}

	//친구 릴레이션과 멤버 릴레이션을 통해 친구가 맺어져있는 user들을 반환
	public ArrayList<String> friendList() {

		String uid = findUserId();
		connect();
		ArrayList<String> friends = new ArrayList<String>();
		String sql = "select m.uname from member_table1 m, friendList1 f where m.uid = f.friendid and f.userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				friends.add(rs.getString("uname"));
			}
		} catch (SQLException e) {
		}
		disconnect();
		System.out.println(friends.size());
		return friends;
	}

	//해당 이름을 가진 user의 id를 반환
	private String findUserId() {

		connect();
		String sql = "select uid from member_table1 where uname = ?";
		String uid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				uid = rs.getString("uid");
			}
		} catch (SQLException e) {
		}
		disconnect();
		return uid;
	}

	


}
