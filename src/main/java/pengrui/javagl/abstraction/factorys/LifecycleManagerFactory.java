package pengrui.javagl.abstraction.factorys;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pengrui.javagl.abstraction.basics.Factoryable;
import pengrui.javagl.abstraction.cores.Lifecyclable;
import pengrui.javagl.abstraction.managers.AbstractLifecycleManager;
import pengrui.javagl.abstraction.managers.ILifecyclableManager;

public interface LifecycleManagerFactory extends Factoryable<ILifecyclableManager>{
	ILifecyclableManager instance();
	
	
	ILifecyclableManager INSTANCE = new AbstractLifecycleManager() {
		Set<Lifecyclable> beans //这个必须尽早初始化，来放至需要初始化的对象；
			= new HashSet<Lifecyclable>();
		
		public void init(){
			super.init();
		}
		
		@Override
		public Collection<Lifecyclable> getAll() {
			return beans;
		}
	};
	public static ILifecyclableManager getInstance(){
		return INSTANCE;
	}
}
