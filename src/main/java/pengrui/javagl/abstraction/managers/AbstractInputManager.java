package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.factorys.LifecycleManagerFactory;

public abstract class AbstractInputManager implements IInputableManager{
	public AbstractInputManager() {
		//上下文 接口还没有设计完 ， 所以暂时用工厂来获取周期管理对象
		//对于每个场景 都有上下文来获取周期管理对象
		LifecycleManagerFactory.getInstance().register(this);
	}
	
	@Override
	public void register(Inputable bean) {
		IInputableManager.register(this, bean);
	}

	@Override
	public void remove(Inputable bean) {
		IInputableManager.remove(this, bean);
	}
	
	@Override
	public void destroy() {
		if(null!=this.getAll())
			this.getAll().clear();
	}
}