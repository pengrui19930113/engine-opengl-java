package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.TimeUtil;

/**
 * 
 * 管理所有的  一级 action 对象
 * @author Administrator
 *
 */
public interface IActionableManager extends Manageable<Actionable>{
	@Override
	default void register(Actionable bean){// by Manageable
		IActionableManager.register(this, bean);
	}
	@Override
	default void unregister(Actionable bean){// by Manageable
		IActionableManager.unregister(this, bean);
	}
	
	default void actions(){
		IActionableManager.actions(this, TimeUtil.getDelteTime());
	}
	
	public static <T extends Actionable>
		void register(IActionableManager am,Actionable bean){
		if(HasChildrenable.isOneLevelBean(bean)){
			Manageable.register(am, bean);
		}else{
			LogUtil.debug("is not one level object, ignore register");
		}
	}
	
	public static void unregister(IActionableManager am,Actionable bean){
		if(HasChildrenable.isOneLevelBean(bean)){
			Manageable.unregister(am, bean);
		}else{
			LogUtil.debug("is not one level object, ignore remove");
		}
	}
	
	public static void actions(IActionableManager am,long delteTime){
		
		Manageable.checkManager(am);
		if(delteTime < 0)
			LogUtil.info("warning! the delte time is less than zero");
		
		for(Actionable a:am.getAll()){
			a.actions(delteTime);
		}
	}
}
