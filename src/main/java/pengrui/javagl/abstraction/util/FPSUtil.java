package pengrui.javagl.abstraction.util;

public class FPSUtil {

	static int currentFPS = 0;
	static long tempTime = System.currentTimeMillis();
	
	public static void update(){
		currentFPS++;
		if((System.currentTimeMillis()-tempTime )> 1000){
			tempTime = System.currentTimeMillis();
			String title = String.format("fps:%d", currentFPS);
			currentFPS = 0;
			PlatformInterface.current.setWindowTitle(title);
		}
	}
	
	
}
