import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;

class changePass extends JFrame
{
	JPanel panel;

	JPasswordField jPasswordField1;
	JLabel jLabel1;
	JLabel jLabel2;
	JPasswordField jPasswordField2;
	JButton jButton1;
	String myID;
	TwittJDBC myTwitt;
	public changePass(String LoginID,TwittJDBC t)
	{
		myTwitt = t;
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		myID = LoginID;
		setTitle("새 프래임");
		setBounds(158, 40, 216, 306);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);

		makeComponent();

		getContentPane().add(panel, BorderLayout.CENTER);
	}

	public void makeComponent()
	{
		jPasswordField1 = new JPasswordField();
		jPasswordField1.setText("");
		jPasswordField1.setFont(new Font("Dialog.plain", 0, 12));
		jPasswordField1.setForeground(new Color(-13421773));
		jPasswordField1.setBackground(new Color(-1));
		jPasswordField1.setBounds(2, 34, 140, 39);
		panel.add(jPasswordField1);

		jLabel1 = new JLabel();
		jLabel1.setText("PresentPassword");
		jLabel1.setFont(new Font("Dialog.plain", 0, 12));
		jLabel1.setIcon(new ImageIcon(""));
		jLabel1.setForeground(new Color(-13421773));
		jLabel1.setBackground(new Color(-1118482));
		jLabel1.setBounds(6, 11, 131, 15);
		panel.add(jLabel1);

		jLabel2 = new JLabel();
		jLabel2.setText("NewPassword");
		jLabel2.setFont(new Font("Dialog.plain", 0, 12));
		jLabel2.setIcon(new ImageIcon(""));
		jLabel2.setForeground(new Color(-13421773));
		jLabel2.setBackground(new Color(-1118482));
		jLabel2.setBounds(2, 130, 164, 19);
		panel.add(jLabel2);

		jPasswordField2 = new JPasswordField();
		jPasswordField2.setText("");
		jPasswordField2.setFont(new Font("Dialog.plain", 0, 12));
		jPasswordField2.setForeground(new Color(-13421773));
		jPasswordField2.setBackground(new Color(-1));
		jPasswordField2.setBounds(1, 171, 145, 39);
		panel.add(jPasswordField2);

		jButton1 = new JButton();
		jButton1.setText("Change");
		jButton1.setFont(new Font("Dialog.plain", 0, 12));
		jButton1.setIcon(new ImageIcon(""));
		jButton1.setForeground(new Color(-13421773));
		jButton1.setBounds(6, 232, 158, 25);
		jButton1.addActionListener(new ChangePass());
		panel.add(jButton1);
		setVisible(true);
	}
	private class ChangePass implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = 0;
			result = myTwitt.ChangePassword(myID, jPasswordField1.getText(), jPasswordField2.getText());
			if(result ==1)
				JOptionPane.showMessageDialog(null, "Sucess Change Password!");
			setVisible(false);
		}
	}
	
}
