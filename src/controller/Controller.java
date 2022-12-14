package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import client.datacommunication.ClientSocket;
import client.frame.ErrorMessagePanel;
import client.frame.IndexPanel;
import client.frame.MainPanel;
import client.frame.addFriendPanel;
import server.userdb.User;
import server.userdb.UserDAO;

public class Controller {

	private static Controller singleton = new Controller();

	public String username = null;

	public ArrayList<String> userinfo = null;

	public ClientSocket clientSocket;
	
	public String msg = null;

	UserDAO userDAO;

	private Controller() {

		clientSocket = new ClientSocket();

		userDAO = new UserDAO();

	}

	public static Controller getInstance() {

		return singleton;
	}

	//회원가입을 위해 DB에 회원정보를 넣는다.
	public void insertDB(User user) {

		boolean isInsert = userDAO.insertDB(user);

		if (isInsert) {
			MainPanel mainPanel = new MainPanel(MainPanel.frame);
			MainPanel.frame.change(mainPanel);
			JOptionPane.showMessageDialog(mainPanel, "회원가입 성공!!!", "회원가입", JOptionPane.WARNING_MESSAGE);
		} else {
			ErrorMessagePanel errorPanel = new ErrorMessagePanel("회원가입");
			MainPanel.frame.change(errorPanel);
		}

	}

	//로그인을 위해 DB에서 일치하는 정보가 있는지 확인
	public void findUser(ArrayList<JTextField> userInfos) {

		username = userDAO.findUser(userInfos);

		if (username != null) {
			IndexPanel indexPanel = new IndexPanel();
			MainPanel.frame.change(indexPanel);
			clientSocket.startClient();
			JOptionPane.showMessageDialog(indexPanel, "로그인 성공!!!", "로그인", JOptionPane.WARNING_MESSAGE);
		} else if (username == null) {
			ErrorMessagePanel err = new ErrorMessagePanel("로그인");
			MainPanel.frame.change(err);
		}
	}
	
	//한줄소개


	//친구추가를 위해 DB에 친구정보가 있는지 확인.
	public void addFriendDB(String friendId) {

		int already = userDAO.findUserInfo(friendId);

		boolean isAdd = false;

		if (already == 0) {
			addFriendPanel addfriendPanel = new addFriendPanel();
			JOptionPane.showMessageDialog(addfriendPanel, "존재하지 않는 아이디입니다.", "오류", JOptionPane.WARNING_MESSAGE);
		} else if (already == 1){
			
			isAdd =userDAO.addFriendDB(friendId);
			
			if (isAdd) {
				IndexPanel indexPanel = new IndexPanel();
				MainPanel.frame.change(indexPanel);
				JOptionPane.showMessageDialog(indexPanel, "친구추가 성공!", "친구추가", JOptionPane.WARNING_MESSAGE);
			} else {
				ErrorMessagePanel errorPanel = new ErrorMessagePanel("친구추가");
				MainPanel.frame.change(errorPanel);
			}
		}

	}

	//내 친구 리스트를 return
	public ArrayList<String> friendList() {

		ArrayList<String> friends = new ArrayList<String>();
		friends = userDAO.friendList();

		return friends;
	}

}
