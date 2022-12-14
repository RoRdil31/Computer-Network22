package util;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MenuPanelButton extends JButton {
	
	public MenuPanelButton(String buttonTitle) {
		
		setName(buttonTitle);
		setText(buttonTitle);
		setBackground(ColorSet.talkBackgroundColor);
		setFocusPainted(false);
		setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 10));
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
	}
	
	@Override
	  public void setBounds(int x, int y, int width, int height) {

	    super.setBounds(x, y, width, height);
	  }
}

