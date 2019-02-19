package pengrui.javagl.abstraction.factorys;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pengrui.javagl.abstraction.basics.Factoryable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.managers.AbstractActionManager;
import pengrui.javagl.abstraction.managers.IActionableManager;

public interface ActionManagerFactory extends Factoryable<IActionableManager>{
	
	IActionableManager instance();

	static class Impl extends AbstractActionManager{
		
		Set<Actionable> beans= new HashSet<Actionable>();
		
		@Override
		public Collection<Actionable> getAll() {
			return beans;
		}
		
		@Override
		public void destroy() {
			super.destroy();
			beans = null; //help GC
		}

		@Override
		public void init() {
			//nothing
		}
	}
	
	static final IActionableManager INSTANCE = new Impl();
	
	public static IActionableManager getInstance(){
		return INSTANCE;
	}
}
