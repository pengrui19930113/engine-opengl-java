package pengrui.javagl.abstraction.util;

public class GlobalConfig {
	/**
	 * 全局debug日志使能开关
	 * release版本设置为false 
	 */
	public static boolean GLOBAL_DEBUG_ENABLE = true;

	/**
	 * 全局info日志使能开关
	 * release版本设置为true
	 */
	public static boolean GLOBAL_INFO_ENABLE = true;
	
	/**
	 * 关于渲染对象深度信息日志打印开关
	 * release版本为false
	 */
	public static boolean DEPTH_INFO_ENABLE = true;

	/**
	 * 输入测试是能开关  如果true 屏蔽掉输入检测
	 * release版本 设置为 false
	 */
	public static boolean INPUT_TEST_ENABLE = true;
	
	/**
	 * 着色器日志使能开关
	 * release版本设置为false
	 */
	public static boolean SHADER_DEBUG_ENABLE = true;
	
	/**
	 * {@link #DEPTH_INFO_ENABLE}开启的时候有意义
	 * 当节点遍历子节点的时候深度 到达该设定值后 打印info日志 帮助调试
	 */
	public static int PRINT_MESSAGE_DEPTH = 6;
	
}
