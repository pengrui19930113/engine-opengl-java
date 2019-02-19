package pengrui.javagl.abstraction.util;

import static pengrui.javagl.abstraction.util.LogUtil.*;

public class CheckUtil {

	static final String MESSAGE = "invalid param ";

	public static boolean paramNotNull(Object obj) {
		return paramNotNull(LOG_LEVEL_DEBUG,obj);
	}
	public static boolean paramNotNull(int logLevel, Object obj) {
		if (null == obj) {
			String message = MESSAGE;
			switch (logLevel) {

			case LOG_LEVEL_ERROR:
				LogUtil.error(message);
				break;
			case LOG_LEVEL_INFO:
				LogUtil.info(message);
				break;
			case LOG_LEVEL_DEBUG:
				LogUtil.debug(message);
				break;

			default:
				LogUtil.debug(message);
				break;
			}
			return false;
		}
		return true;
	}

	public static boolean paramsAllNotNull(Object... obj) {
		return paramsAllNotNull(LOG_LEVEL_DEBUG,obj);
	}
	
	public static boolean paramsAllNotNull(int logLevel, Object... obj) {
		if (!paramNotNull(logLevel, obj))
			return false;

		for (Object object : obj)
			if (!paramNotNull(logLevel, object))
				return false;

		return true;
	}
}
