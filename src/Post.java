import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;

class Post extends JFrame {
	JPanel panel;

	Vector jList1_vector;
	JList jList1;
	JScrollPane jList1_scroll;
	JTextField followingID;
	JButton followButton;
	JButton refresh;
	JButton modeAll;
	JButton modeF;
	JTextField PostField;
	JButton Posting;
	JTextField postDelete;
	JButton PostDelete;
	JTextArea allUser;
	JTextArea followUser;
	JScrollPane allUser_scroll;
	JScrollPane followUser_scroll;
	JTextArea InPost;
	JScrollPane InPost_scroll;
	JTextField visitField;
	JButton visitButton;
	String loginID;
	TwittJDBC myTwitt;
	JMenuBar menuBar;
	JMenu menu_0;
	JMenuItem Change;
	boolean allmode = false;

	public Post(String ID, TwittJDBC obj) {
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		loginID = ID;
		myTwitt = obj;
		setTitle("Namjin's Twitt");
		setBounds(92, 0, 650, 567);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);
		
		makeComponent();
		makeMenuBar();
		getContentPane().add(panel, BorderLayout.CENTER);

		
	}

	public void makeMenuBar() {
		menuBar = new JMenuBar();

		menu_0 = new JMenu("Menu");
		Change = new JMenuItem("Change Password");
		Change.addActionListener(new ChangePass());
		menu_0.add(Change);
		menuBar.add(menu_0);

		setJMenuBar(menuBar);
		setBackground(new Color(-1118482));
	}

	public void makeComponent() {
		jList1_vector = new Vector();
		jList1_vector.addElement("jList1");
		jList1 = new JList(jList1_vector);
		jList1_scroll = new JScrollPane(jList1);
		jList1.setFont(new Font("Dialog.plain", 0, 12));
		jList1.setForeground(new Color(-13421773));
		jList1.setBackground(new Color(-1));
		jList1_scroll.setBounds(0, 0, 0, 0);
		panel.add(jList1_scroll);

		followingID = new JTextField();
		followingID.setText("");
		followingID.setFont(new Font("Dialog.plain", 0, 12));
		followingID.setForeground(new Color(-13421773));
		followingID.setBackground(new Color(-1));
		followingID.setBounds(435, 2, 146, 28);
		panel.add(followingID);

		followButton = new JButton();
		followButton.setText("Follow");
		followButton.setFont(new Font("Dialog.plain", 0, 12));
		followButton.setIcon(new ImageIcon(""));
		followButton.setForeground(new Color(-13421773));
		followButton.setBounds(434, 33, 147, 30);
		followButton.addActionListener(new addDelfollow());
		panel.add(followButton);

		refresh = new JButton();
		refresh.setText("Refresh");
		refresh.setFont(new Font("Dialog.plain", 0, 12));
		refresh.setIcon(new ImageIcon(""));
		refresh.setForeground(new Color(-13421773));
		refresh.setBounds(433, 84, 150, 34);
		refresh.addActionListener(new getPosts());
		panel.add(refresh);

		modeAll = new JButton();
		modeAll.setText("All_View");
		modeAll.setFont(new Font("Dialog.plain", 0, 12));
		modeAll.setIcon(new ImageIcon(""));
		modeAll.setForeground(new Color(-13421773));
		modeAll.setBounds(435, 220, 147, 45);
		modeAll.addActionListener(new changeAll());
		panel.add(modeAll);

		modeF = new JButton();
		modeF.setText("Follow_View");
		modeF.setFont(new Font("Dialog.plain", 0, 12));
		modeF.setIcon(new ImageIcon(""));
		modeF.setForeground(new Color(-13421773));
		modeF.setBounds(434, 268, 145, 41);
		modeF.addActionListener(new changeFollow());
		panel.add(modeF);

		PostField = new JTextField();
		PostField.setText("");
		PostField.setFont(new Font("Dialog.plain", 0, 12));
		PostField.setForeground(new Color(-13421773));
		PostField.setBackground(new Color(-1));
		PostField.setBounds(3, 348, 431, 48);
		panel.add(PostField);

		Posting = new JButton();
		Posting.setText("Posting");
		Posting.setFont(new Font("Dialog.plain", 0, 12));
		Posting.setIcon(new ImageIcon(""));
		Posting.setForeground(new Color(-13421773));
		Posting.setBounds(434, 366, 129, 29);
		Posting.addActionListener(new WritePost());
		panel.add(Posting);

		allUser = new JTextArea();
		allUser_scroll = new JScrollPane(allUser);
		allUser.setText(myTwitt.getfollowerUser(loginID));
		allUser.setFont(new Font("Dialog.plain", 0, 12));
		allUser.setForeground(new Color(-13421773));
		allUser.setBackground(new Color(-1));
		allUser.setEditable(false);
		allUser_scroll.setBounds(0, 400, 200, 100);
		panel.add(allUser_scroll);

		followUser = new JTextArea();
		followUser.setText(myTwitt.getFollowUser(loginID));
		followUser_scroll = new JScrollPane(followUser);
		followUser.setFont(new Font("Dialog.plain", 0, 12));
		followUser.setForeground(new Color(-13421773));
		followUser.setBackground(new Color(-1));
		followUser.setEditable(false);
		followUser_scroll.setBounds(201, 400, 200, 100);
		panel.add(followUser_scroll);

		visitField = new JTextField();
		visitField.setText("");
		visitField.setBounds(410, 400, 150, 50);
		visitButton = new JButton();
		visitButton.setText("Visit!");
		visitButton.setBounds(410, 460, 150, 30);
		visitButton.addActionListener(new visitBoard());
		panel.add(visitField);
		panel.add(visitButton);
		postDelete = new JTextField();
		postDelete.setText("");
		postDelete.setFont(new Font("Dialog.plain", 0, 12));
		postDelete.setForeground(new Color(-13421773));
		postDelete.setBackground(new Color(-1));
		postDelete.setBounds(436, 130, 149, 49);

		panel.add(postDelete);

		PostDelete = new JButton();
		PostDelete.setText("PostDelete");
		PostDelete.setFont(new Font("Dialog.plain", 0, 12));
		PostDelete.setIcon(new ImageIcon(""));
		PostDelete.setForeground(new Color(-13421773));
		PostDelete.setBounds(434, 183, 148, 24);
		PostDelete.addActionListener(new postdel());
		panel.add(PostDelete);

		InPost = new JTextArea();
		InPost_scroll = new JScrollPane(InPost);
		InPost.setText("");
		InPost.setFont(new Font("Dialog.plain", 0, 12));
		InPost.setForeground(new Color(-13421773));
		InPost.setBackground(new Color(-1));
		InPost_scroll.setBounds(0, 3, 433, 307);
		InPost.setEditable(false);
		panel.add(InPost_scroll);

		String result = myTwitt.getPost(loginID, false);
		InPost.setText(result);
		this.setVisible(true);
		
	}

	public void refresh() {
		InPost.setText("");
		String result = myTwitt.getPost(loginID, allmode);
		InPost.setText(result);
		allUser.setText(myTwitt.getfollowerUser(loginID));
		followUser.setText(myTwitt.getFollowUser(loginID));
	}

	private class WritePost implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String post = PostField.getText();
			PostField.setText("");
			myTwitt.writePost(loginID, post);
			refresh();
		}
	}

	private class ChangePass implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new changePass(loginID, myTwitt);
		}
	}

	private class getPosts implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			refresh();
		}
	}

	private class changeAll implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			allmode = true;
			refresh();
		}
	}

	private class visitBoard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!visitField.getText().equals("")) {
				othersBoard ob = new othersBoard(myTwitt, visitField.getText(),loginID);
				visitField.setText("");
			}
		}
	}

	private class changeFollow implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			allmode = false;
			refresh();
		}
	}

	private class addDelfollow implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int result = 0;
			result = myTwitt.checkingfollow(loginID, followingID.getText());
			if (result == 1) {
				myTwitt.addFollow(loginID, followingID.getText());
				followingID.setText("");
				JOptionPane.showMessageDialog(null, "Sucess add Follow!");
			} else {
				myTwitt.deleteFollow(loginID, followingID.getText());
				postDelete.setText("");
				JOptionPane.showMessageDialog(null, "Sucess delete Follow!");
			}
			followUser.setText(myTwitt.getFollowUser(loginID));
			refresh();
		}
	}

	private class postdel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myTwitt.postDelete(loginID, postDelete.getText());
			postDelete.setText("");
			refresh();
		}
	}
}
