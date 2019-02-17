package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.factorys.LifecycleManagerFactory;
import pengrui.javagl.abstraction.util.TimeUtil;

public abstract class AbstractActionManager implements IActionableManager{
	public AbstractActionManager() {
		//上下文 接口还没有设计完 ， 所以暂时用工厂来获取周期管理对象
		//对于每个场景 都有上下文来获取周期管理对象
		LifecycleManagerFactory.getInstance().register(this);
	}
	
	@Override
	public void register(Actionable bean) {
		IActionableManager.register(this, bean);
	}

	@Override
	public void remove(Actionable bean) {
		IActionableManager.remove(this, bean);
	}

	@Override
	public void actions() {
		IActionableManager.actions(this, TimeUtil.getDelteTime());
	}

	@Override
	public void destroy() {
		IActionableManager.destroy(this);
	}
}