package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	
	private Connection con;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/yesdbp";
			String dbID = "root";
			String dbPassword = "onlyroot";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �α���
	/*
	 * -2: ���̵����
	 * -1: ��������
	 * 0: ��й�ȣ Ʋ��
	 * 1: ����
	 */
	public int login(String id, String password) {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT userPassword FROM user WHERE userID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString(1).equals(password) ? 1 : 0;
			} else {
				return -2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// �ߺ����� Ȯ��
	public boolean ID_Check(String id) {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// ȸ������
	/*
	 * -1: ��������
	 * 0: �̹� �����ϴ� ���̵�
	 * 1: ����
	 */
	public int join(UserDAO userDAO) {
		if(!ID_Check(userDAO.getUserID())) return 0;
		
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
			pst.setString(1, userDAO.getUserID());
			pst.setString(2, userDAO.getUserPassword());
			pst.setString(3, userDAO.getUserName());
			pst.setString(4, userDAO.getUserNumber());
			pst.setString(5, userDAO.getUserAddress());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// ���� ������ ��������
	public UserDAO getUser(String id) {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				UserDAO userDAO = new UserDAO();
				userDAO.setUserID(rs.getString(1));
				userDAO.setUserPassword(rs.getString(2));
				userDAO.setUserName(rs.getString(3));
				userDAO.setUserNumber(rs.getString(4));
				userDAO.setUserAddress(rs.getString(5));
				return userDAO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getUserID() {
		return id;
	}
	public void setUserID(String id) {
		this.id = id;
	}
	
	public String getUserPassword() {
		return password;
	}
	public void setUserPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return name;
	}
	public void setUserName(String name) {
		this.name = name;
	}
	
	public String getUserNumber() {
		return phone;
	}
	public void setUserNumber(String phone) {
		this.phone = phone;
	}
	public String getUserAddress() {
		return address;
	}
	public void setUserAddress(String address) {
		this.address = address;
	}
}