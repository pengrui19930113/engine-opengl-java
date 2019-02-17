package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.factorys.LifecycleManagerFactory;

public abstract class AbstractDrawManager implements IDrawableManager{
	public AbstractDrawManager() {
		//上下文 接口还没有设计完 ， 所以暂时用工厂来获取周期管理对象
		//对于每个场景 都有上下文来获取周期管理对象
		LifecycleManagerFactory.getInstance().register(this);
	}
	
	@Override
	public void register(Drawable bean) {
		IDrawableManager.register(this, bean);
	}

	@Override
	public void unregister(Drawable bean) {
		IDrawableManager.unregister(this, bean);
	}

	@Override
	public void destroy() {
		if(null!=this.getAll())
			this.getAll().clear();
	}

	@Override
	public void draws() {
		IDrawableManager.draws(this);
	}

}