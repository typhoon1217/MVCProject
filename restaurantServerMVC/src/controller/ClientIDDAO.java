package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.MenuChoice;

public class ClientIDDAO {
    // 데이터베이스에서 ID 생성 메서드
    public String generateIDFromDB() {
        String newCID = null;
        String insertSql = "INSERT INTO client_ids (client_id) VALUES ('CID' || LPAD(client_seq.NEXTVAL, 17, '0'))";
        String selectSql = "SELECT 'CID' || LPAD(client_seq.CURRVAL, 17, '0') AS new_client_id FROM DUAL";

        DBUtil dbu = new DBUtil();
        try (Connection conn = dbu.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        	
            // ID 입력
            System.out.println(insertSql + " 쿼리 전송");
            insertStmt.executeUpdate();

            // ID 조회
            System.out.println(selectSql + " 쿼리 전송");
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    newCID = rs.getString("new_client_id");
                }
            }
            System.out.println("newCID: " + newCID);
            return newCID;
        } catch (SQLException e) {
            e.printStackTrace();
            return MenuChoice.FAIL;
        }
    }

    // 클라이언트 ID 존재 여부 확인
    public boolean checkClientIDExists(String clientID) {
        String sql = "SELECT COUNT(*) AS count FROM client_ids WHERE client_id = ?";

        DBUtil dbu = new DBUtil();
        try (Connection conn = dbu.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, clientID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 로그인 시도 기록
    public void logLoginAttempt(String clientID) {
        String sql = "INSERT INTO client_login_logs (log_id, client_id, login_attempt_time) VALUES (logs_seq.NEXTVAL, ?, SYSTIMESTAMP)";

        DBUtil dbu = new DBUtil();
        try (Connection conn = dbu.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, clientID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 30분 동안의 로그인 시도 횟수를 확인
    public int getRecentLoginAttempts(String clientID) {
        String sql = "SELECT COUNT(*) AS login_attempts " +
                     "FROM client_login_logs " +
                     "WHERE client_id = ? " +
                     "AND login_attempt_time >= (SYSTIMESTAMP - INTERVAL '30' MINUTE)";
        DBUtil dbu = new DBUtil();
        try (Connection conn = dbu.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, clientID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("login_attempts");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    // 로그인 시도 횟수를 초기화하는 메서드  //TODO: 나중에 삭제된정보를 다른테이블에 보관하는 기능 추가  옮기고 지우면 될듯?
    public void resetLoginAttempts(String cID) {
        String sql = "DELETE FROM client_login_logs WHERE client_id = ?";
        DBUtil dbu = new DBUtil();
        try (Connection conn = dbu.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

