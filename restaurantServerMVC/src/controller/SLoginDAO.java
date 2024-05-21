package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.MenuChoice;

public class SLoginDAO {
    
    public String loginAndGetDepartment(String id, String password) {
        String department = MenuChoice.FAIL;
        
        String sql = "SELECT EMP_DEPARTMENT FROM Employee WHERE EMP_ID = ? AND EMP_PASSWORD = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {

	        DBUtil dbu = new DBUtil();
			con = dbu.getConnection();
			
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            
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
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } finally {
            // 리소스 해제 --메모리 누수 방지를 위해 별도로 닫아줌
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return department;
    }

}


