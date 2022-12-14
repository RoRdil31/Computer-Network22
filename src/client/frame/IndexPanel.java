/**
 *  로그인 이후에 실행되는 대기화면.
 *  친구추가 버튼과 친구들이 있고, 친구를 눌렀을 때 채팅 실행.
 *  아이디를 검색했을 때, 존재하면 친구추가, 없다면 오류 메세지 출력.
 *  API 를 이용한 코로나 확진자 수 정보 출력.
 */
package client.frame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import controller.Controller;
import enums.CommonWord;
import server.datacommunication.Message;
import util.CommonPanel;
import util.MenuPanelButton;
import util.UseImageFile;
import util.UserProfileButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class IndexPanel extends CommonPanel {

	private JLabel jLabel;

	private Image img = UseImageFile.getImage("C:/Users/KSB/Desktop/java/src/Image/woman.png");
	// 사진 받아오기

	public static UserProfileButton userProfileButton;

	public static MenuPanelButton addButton;

	public static MenuPanelButton onlineButton;

	// 버튼 선언

	public static ArrayList<ChatWindowPanel> chatPanelName = new ArrayList<ChatWindowPanel>();

	Controller controller;
	private JTextField textField;

	public IndexPanel() {
		setBackground(new Color(255, 215, 0));

		controller = Controller.getInstance();

		moveAddPanel();

		meanMyProfileTitle(CommonWord.MYPROFILE.getText());
		meanMyProfile();
		// 내 프로필 실행

		meanFriendProfileTitle(CommonWord.FRIEND.getText());
		showFriendList();
		// 친구 리스트 실행

		meanApiTitle(CommonWord.API.getText());
		meanApi();
		// 공공데이터 실행

	}

	private void moveAddPanel() {

		addButton = new MenuPanelButton(CommonWord.ADD.getText());
		addButton.setBounds(12, 20, 92, 46);
		addButton.setOpaque(true);

		add(addButton);

		// 버튼 추가

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				addFriendPanel addfrinedpanel = new addFriendPanel();
				MainPanel.frame.change(addfrinedpanel);
				// 버튼 눌리면 addfriendpanel 로 이동
			}
		});
	}

	// My ProfileTitle
	private void meanMyProfileTitle(String text) {

		jLabel = new JLabel(text);
		// label create
		jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		// label font
		jLabel.setBounds(30, 80, 200, 30);
		add(jLabel);

	}

	private void meanMyProfile() {

		ImageIcon imageIcon = new ImageIcon(img);
		userProfileButton = new UserProfileButton(imageIcon);
		userProfileButton.setBackground(new Color(240, 248, 255));
		userProfileButton.setForeground(new Color(184, 134, 11));
		userProfileButton.setText(controller.username);
		userProfileButton.setBounds(30, 120, 325, 80);
		add(userProfileButton);
		userProfileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 버튼 클리식 대화창 이동
				if (userProfileButton.getText().contains("대화 중..")) {
					// 작동x
				} else {
					userProfileButton.setText(userProfileButton.getText() + "       대화 중..");
					String messageType = "text";
					Message message = new Message(controller.username, controller.username + "님이 입장하였습니다.",
							LocalTime.now(), messageType, controller.username);
					ChatWindowPanel c = new ChatWindowPanel(imageIcon, controller.username);
					new ChatWindowFrame(c, controller.username);
					chatPanelName.add(c);

					Controller controller = Controller.getInstance();
					controller.clientSocket.send(message);
				}
			}
		});
	}

	// friendprofile 타이틀
	private void meanFriendProfileTitle(String text) {

		jLabel = new JLabel(text);
		jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		jLabel.setBounds(30, 220, 200, 30);
		add(jLabel);
	}

	// 친구리스트 보여주기
	private void showFriendList() {

		FriendListPanel jpanel = new FriendListPanel();
		jpanel.setBackground(new Color(222, 184, 135));
		// friendListPanel 실행
		JScrollPane scroller = new JScrollPane(jpanel);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBounds(30, 250, 325, 200);
		add(scroller);
		// 스크롤 설정
	}

	// 공공데이터
	private void meanApiTitle(String text) {

		jLabel = new JLabel(text);
		jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		jLabel.setBounds(30, 470, 200, 30);
		add(jLabel);
	}

	// 공공데이터
	private void meanApi() {
		String bb = client.API.XMLParser.XML();

		jLabel = new JLabel(bb);
		jLabel.setBounds(30, 510, 300, 30);
		add(jLabel);
		
		JButton btnNewButton = new JButton("PW");
		btnNewButton.setBackground(new Color(248, 248, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(150, 20, 54, 46);
		add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		
		
		JButton btnNn = new JButton("NN");
		btnNn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNn.setBounds(216, 20, 54, 46);
		add(btnNn);		
		btnNn.setBorderPainted(false);
		btnNn.setContentAreaFilled(false);
		btnNn.setFocusPainted(false);
		
		JButton btnMsg = new JButton("MSG");
		btnMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMsg.setBounds(282, 20, 73, 46);
		add(btnMsg);
		btnMsg.setBorderPainted(false);
		btnMsg.setContentAreaFilled(false);
		btnMsg.setFocusPainted(false);
		
		
		textField = new JTextField("Im saebom");
		textField.setBackground(SystemColor.menu);
		textField.setBounds(195, 146, 135, 21);
		add(textField);
		textField.setColumns(10);
		// 문자열 추가 하기
	}

	// 그래픽 이용해서 선긋기
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D lin = new Line2D.Float(30, 210, 350, 210);
		g2.draw(lin);
	}
}
