package pengrui.javagl.abstraction.managers;

import java.util.Collection;

import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.util.LogUtil;

/**
 * 
 * 管理所有的  一级 action 对象
 * @author Administrator
 *
 */
public interface IActionableManager extends Manageable<Actionable>{
	@Override
	void register(Actionable bean);// by Manageable
	@Override
	void unregister(Actionable bean);// by Manageable
	@Override
	Collection<Actionable> getAll();// by Manageable
	@Override
	void init(); // by Lifecyclable
	@Override
	void destroy();// by Lifecyclable
	void actions();
	
	public static <T extends Actionable>
		void register(IActionableManager am,Actionable bean){
		if(null == bean){
			LogUtil.debug("bean is null,ignore register");
			return;
		}
		
		if(1 == ((HasChildrenable<?>)bean).getDepth()){
			Manageable.register(am, bean);
		}else{
			LogUtil.debug("is not one level object, ignore register");
		}
	}
	
	public static void unregister(IActionableManager am,Actionable bean){
		
		if(null == bean){
			LogUtil.debug("bean is null,ignore remove");
			return;
		}
//		if(1 == bean.getDepth((T)null)){
			Manageable.unregister(am, bean);
//		}else{
//			LogUtil.debug("is not one level object, ignore remove");
//		}
	}
	
	public static void destroy(IActionableManager am){
		Manageable.destroy(am);
	}
	
	public static void actions(IActionableManager am,long delteTime){
		
		Manageable.checkManager(am);
		if(delteTime < 0)
			LogUtil.info("warning! the delte time is less than zero");
		
		for (Actionable actionable : am.getAll()) 
				actionable.onAction(delteTime);
		
	}
}
