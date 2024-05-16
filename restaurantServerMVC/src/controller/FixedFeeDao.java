import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date; // Date를 import합니다.
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FixedFeeDao {
    public static Map<String, Integer> loadFee() {
        Map<String, Integer> feeMap = new HashMap<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            stmt = con.prepareStatement("SELECT condition, fee FROM fee_table");
            rs = stmt.executeQuery();

            while (rs.next()) {
                String condition = rs.getString("condition"); 
                int fee = rs.getInt("fee"); 
                feeMap.put(condition, fee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return feeMap;
    }
}