package dbp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gazua {

//	mysql에 연결하는 메소드
	public static Connection getMySQLConnection() {
		Connection conn = null;
		try {
//			물리적인 클래스명, 드라이버를 쓰겠다는 선언
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
//			DB가 있는 주소
			String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
//			Class.forName으로 반환된 클래스로 연결을 맺는 부분
			conn = DriverManager.getConnection(url, "root", "root");
			
		} catch (ClassNotFoundException e) {	//예외 처리
			System.out.println("드라이버 클래스가 없거나 읽어올 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("데이터베이스 접속 정보가 올바르지 않습니다.");
		}
		return conn;
	}

//	데이터베이스 작업에 사용한 객체를 닫는 메소드
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