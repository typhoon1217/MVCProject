package controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedFee {
	public static boolean fixedFeeExit = false;
	
    public static void setup() {
    	while(!fixedFeeExit){
            FixedFeeDao.loadFee(); // 시작 시 고정비용 로드
            System.out.println("고정비용 로딩됨");

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                ChargeOnTime(); // 특정시간에 차감되는 로직 
            });
            
            System.out.println("고정비용 차감 프로그램 종료");
            executor.shutdown(); 
            System.out.println("재실행합니다.");
    	}
    	System.out.println();
    }
    
    public static void ChargeOnTime() {
    	System.out.println("고정비용 차감 프로그램 실행");
        while (!fixedFeeExit) {
        	syncronized(lock){
        		
        	}
            System.out.println("시간 청구 중...");
            // 여기서 백그라운드에서 수행할 시간 청구 작업을 수행합니다.
            try {
                Thread.sleep(1000); // 1초 동안 대기합니다. (조절합니다.)
                //Thread.sleep(3600000); // 1시간 동안 대기합니다. 
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;// 나갈시 프로그램 종료후 재실행됨.
            }
        }
    }
}
