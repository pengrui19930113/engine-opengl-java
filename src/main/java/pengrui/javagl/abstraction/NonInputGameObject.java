package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;
/**
 * 不会有输入处理 只有动画效果的游戏对象
 * @author Administrator
 *
 */
public abstract class NonInputGameObject 
		implements Actionable,Drawable{
	
	public NonInputGameObject() {
		ActionManagerFactory.getInstance().register(this);
		DrawManagerFactory.getInstance().register(this);
	}
	
}
