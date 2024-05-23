package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginInsert {
	public void loginInsert(String inputID, String inputPW) {
		String department = "ㅇㅇ";
	    ResultSet rs = null;
		Connection con = null; 
        PreparedStatement pstmt = null; 
        try {
            con = DBUtil.getConnection();
            if(con!=null) {
                System.out.println("접속완료 ");	
            }else {
                System.out.println("접속실패 ");	
            }
            String sql = "SELECT * FROM Employee WHERE EMP_ID = ? AND EMP_PASSWORD = ?";
  
            
            
            //test
            System.out.println(inputID);
            System.out.println(inputPW);
            
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, inputID);
            pstmt.setString(2, inputPW);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.print("Passed as: ");
                // 부서명을 가져오기
                department = rs.getString("EMP_DEPARTMENT");
                System.out.println(department);
            } else {
                //직원 없음 fail 유지 개인정보 불일치 출력 
                System.out.println("Invalid credentials. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                try {
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
	}
	

}
