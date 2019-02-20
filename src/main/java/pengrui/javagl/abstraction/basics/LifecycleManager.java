package pengrui.javagl.abstraction.basics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pengrui.javagl.abstraction.managers.ILifecyclableManager;
import pengrui.javagl.abstraction.managers.Manageable;

public class LifecycleManager implements ILifecyclableManager<Lifecyclable>{
	Set<Lifecyclable> beans //这个必须尽早初始化，来放至需要初始化的对象；
		= new HashSet<Lifecyclable>();

	@Override
	public void register(Lifecyclable bean) {
		Manageable.register(this, bean);
	}

	@Override
	public void unregister(Lifecyclable bean) {
		Manageable.unregister(this, bean);
	}

	@Override
	public Collection<Lifecyclable> getAll() {
		return beans;
	}

}
