package dbp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class dhldksgehl {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null, rs2= null;
		int re=0;
        Scanner scanner1 = new Scanner(System.in);
        String user_id = null;
		System.out.print("아이디 : ");
		String a_id = scanner1.nextLine();
		System.out.print("비밀번호 : ");
		String a_pw = scanner1.nextLine();
		
		try {
			conn = gazua.getMySQLConnection();
			
			String sql= "select audience_id, audience_pw from audience";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				if (id.contentEquals(a_id) && pw.contentEquals(a_pw)) {
					user_id = id;
					re=1;
					System.out.println("로그인 성공!");
					gazua.close(rs);
					gazua.close(stmt);
					break;
					}
			}
			if(re == 0) {
				System.out.println("로그인 실패!");
				System.exit(0);
			}
			
			String sql1 = "select movie_name, rating, review_content, writing_date, movie_id from(select * from movie natural join review where audience_id="+user_id+") as a";
			stmt = conn.createStatement();
			rs2 = stmt.executeQuery(sql1);
			
			System.out.println("*****내가 작성한 리뷰*****");
			while(rs2.next()) {
				String mname = rs2.getString(1);
				String rate = rs2.getString(2);
				String content = rs2.getString(3);
				String date = rs2.getString(4);
				String id = rs2.getString(5);
				System.out.println("영화 id :"+id+"\t영화 이름: "+mname+"\t평점: "+rate+"\t내용: "+content+"\t작성 날짜: "+date);
			}
			gazua.close(stmt);
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("\n삭제할 영화 id 입력 : ");
			String movie_id = scanner.nextLine();

	        String sql2 = "delete from review where movie_id = ? and audience_id = ?";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, Integer.parseInt(movie_id));
			pstmt.setInt(2, Integer.parseInt(user_id));
			pstmt.executeUpdate();
			System.out.println("리뷰 삭제 완료!");
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			gazua.close(stmt);
			gazua.close(conn);
		}
	}
}

