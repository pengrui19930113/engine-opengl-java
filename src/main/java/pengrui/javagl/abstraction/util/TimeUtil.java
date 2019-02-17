package pengrui.javagl.abstraction.util;

public class TimeUtil {
	
	static long startTime = System.currentTimeMillis();
	
	public static void update(){
		startTime = System.currentTimeMillis();
	}
	
	public static long getDelteTime(){
		return System.currentTimeMillis() - startTime;
	}
}
