
package client.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;
import enums.CommonWord;
import util.UserInfoPanel;
import java.awt.Color;

@SuppressWarnings("serial")
public class addFriendPanel extends UserInfoPanel {
	
	private final String SEARCH = "검색";
	
	private final String GOBACK = "뒤로가기";

	public static MainFrame frame;
	
	private String friendid;
	
	public addFriendPanel() {
		setBackground(new Color(255, 215, 0));
		
		showFormTitle(CommonWord.ADD.getText());
		writeUserInfo();
		showSearchButton();
		showBackButton();
	}
		
	//label 과 text field 
	public void writeUserInfo() {
		
		int y_value = 200;
		
		formTitleLabel = new JLabel(CommonWord.ID.getText());
		formTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		formTitleLabel.setBounds(30, y_value, 200, 50);
		add(formTitleLabel);
		
		userInfoTextField = new JTextField(10);
		userInfoTextField.setBounds(30, y_value + 45, 200, 50);
		add(userInfoTextField);		


	}
	
	//검색버튼
	private void showSearchButton() {
		
		JButton searchButton = showFormButton(SEARCH);
		searchButton.setBounds(100, 400, 180, 40);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					friendid = userInfoTextField.getText();
					addUser(friendid);
					
			}
		});				
	}
	
	//뒤로가기 버튼
	private void showBackButton() {
		
		JButton gobackButton = showFormButton(GOBACK);
		gobackButton.setBounds(100, 450, 180, 40);
		gobackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IndexPanel indexPanel = new IndexPanel();
				MainPanel.frame.change(indexPanel);					
			}
		});
	}
	
	//친구추가를 위해 DB에 접근
	private void addUser(String friendid) {
					
		Controller controller = Controller.getInstance();
		controller.addFriendDB(friendid);
	}
}
