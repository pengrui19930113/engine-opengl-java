package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.events.IEvent;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.InputManagerFactory;

/**
 * 静态游戏对象 只有静态模型 不会改变当前显示的帧动画 就只有一个模型
 * @author Administrator
 *
 */
public abstract class StaticGameObject  
		extends GenericGameObject{

	public StaticGameObject() {
		enableDraw = true;
		InputManagerFactory.getInstance().unregister(this);
		ActionManagerFactory.getInstance().unregister(this);
	}
	
	@Override
	public boolean isEnableAction() {
		return false;
	}
	
	@Override
	public boolean isEnableInput() {
		return false;
	}

	@Override
	public void onAction(long delteTime) {
		//nothing
	}

	@Override
	public void onInput(IEvent evn) {
		//nothing;
	}
}
