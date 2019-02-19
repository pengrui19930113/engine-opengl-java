package pengrui.javagl.abstraction.managers;

import java.util.Collection;

import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.util.LogUtil;
/**
 * 后续需要定义事件 来完善输入管理类
 * @author Administrator
 *
 */
public interface IInputableManager extends Manageable<Inputable>{
	@Override
	void register(Inputable bean);// by Manageable
	@Override
	void unregister(Inputable bean);// by Manageable
	@Override
	Collection<Inputable> getAll();// by Manageable
	@Override
	void init(); // by Lifecyclable
	@Override
	void destroy();// by Lifecyclable
	void inputs();//捕获事件 然后分发事件
	
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
	
	public static void destroy(IInputableManager im){
		Manageable.destroy(im);
	}
	
	public static void inputs(IInputableManager im) {
//		Manageable.checkManager(im);
//		for (Inputable input : im.getAll()) 
//				input.inputs(null);//TODO
//		// 通常每个场景的输入处理都不太一样 //TODO
		
	}

}
