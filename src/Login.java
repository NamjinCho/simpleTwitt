import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;

class Login extends JFrame
{
	JPanel panel;

	JLabel jLabel1;
	JTextField IDfield;
	JLabel jLabel2;
	JPasswordField password;
	JButton Login;

	JMenuBar menuBar;
	JMenu menu_0;
	JMenuItem Join;
	TwittJDBC myTwitt;
	Post logPost;
	public Login(TwittJDBC my)
	{
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		myTwitt = my;
		setTitle("Login");
		setBounds(95, 9, 200, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);

		makeComponent();
		makeMenuBar();

		getContentPane().add(panel, BorderLayout.CENTER);
		setVisible(true);
	}

	public void makeComponent()
	{
		jLabel1 = new JLabel();
		jLabel1.setText("ID");
		jLabel1.setFont(new Font("Dialog.plain", 0, 12));
		jLabel1.setIcon(new ImageIcon(""));
		jLabel1.setForeground(new Color(-13421773));
		jLabel1.setBackground(new Color(-1118482));
		jLabel1.setBounds(2, 3, 92, 19);
		panel.add(jLabel1);

		IDfield = new JTextField();
		IDfield.setText("");
		IDfield.setFont(new Font("Dialog.plain", 0, 12));
		IDfield.setForeground(new Color(-13421773));
		IDfield.setBackground(new Color(-16724788));
		IDfield.setBounds(3, 30, 93, 27);
		panel.add(IDfield);

		jLabel2 = new JLabel();
		jLabel2.setText("Password");
		jLabel2.setFont(new Font("Dialog.plain", 0, 12));
		jLabel2.setIcon(new ImageIcon(""));
		jLabel2.setForeground(new Color(-13421773));
		jLabel2.setBackground(new Color(-1118482));
		jLabel2.setBounds(3, 68, 81, 19);
		panel.add(jLabel2);

		password = new JPasswordField();
		password.setText("");
		password.setFont(new Font("Dialog.plain", 0, 12));
		password.setForeground(new Color(-13421773));
		password.setBackground(new Color(-16711681));
		password.setBounds(2, 97, 95, 23);
		panel.add(password);

		Login = new JButton();
		Login.setText("Login");
		Login.setFont(new Font("Dialog.plain", 0, 12));
		Login.setIcon(new ImageIcon(""));
		Login.setForeground(new Color(-13421773));
		Login.setBackground(new Color(-16724788));
		Login.setBounds(3, 128, 94, 24);
		Login.addActionListener(new login());
		panel.add(Login);
	}

	public void makeMenuBar()
	{
		menuBar = new JMenuBar();

		menu_0 = new JMenu("menu");
		Join = new JMenuItem("Join");
		menu_0.add(Join);
		Join.addActionListener(new register());
		menuBar.add(menu_0);

		setJMenuBar(menuBar);
		
	}
	private class register implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			new Join(myTwitt);
		}
	}
		private class login implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				String ID;
				String pass;
				ID = IDfield.getText();
				IDfield.setText("");
				pass = password.getText();
				password.setText("");
				int result = myTwitt.login(ID, pass);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "You are Wellcome Namjin's Twitt");
					logPost = new Post(ID,myTwitt);
					setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "ID is not exist or incorrect Password!");
				}
			}
		}

}
