package pengrui.javagl.abstraction.util;

import java.io.InputStream;

import pengrui.javagl.GameMainLoop;

public class ResourceUtil {
	public static InputStream getResource(String basicMain){
		return GameMainLoop.class.getResourceAsStream(basicMain);
	}
}
