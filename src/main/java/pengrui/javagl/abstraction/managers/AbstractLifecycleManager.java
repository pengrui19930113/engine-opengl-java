package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Lifecyclable;

public abstract class AbstractLifecycleManager implements ILifecyclableManager{
	
	public AbstractLifecycleManager() {
	}

	@Override
	public void register(Lifecyclable bean) {
		ILifecyclableManager.register(this, bean);
	}

	@Override
	public void remove(Lifecyclable bean) {
		ILifecyclableManager.remove(this, bean);
	}

	@Override
	public void init() {
		ILifecyclableManager.init(this);
	}

	@Override
	public void destroy() {
		ILifecyclableManager.destroy(this);
	}
	



}