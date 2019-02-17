package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Inputable;
/**
 * 后续需要定义事件 来完善输入管理类
 * @author Administrator
 *
 */
public interface IInputableManager extends Manageable<Inputable>{
	void inputs();//捕获事件 然后分发事件
	
	public static void register(IInputableManager im,Inputable bean) {
		Manageable.checkManager(im);
		im.getAll().add(bean);
	}

	public static void remove(IInputableManager im,Inputable bean) {
		Manageable.checkManager(im);
		im.getAll().remove(bean);
	}

	public static void inputs(IInputableManager im) {
		// 通常每个场景的输入处理都不太一样 所以无法做统一处理
		
		
	}

}
