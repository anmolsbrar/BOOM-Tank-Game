package boomGame;

import javax.swing.JFrame;

public class frame extends JFrame {

	public frame() {
		setSize(700, 365);
		setTitle("BOOM!");
		add(new Board());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}


}
