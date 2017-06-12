import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;

class Join extends JFrame
{
	JPanel panel;

	JLabel jLabel1;
	JTextField IDfield;
	JLabel jLabel2;
	JPasswordField password;
	JButton Join;
	TwittJDBC myTwitt;

	public Join(TwittJDBC my)
	{
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		myTwitt = my;
		setTitle("Join");
		setBounds(95, 9, 176, 215);

		panel = new JPanel();
		panel.setLayout(null);

		makeComponent();
		

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
		IDfield.setBounds(2, 30, 93, 27);
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
		password.setBackground(new Color(-16724788));
		password.setBounds(2, 97, 95, 23);
		panel.add(password);

		Join = new JButton();
		Join.setText("Join");
		Join.setFont(new Font("Dialog.plain", 0, 12));
		Join.setIcon(new ImageIcon(""));
		Join.setForeground(new Color(-13421773));
		Join.setBackground(new Color(-16724788));
		Join.setBounds(3, 128, 94, 24);
		Join.addActionListener(new register());
		panel.add(Join);
	}
	private class register implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String ID;
			String pass;
			ID = IDfield.getText();
			IDfield.setText("");
			pass = password.getText();
			password.setText("");
			int result = myTwitt.register(ID, pass);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "You are Wellcome Namjin's Twitt");

			} else {
				JOptionPane.showMessageDialog(null, "ID is already exist");
			}
		}
	}
	
}
