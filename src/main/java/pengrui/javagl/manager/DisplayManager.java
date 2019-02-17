package pengrui.javagl.manager;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

//	static final int WIDTH = 1024;
//	static final int HEIGHT = 768;
	static final int WIDTH = 1024;
	static final int HEIGHT = 680;
	static final int FPS = 60;
	static final String TITLE = "LWJGL 标题";
	
	public static void createDispaly(){
		ContextAttribs att = new ContextAttribs(3, 2);
		att.withForwardCompatible(true);
		att.withProfileCompatibility(true);
		try {
			Display.setLocation(0, 0); // window left top position on the desktop
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(),att);
			Display.setTitle(TITLE);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	public static void setWindowTitle(String title){
		Display.setTitle(title);
	}
	public static void updateDispaly(){
		Display.sync(FPS);
		Display.update();
	}
	
	public static void closeDispaly(){
		Display.destroy();
	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
}
