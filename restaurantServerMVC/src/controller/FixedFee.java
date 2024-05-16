package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//초기 계획: 백그라운드에서 계속 실행되고 날자를 가지고 와서 그 날자에 실행되는 프로그램 만들기
//변경 계뢱: 어차피 볼때만 가지고오면 되지 않나 싶어서 볼때 호출하는 식으로 바꿈
//3차 계획: 어차피 최소 날이 지날때 발생하니까 매일 밤 로드해서 확인해보고 실행하게 수정 
//특정 시간에 실행하게 할려면 초기 계획도 좋을듯 하지만 최소 날이 지나야되서 3차 계획이 제일 좋은 방법이라 생각됨. 

public class FixedFee {
	private static final String FIXED_EXPENSE_TABLE = "FIXED_EXPENSE";
	private static final String ACCOUNTING_TABLE = "ACCOUNTING";
	private static final String TRANSACTION_AMOUNT = "TRANSACTION_AMOUNT";
	private static final String TRANSACTION_TIME = "TRANSACTION_TIME";
	private static final String TRANSACTION_DESCRIPTION = "TRANSACTION_DESCRIPTION";
	private static final String FIXED_EXPENSE_PERIOD = "FIXED_EXPENSE_PERIOD";
	private static final String FIXED_EXPENSE_DAY = "FIXED_EXPENSE_DAY";
	private static final String FIXED_EXPENSE_NAME = "FIXED_EXPENSE_NAME";
	private static final String FIXED_EXPENSE_AMOUNT = "FIXED_EXPENSE_AMOUNT";

	public static void setup() {

		try {
			Connection con = DBUtil.getConnection();

			// 현재 날짜 정보 가져오기
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1; // 0 기반에서 1 더해줘야 함
			int day = calendar.get(Calendar.DAY_OF_MONTH);

			// 고정 지출 정보 조회 쿼리
			String sql = "SELECT " 
					//+ FIXED_EXPENSE_PERIOD + ", " 
					+ FIXED_EXPENSE_DAY + ", " 
					+ FIXED_EXPENSE_NAME + ", "
					+ FIXED_EXPENSE_AMOUNT + " " 
					+ "FROM " + FIXED_EXPENSE_TABLE;

			// PreparedStatement 생성
			PreparedStatement stmt = con.prepareStatement(sql);

			// 쿼리 실행 및 ResultSet 객체 얻기
			ResultSet rs = stmt.executeQuery();

			// 회계 테이블에 삽입할 데이터 목록
			List<Map<String, Object>> accountingData = new ArrayList<>();

			// 고정 지출 정보 처리
			while (rs.next()) {
				//int period = rs.getInt(FIXED_EXPENSE_PERIOD); // 주기
				int fixedExpenseDay = rs.getInt(FIXED_EXPENSE_DAY); // 지출 일 (27일 등)
				String fixedExpenseName = rs.getString(FIXED_EXPENSE_NAME); // 지출 명칭
				double fixedExpenseAmount = rs.getDouble(FIXED_EXPENSE_AMOUNT); // 지출 금액

				// 지출 시기 계산
				Calendar expenseCalendar = Calendar.getInstance();
				expenseCalendar.set(year, month, fixedExpenseDay);

				// 현재 날짜와 지출 시기 비교
				if (expenseCalendar.getTimeInMillis() <= calendar.getTimeInMillis()) {
					// 지출 시기가 현재 날짜 이전 또는 같은 경우 회계 테이블에 삽입
					Map<String, Object> data = new HashMap<>();
					data.put(TRANSACTION_AMOUNT, fixedExpenseAmount); // 거래 금액
					data.put(TRANSACTION_TIME, new Timestamp(expenseCalendar.getTimeInMillis())); // 거래 시간
					data.put(TRANSACTION_DESCRIPTION, fixedExpenseName + " 지출"); // 거래 설명
					accountingData.add(data);
				}
			}

			// 회계 테이블에 데이터 삽입
			if (!accountingData.isEmpty()) {
				insertAccountingData(con, accountingData);
			}

			// 연결 종료
			con.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private static void insertAccountingData(Connection conn, List<Map<String, Object>> data) throws Exception {
		// PreparedStatement 생성
		String insertQuery = "INSERT INTO " + ACCOUNTING_TABLE + " (" + TRANSACTION_AMOUNT + ", " + TRANSACTION_TIME
				+ ", " + TRANSACTION_DESCRIPTION + ") VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(insertQuery);

		for (Map<String, Object> entry : data) {
			stmt.setDouble(1, (Double) entry.get(TRANSACTION_AMOUNT)); 
			stmt.setTimestamp(2, (Timestamp) entry.get(TRANSACTION_TIME)); 
			stmt.setString(3, (String) entry.get(TRANSACTION_DESCRIPTION)); 
			stmt.addBatch(); // Add the current record to the batch
		}

		// Execute batch insert
		stmt.executeBatch();

		// Close the PreparedStatement
		stmt.close();
	}
}
