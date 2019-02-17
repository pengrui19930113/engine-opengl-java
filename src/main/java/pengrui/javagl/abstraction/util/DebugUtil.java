package pengrui.javagl.abstraction.util;

public class DebugUtil {

	public static void depthInfo(int depth,int needPrintDeepth,Class<?> clazz,String sign){
		sign = null==sign?"#":sign;
		if(depth > needPrintDeepth){
			StringBuilder tabs = new StringBuilder("");
			for(int i = needPrintDeepth; i < depth; ++i ){
				tabs.append(sign);
			}
			LogUtil.info(
					String.format("%s-depth message,class:%s,depth:%d"
							, tabs.toString()
							, clazz==null?Object.class:clazz
							,depth));		
		}
	}
	
	public static void depthInfo(int depth,int needPrintDepth,Class<?> clazz){
		depthInfo(depth,needPrintDepth,clazz,null);
	}
	public static void depthInfo(int depth,Class<?> clazz){
		depthInfo(depth,GlobalConfig.PRINT_MESSAGE_DEPTH,clazz);
		
	}
}
