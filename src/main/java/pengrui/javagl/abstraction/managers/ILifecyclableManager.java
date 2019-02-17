package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Lifecyclable;

public interface ILifecyclableManager extends Manageable<Lifecyclable>{
	

	public static void register(ILifecyclableManager lm,Lifecyclable bean) {
		Manageable.checkManager(lm);
		lm.getAll().add(bean);
	}

	public static void remove(ILifecyclableManager lm,Lifecyclable bean) {
		Manageable.checkManager(lm);
		lm.getAll().remove(bean);
	}

	public static void init(ILifecyclableManager lm) {
		Manageable.checkManager(lm);
		for (Lifecyclable lifecycle : lm.getAll()) 
			lifecycle.init();
	}

	public static void destroy(ILifecyclableManager lm) {
		Manageable.checkManager(lm);
		for (Lifecyclable lifecycle : lm.getAll()) 
			lifecycle.destroy();
		
		if(null!=lm.getAll())
			lm.getAll().clear();
	}
	
}
