
import java.sql.*;

public class TwittJDBC {
	Connection con;
	Login lg;

	public TwittJDBC() {
		lg = new Login(this);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/jdbctwit", "root", "angel123");
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}

	public int register(String ID, String Pass) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(ID) from user where ID='" + ID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int result = 0;
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					result = Integer.parseInt(rs.getString(i));
			}

			if (result == 0) {
				PreparedStatement pStmt = con.prepareStatement("insert into user values( ?, ?)");
				pStmt.setString(1, ID);
				pStmt.setString(2, Pass);
				pStmt.executeUpdate();
				pStmt.close();
			} else
				return -1;
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	public int login(String ID, String Pass) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select count(ID) from user where ID='" + ID + "' and password='" + Pass + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int result = 0;
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					result = Integer.parseInt(rs.getString(i));
			}
			if (result == 0) {
				return -1;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public String getPost(String ID, boolean AllM) {
		String result = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			if (AllM == true)
				rs = stmt.executeQuery("select postnum,ID,post from post where postnum>0 order by postnum desc");
			else
				rs = stmt.executeQuery("select distinct(P.postnum),P.ID,P.post from post as P , follow as F"
						+ " where P.id ='" + ID + "' or (F.ID = '" + ID
						+ "' and F.following = P.ID) or (P.post like '%@" + ID + "%') order by P.postnum desc");
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = 0;
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++) {
					if (i == 1)
						result = result + "Post Number : " + rs.getString(i) + "\n";
					else if (i == 2)
						result = result + "ID : " + rs.getString(i) + "\n";
					else
						result = result + "Post : " + rs.getString(i)
								+ "\n--------------------------------------------\n";
				}
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void writePost(String ID, String post) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select max(postnum) from post");
			ResultSetMetaData rsmd = rs.getMetaData();
			int result = 0;
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					result = Integer.parseInt(rs.getString(i));
			}
			PreparedStatement pStmt = con.prepareStatement("insert into post values(?,?,?)");
			pStmt.setString(1, ID);
			pStmt.setString(2, post);
			pStmt.setInt(3, result + 1);
			pStmt.executeUpdate();
			pStmt.close();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int ChangePassword(String ID, String present , String newPass) {
		int result = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select count(*) from user where ID ='" + ID + "' and password ='" + present + "'");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					result = Integer.parseInt(rs.getString(i));
			}
			if(result == 1)
			{
				PreparedStatement pStmt = con.prepareStatement("update user set password = ? where ID = ?");
				pStmt.setString(1, newPass);
				pStmt.setString(2, ID);
				pStmt.executeUpdate();
				pStmt.close();

			}
			else
				return -1;
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	public int checkingfollow(String ID, String following) {
		int result = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select count(*) from follow where ID ='" + ID + "' and following ='" + following + "'");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					result = Integer.parseInt(rs.getString(i));
			}
			if (result == 1)
				return -1;
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public int addFollow(String ID, String following) {
		try {

			PreparedStatement pStmt = con.prepareStatement("insert into follow values(?,?)");
			pStmt.setString(1, ID);
			pStmt.setString(2, following);
			pStmt.executeUpdate();
			pStmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public int deleteFollow(String ID, String following) {
		try {

			PreparedStatement pStmt = con.prepareStatement("delete from follow where ID = ? and following = ?");
			pStmt.setString(1, ID);
			pStmt.setString(2, following);
			pStmt.executeUpdate();
			pStmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public void postDelete(String ID, String postnum) {
		try {

			PreparedStatement pStmt = con.prepareStatement("delete from post where ID = ? and postnum = ?");
			pStmt.setString(1, ID);
			pStmt.setString(2, postnum);
			pStmt.executeUpdate();
			pStmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getfollowerUser(String ID) {
		String result = "follower User List\n------------------\n";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select ID from follow where following='"+ID+"'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = 0;
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++) {
					result = result + rs.getString(i) + "\n";
				}
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public String getFollowUser(String ID) {
		String result ="FollowUser List\n------------------\n";
		try {

			PreparedStatement pStmt = con.prepareStatement("select following from follow where ID=?");
			pStmt.setString(1, ID);

			ResultSet rs;
			rs = pStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = 0;
			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++) {
					result = result + rs.getString(i) + "\n";
				}
			}
			pStmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}