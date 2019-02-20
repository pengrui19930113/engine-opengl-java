package pengrui.javagl.abstraction.scenes;

import java.util.Collection;

import pengrui.javagl.abstraction.basics.Lifecyclable;
import pengrui.javagl.abstraction.basics.LifecycleManager;
import pengrui.javagl.abstraction.context.IContext;
import pengrui.javagl.abstraction.game.IGame;
import pengrui.javagl.abstraction.ids.Identifiable;
import pengrui.javagl.abstraction.loads.ILoaderManager;
import pengrui.javagl.abstraction.net.INetComponent;
import pengrui.javagl.abstraction.util.CheckUtil;
import pengrui.javagl.abstraction.util.LoaderUtil;

/**
 * 该管理类为游戏初始化的入口 
 * 逻辑上与其他 InputableManager 等其他Manager 不一样， 因此不需要实现Manager接口
 * 
 * 
 * 层次关系
 * 一个游戏有一个场景管理器
 * 
 * 一个场景管理器有 多个场景
 * 
 * 一个场景有一个场景上下文
 * 
 * 一个场景上下文 有各种 游戏中需要的管理类
 * 
 * ISceneManager 的实现 包含一个map 方便查询目标场景
 * @author Administrator
 *
 */
public interface ISceneManager extends Lifecyclable{
	IGame getGame();
	ISceneManager setGame(IGame game);
	
	Collection<IScene> getScenes();
	default ISceneManager setScenes(Collection<IScene> scenes){
		if(CheckUtil.paramNotNull(scenes)){
			for(IScene s:scenes){
				s
				.setContext(IContext.cache)//TODO 每个场景的上下文都不一样
				.setID(Identifiable.getSceneID())//TODO id 后面再来规划
				.setLoaderManager(ILoaderManager.cache)//TODO 后续修改 
				.setNextDefaultScene(null)//TODO 后续由场景关系类来描述
				.setSenceManager(this);
			}
		}
		return this;
	}
	IScene getSceneByID(long id);
	IScene getCurrentScene();
	INetComponent getNetComponent();
	IScene setNetComponent(INetComponent nc);
	LifecycleManager getSceneLifecycleManager();
	IScene getSceneLifecycleManager(LifecycleManager lm);
	IScene setWillBeStartScene(IScene scene);
	void exitCurrentScene();
	IScene addScene(IScene scene);
	
	default void exitGame(){
		this.getGame().setGameIsOver(true);
	}
	
	default void startScene(){
		while(!this.getGame().gameIsOver()){
			getCurrentScene().setSenceManager(this)
				.setContext(IContext.cache)//TODO 每个场景的上下文不一样 后续需要更改
				.setLoaderManager(ILoaderManager.cache)//TODO 每个场景所需要加载的元素也不一样 后续需要更改
				.setID(Identifiable.getSceneID())//TODO 场景ID 后续需要更改获取方式
				;
			getCurrentScene().init();
			getCurrentScene().loop();
		}
	}
	default void init(){
		//TODO do some check
	
		if(this.getGame().isSupportNet())
			this.getNetComponent().init();
		//TODO some check
		startScene();
	}
	default void destroy(){
		LoaderUtil.clearup();
		this.getSceneLifecycleManager().destroy();
	}
}
