package AutoITPractice;

public class UtilityClass {
	
	public static void sleep(long seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
