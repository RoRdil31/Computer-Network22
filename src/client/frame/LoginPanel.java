/**
 * 로그인 화면
 * 아이디와 비밀번호를 통해 로그인 할 수 있음. 로그인에 실패하였다면 ErrorMessagePanel이 실행된다.
 */
package client.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.Controller;
import enums.CommonWord;
import util.UserInfoPanel;
import java.awt.Color;

@SuppressWarnings("serial")
public class LoginPanel extends UserInfoPanel {

	private final String LOGIN = "로그인";

	private final String GOBACK = "뒤로가기";
	
	private ArrayList<JTextField> userInfos = new ArrayList<JTextField>(); // 이메일과 비밀번호

	public LoginPanel() {
		setBackground(new Color(255, 215, 0));

		showFormTitle(CommonWord.LOGIN.getText());
		writeUserInfo();
		showLoginButton();
	}

	public void writeUserInfo() {

		int y_value = 155;
		for (CommonWord commonWord : CommonWord.values()) {

			//아이디 입력창
			if (commonWord.getNum() == CommonWord.ID.getNum()) {
				formTitleLabel = new JLabel(commonWord.getText());
				formTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
				formTitleLabel.setBounds(30, y_value, 200, 50);
				add(formTitleLabel);
				userInfoTextField = new JTextField(10);
				userInfoTextField.setBounds(30, y_value + 45, 325, 30);
				add(userInfoTextField);
				y_value += 100;

				userInfos.add(userInfoTextField);

			//비밀번호 입력창 
			} else if (commonWord.getNum() == CommonWord.PASSWORD.getNum()) {

				formTitleLabel = new JLabel(commonWord.getText());
				formTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
				formTitleLabel.setBounds(30, y_value, 200, 50);
				add(formTitleLabel);
				userInfoPasswordField = new JPasswordField(10);
				userInfoPasswordField.setBounds(30, y_value + 45, 325, 30);
				add(userInfoPasswordField);
				y_value += 100;

				userInfoPasswordField.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						loginUser();
					}
				});

				userInfos.add(userInfoPasswordField);
			} else {
				continue;
			}
		}
	}

	//로그인 버튼
	private void showLoginButton() {

		JButton loginButton = showFormButton(LOGIN);
		loginButton.setBounds(100, 400, 180, 40);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginUser();
			}
		});
	}

	//로그인을 위해 DB에서 접근
	private void loginUser() {
		Controller controller = Controller.getInstance();
		controller.findUser(userInfos);
	}
}
