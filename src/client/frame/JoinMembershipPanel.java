/**
 * 회원가입 화면.  
 * 비밀번호의 보안정도와 누락정보의 경우 오류메세지 출력. 
 * 아이디가 중복된 경우 ErrorMessagePanel이 실행 됨.
 */
package client.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.Controller;
import enums.CommonWord;
import server.userdb.User;
import util.UserInfoPanel;
import server.userdb.UserDAO;
import java.awt.Color;

@SuppressWarnings("serial")
public class JoinMembershipPanel extends UserInfoPanel {

	private static final String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$";
	private Pattern pattern;
	private Matcher matcher;
	private String hex;
	
	private final String SIGN_UP = "가입하기";

	private ArrayList<JTextField> userInfos = new ArrayList<JTextField>();

	private User user;	

	public JoinMembershipPanel() {
		setBackground(new Color(255, 215, 0));

		showFormTitle(CommonWord.SIGN_UP_MEMBERSHIP.getText());
		writeUserInfo();		
		showSignUpButton();
	
	}
	
	UserDAO userDAO;


	/* 회원가입 폼 내용 */
	public void writeUserInfo() {

		int y_value = 120;
		for (CommonWord commonWord : CommonWord.values()) {
			if (commonWord.getNum() >= CommonWord.ID.getNum() && commonWord.getNum() <= CommonWord.BIRTH.getNum()) {
				
				formTitleLabel = new JLabel(commonWord.getText());
				formTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
				formTitleLabel.setBounds(30, y_value, 200, 50);
				add(formTitleLabel);

				if(commonWord.getNum() == CommonWord.PASSWORD.getNum()) {
					
					userInfoPasswordField = new JPasswordField(10);
					userInfoPasswordField.setBounds(30, y_value + 35, 325, 30);
					add(userInfoPasswordField);
					
					userInfos.add(userInfoPasswordField);
					
				} else {
					
					userInfoTextField = new JTextField(10);
					userInfoTextField.setBounds(30, y_value + 35, 325, 30);
					add(userInfoTextField);
					
					userInfos.add(userInfoTextField);
				}
				
				y_value += 55;
				
				
			} else {
				continue;
			}
		}
		
	
	}

	//회원가입 버튼
	private void showSignUpButton() {

		JButton signupButton = showFormButton(SIGN_UP);
		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(checkError() == 1) {
					createUser();
				}
				
				
			}
		});
	}

	//비밀번호의 보안성 체크 
	public boolean PasswordValidate(String hex) {
		hex = userInfoPasswordField.getText();
		pattern = Pattern.compile(Passwrod_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
	
	//회원가입 창에서 오류를 찾는 method
	private int checkError() {
			
		//userDAO = new UserDAO();
		if(userInfos.get(0).getText().equals("") || userInfos.get(1).getText().equals("") ||
				userInfos.get(2).getText().equals("") || userInfos.get(3).getText().equals("") ||
				userInfos.get(4).getText().equals("") || userInfos.get(5).getText().equals("")) {
			JOptionPane.showMessageDialog(this,"다시 입력해주세요.");
			return 0;
		}
		
		if(PasswordValidate(userInfos.get(1).getText()) == false) {
			JOptionPane.showMessageDialog(this,"8자 ~ 16자사이 영숫자 혼합,특수문자 패스워드입력해주세요.");
			return 0;
		}
		
		/*
		if(userDAO.findUid(userInfos) > 0) {
			JOptionPane.showMessageDialog(this,"중복ID입니다!");
			return 0;
		}
		*/
		return 1;
		
	}
	
	//회원가입을 위해 DB에 접근
	private void createUser() {

		user = new User(userInfos.get(0).getText(), userInfos.get(1).getText(), userInfos.get(2).getText(),
				userInfos.get(3).getText(), userInfos.get(4).getText(), userInfos.get(5).getText());

		Controller controller = Controller.getInstance();
		controller.insertDB(user);
	}
}
