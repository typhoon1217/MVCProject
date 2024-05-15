package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import view.EmpVo;

public class SLoginDAO {
	
	private static void loginValidation(String id, String pw) {
		String sql = "SELECT EMP_DEPARTMENT FROM Employee WHERE EMP_ID = ? AND EMP_PASSWORD = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, empVo.getEMP_ID());
			pstmt.setString(2, empVo.getEMP_PASSWORD());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(EmpVo.getS_name() + " 학과 수정 완료.");
				System.out.println("학과 수정 성공!!!");
			} else {
				System.out.println("로그인 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

	}
}
