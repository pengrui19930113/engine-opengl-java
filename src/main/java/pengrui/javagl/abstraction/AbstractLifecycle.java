package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.cores.Lifecyclable;
import pengrui.javagl.abstraction.factorys.LifecycleManagerFactory;

public abstract class AbstractLifecycle implements Lifecyclable{

	public AbstractLifecycle() {
		LifecycleManagerFactory.getInstance().register(this);
	}
}
