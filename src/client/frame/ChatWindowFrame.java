/**
 * 채팅창 프레임
 * 
 */
package client.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.Controller;
import util.JFrameWindowClosingEventHandler;
import java.awt.Color;

@SuppressWarnings("serial")
public class ChatWindowFrame extends JFrame {

  private String frameName;

  public ChatWindowFrame(JPanel panel, String frameName) {
  	getContentPane().setBackground(new Color(255, 215, 0));

    Controller controller = Controller.getInstance();
    
    this.frameName = frameName;

    setTitle(controller.username + "의 Chatting");
    setBounds(1200, 250, 405, 605);
    getContentPane().add(panel);
    setResizable(false);
    setVisible(true);
    addWindowListener(new JFrameWindowClosingEventHandler(getFrameName()));
  }

  public String getFrameName() {

    return frameName;
  }

  public void setFrameName(String frameName) {

    this.frameName = frameName;
  }
}
