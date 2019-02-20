package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.basics.Lifecyclable;

public interface ILifecyclableManager<T extends Lifecyclable> extends Manageable<T>{
	
	@Override
	default void init() {
		ILifecyclableManager.init(this);
	}
	
	@Override
	default void destroy() {
		ILifecyclableManager.destroy(this);
	}
	
	public static<T extends Lifecyclable> void init(ILifecyclableManager<T> lm) {
		Manageable.checkManager(lm);
		for (Lifecyclable lifecycle : lm.getAll()) 
			lifecycle.init();
	}

	public static <T extends Lifecyclable>void destroy(ILifecyclableManager<T> lm) {
		Manageable.checkManager(lm);
		for (T lifecycle : lm.getAll()) 
			lifecycle.destroy();
		
		if(null!=lm.getAll())
			lm.getAll().clear();
	}
	
}
