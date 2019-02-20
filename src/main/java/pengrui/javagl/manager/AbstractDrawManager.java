package pengrui.javagl.manager;

import pengrui.javagl.abstraction.managers.IDrawableManager;
import pengrui.javagl.factory.LifecycleManagerFactory;

public abstract class AbstractDrawManager implements IDrawableManager{
	public AbstractDrawManager() {
		//上下文 接口还没有设计完 ， 所以暂时用工厂来获取周期管理对象
		//对于每个场景 都有上下文来获取周期管理对象
		LifecycleManagerFactory.getInstance().register(this);
	}
}