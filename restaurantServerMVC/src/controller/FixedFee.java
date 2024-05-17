package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.DATE;
import view.Fixed;
import view.Transaction;

//초기 계획: 백그라운드에서 계속 실행되고 날자를 가지고 와서 그 날자에 실행되는 프로그램 만들기
//변경 계뢱: 어차피 볼때만 가지고오면 되지 않나 싶어서 볼때 호출하는 식으로 바꿈
//3차 계획: 어차피 최소 날이 지날때 발생하니까 매일 밤 로드해서 확인해보고 실행하게 수정 
//특정 시간에 실행하게 할려면 초기 계획도 좋을듯 하지만 최소 날이 지나야되서 3차 계획이 제일 좋은 방법이라 생각됨. 

public class FixedFee {

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
					+Fixed.EXPENSE_DAY + ", " 
					+Fixed.EXPENSE_NAME + ", "
					+Fixed.EXPENSE_AMOUNT + " " 
					+ "FROM " + Fixed.EXPENSE_TABLE;
			// PreparedStatement 생성
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// 쿼리 실행 및 ResultSet 객체 얻기
			ResultSet rs = stmt.executeQuery();

			// 회계 테이블에 삽입할 데이터 목록
			List<Map<String, Object>> montlyFixedFee = new ArrayList<>();

			// 고정 지출 정보 처리
			while (rs.next()) {
				//int period = rs.getInt(FIXED_EXPENSE_PERIOD); // 주기
				int fixedExpenseDay = rs.getInt(Fixed.EXPENSE_DAY); // 지출 일 (27일 등)
				String fixedExpenseName = rs.getString(Fixed.EXPENSE_NAME); // 지출 명칭
				double fixedExpenseAmount = rs.getDouble(Fixed.EXPENSE_AMOUNT); // 지출 금액

				// 지출 시기 계산
				Calendar expenseCalendar = Calendar.getInstance();
				expenseCalendar.set(year, month, fixedExpenseDay);

				// 현재 날짜와 지출 시기 비교
				if (expenseCalendar.getTimeInMillis() <= calendar.getTimeInMillis()) {
					// 지출 시기가 현재 날짜 이전 또는 같은 경우 회계 테이블에 삽입
					Map<String, Object> data = new HashMap<>();
					data.put(Transaction.AMOUNT, fixedExpenseAmount); // 거래 금액
					data.put(Transaction.TIME, new DATE(expenseCalendar.get())); // 거래 시간
					data.put(Transaction.DESCRIPTION, fixedExpenseName + " 지출"); // 거래 설명
					montlyFixedFee.add(data);
				}
			}
			// 회계 테이블에 데이터 삽입
			if (!montlyFixedFee.isEmpty()) {
				updateFixedFee(con, montlyFixedFee);
			}
			// 연결 종료
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateFixedFee(Connection conn, List<Map<String, Object>> data) throws Exception {
		// PreparedStatement 생성
		String insertQuery = "INSERT INTO " + Transaction.TABLE + " (" + Transaction.AMOUNT + ", " + Transaction.TIME
				+ ", " + Transaction.DESCRIPTION + ") VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(insertQuery);

		for (Map<String, Object> entry : data) {
			stmt.setDouble(1, (Double) entry.get(Transaction.AMOUNT)); 
			stmt.setTimestamp(2, (Timestamp) entry.get(Transaction.TIME)); 
			stmt.setString(3, (String) entry.get(Transaction.DESCRIPTION)); 
			stmt.addBatch(); // Add the current record to the batch
		}
		stmt.executeBatch();
		stmt.close();
	}
}
