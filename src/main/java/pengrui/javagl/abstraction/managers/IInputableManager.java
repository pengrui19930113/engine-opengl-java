package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.util.LogUtil;
/**
 * 后续需要定义事件 来完善输入管理类
 * @author Administrator
 *
 */
public interface IInputableManager extends Manageable<Inputable>{
	
	void inputs();//捕获事件 然后分发事件
	
	public static void register(IInputableManager im,Inputable bean) {
		if(1 == bean.getInputDepth()){
			Manageable.register(im, bean);
		}else{
			LogUtil.debug("is not one level object, ignore register");
		}
	}

	public static void unregister(IInputableManager im,Inputable bean) {
		if(1 == bean.getInputDepth()){
			Manageable.unregister(im, bean);
		}else{
			LogUtil.debug("is not one level object, ignore unregister");
		}
	}
	
	public static void destroy(IInputableManager im){
		Manageable.destroy(im);
	}
	
	public static void inputs(IInputableManager im) {
		// 通常每个场景的输入处理都不太一样 //TODO
		Manageable.checkManager(im);
		for (Inputable input : im.getAll()) 
			if(input.isEnableInput())
				im.inputs();
		
	}

}
