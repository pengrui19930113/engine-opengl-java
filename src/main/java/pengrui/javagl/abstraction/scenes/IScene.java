package pengrui.javagl.abstraction.scenes;

import pengrui.javagl.abstraction.basics.Lifecyclable;
import pengrui.javagl.abstraction.basics.LifecycleManager;
import pengrui.javagl.abstraction.context.IContext;
import pengrui.javagl.abstraction.ids.Identifiable;
import pengrui.javagl.abstraction.loads.ILoaderManager;
import pengrui.javagl.abstraction.terrain.ITerrain;
import pengrui.javagl.abstraction.util.FPSUtil;
import pengrui.javagl.abstraction.util.PlatformInterface;
import pengrui.javagl.abstraction.util.TimeUtil;

public interface IScene extends Identifiable<IScene>,Lifecyclable{
	
	IScene setTerrain(ITerrain t);
	ITerrain getTerrain();
	
	ISceneManager getSenceManager();
	IScene setSenceManager(ISceneManager sm);
	
	ILoaderManager getLoaderManager();
	IScene setLoaderManager(ILoaderManager lm);
	
	IContext getContext();
	IScene setContext(IContext cxt);
	
	IScene setGameElementLifecycleManager(LifecycleManager lm);
	LifecycleManager getGameElementLifecycleManager();
	
	IScene setNextDefaultScene(IScene next);
	
	void overThisScene();
	/**
	 * 初始化场景内的各个游戏元素
	 */
	void initSceneElement();
	/**
	 * 销毁场景内 需要销毁的元素
	 */
	void destroySceneElement();
	boolean isSceneOverce();
	IScene setScenceOver(boolean close);
	default void init(){
		//TODO check something
		this.getContext().getInputManager().init();
		this.getContext().getActionManager().init();
		this.getContext().getDrawManager().init();
		this.getContext().getLightManager().init();
		this.getContext().getCameraManager().init();
		this.getContext().getNetComponent().init();
		this.getGameElementLifecycleManager().init();//TODO 后续应该由该对象来控制场景内元素的生命周期
		initSceneElement();//TODO getGameElementLifecycleManager 管理生命周期后 改代码可能删除
		this.setScenceOver(false);//清除关闭标志
	}
	
	default void loop(){
		while (!this.isSceneOverce()) {
			FPSUtil.update();
			TimeUtil.update();
			/**************game logic begin*********************/
			this.getContext().getInputManager().inputs();
			this.getContext().getActionManager().actions();
			/**************game logic end*********************/
			
			/**************game render begin*********************/
			this.getContext().getDrawManager().draws();
			/**************game render end*********************/
			PlatformInterface.current.updateDispaly(); // 设置了 fps
		}
	}
	default void destroy(){
		destroySceneElement();
		this.getContext().getInputManager().destroy();
		this.getContext().getActionManager().destroy();
		this.getContext().getDrawManager().destroy();
		this.getContext().getLightManager().destroy();
		this.getContext().getCameraManager().destroy();
		this.getContext().getNetComponent().destroy();
		// 上面的 destory 可能就由下面一个函数调用来解决了 //TODO
		this.getGameElementLifecycleManager().destroy();
	}
}
