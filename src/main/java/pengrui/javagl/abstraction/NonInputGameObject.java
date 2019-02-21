package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.events.IEvent;
import pengrui.javagl.abstraction.factorys.InputManagerFactory;

/**
 * 不会有输入处理 只有动画效果的游戏对象
 * @author Administrator
 *
 */
public abstract class NonInputGameObject extends GenericGameObject{
	
	public NonInputGameObject() {
		
		enableDraw = true;
		enableAction = true;
		InputManagerFactory.getInstance().unregister(this);
	}

	@Override
	public void onInput(IEvent evn) {
		//nothing
	}
}
