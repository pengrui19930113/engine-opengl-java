package pengrui.javagl.abstraction.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public interface PlatformInterface {

	void setWindowTitle(String title);
	public  void updateDispaly();
	public void closeDispaly();
	public boolean isCloseRequested();
	public void createDispaly();
	static final int WIDTH = 1024;
	static final int HEIGHT = 680;
	static final int FPS = 60;
	static final String TITLE = "LWJGL 标题";
	public static final PlatformInterface current = new PlatformInterface() {
		@Override
		public void createDispaly(){
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
		@Override
		public void setWindowTitle(String title) {
			Display.setTitle(title);
		}
		@Override
		public void updateDispaly(){
			Display.sync(FPS);
			Display.update();
		}
		
		@Override
		public void closeDispaly(){
			Display.destroy();
		}
		
		@Override
		public boolean isCloseRequested(){
			return Display.isCloseRequested();
		}
	};
}
