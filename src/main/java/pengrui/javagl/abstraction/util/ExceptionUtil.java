package pengrui.javagl.abstraction.util;

public class ExceptionUtil {

	public static void ifNullThrowRuntimeException(Object obj , String msg){
		if(null == obj)
			throw new RuntimeException(msg);
	}
}
