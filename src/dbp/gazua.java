package dbp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gazua {

//	mysql�� �����ϴ� �޼ҵ�
	public static Connection getMySQLConnection() {
		Connection conn = null;
		try {
//			�������� Ŭ������, ����̹��� ���ڴٴ� ����
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
//			DB�� �ִ� �ּ�
			String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
//			Class.forName���� ��ȯ�� Ŭ������ ������ �δ� �κ�
			conn = DriverManager.getConnection(url, "root", "root");
			
		} catch (ClassNotFoundException e) {	//���� ó��
			System.out.println("����̹� Ŭ������ ���ų� �о�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("�����ͺ��̽� ���� ������ �ùٸ��� �ʽ��ϴ�.");
		}
		return conn;
	}

//	�����ͺ��̽� �۾��� ����� ��ü�� �ݴ� �޼ҵ�
	public static void close(Connection conn) {
	if(conn!= null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace();}}
	}
	public static void close(Statement stmt) {
	if(stmt!= null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace();}}
	}
	public static void close(PreparedStatement pstmt) {
	if(pstmt!= null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace();}}
	}
	public static void close(ResultSet rs) {
	if(rs!= null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}

}