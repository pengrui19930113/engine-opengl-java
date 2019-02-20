package pengrui.javagl.factory;

import pengrui.javagl.abstraction.basics.Factoryable;
import pengrui.javagl.abstraction.basics.Lifecyclable;
import pengrui.javagl.abstraction.basics.LifecycleManager;
import pengrui.javagl.abstraction.managers.ILifecyclableManager;

public interface LifecycleManagerFactory extends Factoryable<ILifecyclableManager<Lifecyclable>>{
	ILifecyclableManager<Lifecyclable> instance();
	
	
	ILifecyclableManager<Lifecyclable> INSTANCE = new LifecycleManager();
	public static ILifecyclableManager<Lifecyclable> getInstance(){
		return INSTANCE;
	}
}
