import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;

class othersBoard extends JFrame
{
	JPanel panel;


	JTextArea Board;
	JScrollPane Board_scroll;

	JTextArea Follower;
	JScrollPane Follower_scroll;

	JTextArea Follow;
	JScrollPane Follow_scroll;
	
	TwittJDBC myTwitt;
	JTextField texf;
	String ownerID;
	String loginID;
	public othersBoard(TwittJDBC t , String s ,String s2)
	{
		myTwitt =t;
		ownerID = s;
		loginID = s2;
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		setTitle("Board");
		setBounds(119, 15, 434, 486);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);

		makeComponent();

		getContentPane().add(panel, BorderLayout.CENTER);
		setVisible(true);
	}

	public void makeComponent()
	{
		Board = new JTextArea();
		Board_scroll = new JScrollPane(Board);
		Board.setText(myTwitt.getPost(ownerID, false));
		Board.setFont(new Font("Dialog.plain", 0, 12));
		Board.setForeground(new Color(-13421773));
		Board.setBackground(new Color(-16711681));
		Board_scroll.setBounds(1, 3, 256, 239);
		Board.setEditable(false);
		panel.add(Board_scroll);
		String flist = myTwitt.getfollowerUser(ownerID);
		
		Follow = new JTextArea();
		Follow_scroll = new JScrollPane(Follow);
		Follow.setText(myTwitt.getFollowUser(ownerID));
		Follow.setFont(new Font("Dialog.plain", 0, 12));
		Follow.setForeground(new Color(-13421773));
		Follow.setBackground(new Color(-16711681));
		Follow_scroll.setBounds(258, 6, 159, 100);
		Follow.setEditable(false);
		panel.add(Follow_scroll);
		
		Follower = new JTextArea();
		Follower_scroll = new JScrollPane(Follower);
		Follower.setText(flist);
		Follower.setFont(new Font("Dialog.plain", 0, 12));
		Follower.setForeground(new Color(-13421773));
		Follower.setBackground(new Color(-16711681));
		Follower_scroll.setBounds(258, 106, 159, 100);
		Follower.setEditable(false);
		panel.add(Follower_scroll);
		
		texf = new JTextField();
		texf.setBounds(1, 250, 250, 50);
		panel.add(texf);
		JButton button = new JButton();
		button.setBounds(260,250,100,50);
		button.setText("Posting");
		button.addActionListener(new WritePost());
		panel.add(button);
		setBackground(new Color(-1118482));
	}
	public void refresh() {
		Board.setText("");
		String result = myTwitt.getPost(ownerID, false);
		Board.setText(result);
	}
	private class WritePost implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String post = texf.getText();
			texf.setText("");
			myTwitt.writePost(loginID, "@"+ownerID+" "+post);
			refresh();
		}
	}
}
