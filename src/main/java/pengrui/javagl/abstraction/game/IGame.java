package pengrui.javagl.abstraction.game;

import pengrui.javagl.abstraction.scenes.ISceneManager;
import pengrui.javagl.abstraction.util.PlatformInterface;

public interface IGame {
	IGame setSceneManager(ISceneManager sm);
	ISceneManager getSceneManager();
	boolean gameIsOver();
	IGame setGameIsOver(boolean over);
	/**
	 * 保存相关需要持久化的数据 
	 * 如果是网络游戏则需要做最后的挥手动作 如存储相关数据 通知下线等等
	 */
	void shutdown();
	/**
	 * 该游戏是否会使用网络
	 * @return
	 */
	boolean isSupportNet();
	default void start(){
		PlatformInterface.current.createDispaly();
		//TODO do some check
		this.getSceneManager().setGame(this);
		//TODO do some check
		this.getSceneManager().startScene();
		
		this.shutdown();
		PlatformInterface.current.closeDispaly();
		this.getSceneManager().destroy();
	}
}
