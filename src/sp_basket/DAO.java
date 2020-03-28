package sp_basket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private static DAO DAOins;

	private DAO() {
	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패:" + e.getMessage());
		}
	}

	public static DAO getInstance() {
		if (DAOins == null) {
			DAOins = new DAO();
		}
		return DAOins;
	}

	private boolean link() {
		boolean result = false;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			result = true;
		} catch (SQLException e) {
			System.out.println("연결 실패:" + e.getMessage());
		}
		return result;
	}

	public ArrayList<String[]> getContents() {
		ArrayList<String[]> dList = new ArrayList();
		String sql = "SELECT * FROM CART";
		if (link()) {
			try {
				st = con.createStatement();
				if (st != null) {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						DTO_Cart ctDB = new DTO_Cart();
						ctDB.setmID(rs.getString("MID"));
						ctDB.setmName(rs.getString("MNAME"));
						ctDB.setObName(rs.getString("OBNAME"));
						ctDB.setEa(Integer.toString(rs.getInt("OBEA")));
						int sumA = rs.getInt("OBEA");
						ctDB.setPrice(Integer.toString(rs.getInt("OBPRICE")));
						int sumB = rs.getInt("OBPRICE");
						ctDB.setSum(Integer.toString(sumA * sumB));
						dList.add(ctDB.getArray());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}
		return dList;
	}

	public boolean Insert(DTO_Cart ADR) {
		boolean result = false;
		if (link()) {
			String sql = "insert into cart values(?,?,?,?,?)";
			try {
				PreparedStatement ppst = con.prepareStatement(sql);
				ppst.setString(1, ADR.getmID());
				ppst.setString(2, ADR.getmName());
				ppst.setString(3, ADR.getObName());
				ppst.setString(4, ADR.getEa());
				ppst.setString(5, ADR.getPrice());

				int r = ppst.executeUpdate();
				result = true;
				if (r > 0) {
					result = true;
				}
				ppst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deltoOracle(String delID) {
		if (link()) {
			String sql = "delete from cart where mid='" + delID + "'";
			try {
				st = con.createStatement();
				int k = st.executeUpdate(sql);
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}
		return 0;
	}

	public int update(ArrayList<String> Attribute, ArrayList<String> Valuse) {
		if (link()) {
			String sql = "update cart set " + Attribute.get(0) + "='" + Valuse.get(0)+"'";
			try {
				st = con.createStatement();
				int k = st.executeUpdate(sql);
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public ArrayList<String[]> getOrder(String selID) {
		ArrayList<String[]> odList = new ArrayList();
		String sql = "SELECT  FROM CART";
		if (link()) {
			try {
				PreparedStatement pps = con.prepareStatement(sql);
				pps.setString(1, x);
				
				if (st != null) {
					rs = st.executeQuery(sql);
					while (rs.next()) {
						DTO_Cart ctDB = new DTO_Cart();
						ctDB.setmID(rs.getString("MID"));
						ctDB.setmName(rs.getString("MNAME"));
						ctDB.setObName(rs.getString("OBNAME"));
						ctDB.setEa(Integer.toString(rs.getInt("OBEA")));
						int sumA = rs.getInt("OBEA");
						ctDB.setPrice(Integer.toString(rs.getInt("OBPRICE")));
						int sumB = rs.getInt("OBPRICE");
						ctDB.setSum(Integer.toString(sumA * sumB));
						odList.add(ctDB.getArray());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}
		return dList;
	}
}
