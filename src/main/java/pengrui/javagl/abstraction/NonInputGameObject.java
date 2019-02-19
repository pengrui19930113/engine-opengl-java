package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.factorys.InputManagerFactory;

/**
 * 不会有输入处理 只有动画效果的游戏对象
 * @author Administrator
 *
 */
public class NonInputGameObject extends GenericGameObject{
	
	public NonInputGameObject() {
		InputManagerFactory.getInstance().unregister(this);
	}
}
