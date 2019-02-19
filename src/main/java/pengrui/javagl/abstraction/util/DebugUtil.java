package pengrui.javagl.abstraction.util;

import static pengrui.javagl.abstraction.util.LogUtil.*;

public class DebugUtil {

	public static void depthMessage(String msg,int depth,Class<?> clazz){
		depthMessage(msg,depth,GlobalConfig.PRINT_MESSAGE_DEPTH,clazz,null,LOG_LEVEL_INFO);
	}
	/**
	 * 
	 * @param depth
	 * @param needPrintDeepth
	 * @param clazz
	 * @param sign 深度提示符 默认是 #
	 * @param logLevel 0 err; 1 info; 2 debug ; 其他 debug
	 */
	public static void depthMessage(
			String msg
			,int depth
			,int needPrintDeepth
			,Class<?> clazz
			,String sign
			,int logLevel){
		
		msg = null==msg?"":msg;
		sign = null==sign?"#":sign;
		StringBuilder tabs = new StringBuilder("");
		if(depth > needPrintDeepth){
			for(int i = needPrintDeepth; i < depth; ++i ){
				tabs.append(sign);
			}
		}
		String message = String.format("%s-depth message %s,class:%s,depth:%d"
				, tabs.toString()
				, msg
				, clazz==null?Object.class:clazz
						,depth);
		
		switch(logLevel){
		
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
	}
	
	public static void depthInfo(int depth,int needPrintDeepth,Class<?> clazz,String sign){
		depthMessage(null,depth,needPrintDeepth,clazz,sign,LOG_LEVEL_INFO);
	}
	
	public static void depthInfo(int depth,int needPrintDepth,Class<?> clazz){
		depthInfo(depth,needPrintDepth,clazz,null);
	}
	
	public static void depthInfo(int depth,Class<?> clazz){
		depthInfo(depth,GlobalConfig.PRINT_MESSAGE_DEPTH,clazz);
		
	}
	
}
