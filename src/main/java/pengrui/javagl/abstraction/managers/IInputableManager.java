package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.util.LogUtil;
/**
 * 后续需要定义事件 来完善输入管理类
 * @author Administrator
 *
 */
public interface IInputableManager extends Manageable<Inputable>{
	// by Manageable
	@Override
	default void register(Inputable bean){
		IInputableManager.register(this, bean);
	}
	// by Manageable
	@Override
	default void unregister(Inputable bean){
		IInputableManager.unregister(this, bean);
	}
	
	//捕获事件 然后分发事件
	void inputs();//每个场景的输入行为都不一样
	
	public static void register(IInputableManager im,Inputable bean) {
		if(HasChildrenable.isOneLevelBean(bean)){
			Manageable.register(im, bean);
		}else{
			LogUtil.debug("is not one level object, ignore register");
		}
	}

	public static void unregister(IInputableManager im,Inputable bean) {
		if(HasChildrenable.isOneLevelBean(bean)){
			Manageable.unregister(im, bean);
		}else{
			LogUtil.debug("is not one level object, ignore unregister");
		}
	}
	
	public static void inputs(IInputableManager im) {
//		Manageable.checkManager(im);
//		for (Inputable input : im.getAll()) 
//				input.inputs(null);//TODO
//		// 通常每个场景的输入处理都不太一样 //TODO
		
	}

}
