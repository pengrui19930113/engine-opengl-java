package pengrui.javagl;


import java.io.IOException;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;
import pengrui.javagl.abstraction.factorys.InputManagerFactory;
import pengrui.javagl.abstraction.factorys.LifecycleManagerFactory;
import pengrui.javagl.abstraction.models.ModelData;
import pengrui.javagl.abstraction.models.SpaceData;
import pengrui.javagl.abstraction.util.FPSUtil;
import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.PlatformInterface;
import pengrui.javagl.abstraction.util.TimeUtil;
import pengrui.javagl.entitys.Camera;
import pengrui.javagl.entitys.Light;
import pengrui.javagl.model.GameModel;

/**
 * Hello world!
 *
 */
public class GameMainLoop {

	static void gaming() throws IOException{
		PlatformInterface.current.createDispaly();
		InputManagerFactory.getInstance();// 必须现在lifecyclemanager init 前调用 否则 没有注册到其中管理生命周期
		ActionManagerFactory.getInstance();//必须现在lifecyclemanager init 前调用 否则 没有注册到其中管理生命周期
		DrawManagerFactory.getInstance();//必须现在lifecyclemanager init 前调用 否则 没有注册到其中管理生命周期
		
		Light.manualInit(new Vector3f(10.0f,0.f,10.f), new Vector3f(1.0f,1.0f,1.0f));
		Camera.getInstance();
		
		Random r = new Random();
		int xw = 200;
		int yw = 20;
		int zh = 200;
		for(int i=0;i<100;++i){
			float x = r.nextFloat()*xw - xw/2;
			float y = r.nextFloat()*yw - yw/2;
			float z = r.nextFloat()*zh - zh/2;
			SpaceData sp = new SpaceData(new Vector3f(x,y,z), null, null);
			new GameModel(sp,ModelData.cache);
		}
		
		
		LifecycleManagerFactory.getInstance().init();
		
		while (!PlatformInterface.current.isCloseRequested()) {
			FPSUtil.update();
			TimeUtil.update();
			/**************game logic begin*********************/
			InputManagerFactory.getInstance().inputs(); 
			ActionManagerFactory.getInstance().actions();
			/**************game logic end*********************/
			
			/**************game render begin*********************/
			DrawManagerFactory.getInstance().draws();
			/**************game render end*********************/
			PlatformInterface.current.updateDispaly(); // 设置了 fps
				
		}
		LoaderUtil.clearup();
		LifecycleManagerFactory.getInstance().destroy();
		PlatformInterface.current.closeDispaly();
	}
	
	public static void main(String[] args) throws IOException {
		gaming();
//		main3();
	}
	
	static void main3(){
		System.getProperties().entrySet().forEach(LogUtil::info);
	}
	/**
	 * 游戏框架大致流程  
	 * 1.场景选择
	 * 2.进入场景的渲染循环
	 * 3.计算当前帧的初始时间
	 * 4.处理输入（键盘 鼠标 网络）
	 * 5.根据各个组件的当前状态进行一帧
	 * 6.渲染当前帧的画面（包括双缓冲刷新）
	 * 7.根据当前帧所执行的时间来决定是否 sleep 以及sleep的时间
	 */
	static void gameLoopFramework(){
//		String[] scenes = {
//				"scene1"
//				,"scene2"
//				,"scene3"
//				,"scene4"
//				,"scene5"
//				,"over"
//		};
		String targetScene = "scene1";
		
		while(!targetScene.equals("over")){
			boolean senceIsOver = false;
			while(senceIsOver){
				/**
				 * 1. time update
				 */
				/**
				 * 2. process input 
				 */
				/**
				 * 3. gameing logic process
				 */
				/**
				 * 4. render everything
				 */
				/**
				 * 5. control the fps
				 */
			}
		}
	}
}
