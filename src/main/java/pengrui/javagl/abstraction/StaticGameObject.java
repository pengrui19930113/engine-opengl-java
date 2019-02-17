package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.cores.Drawable;

/**
 * 静态游戏对象 只有静态模型 不会改变当前显示的帧动画 就只有一个模型
 * @author Administrator
 *
 */
public abstract class StaticGameObject  
		extends DrawableObject 
		implements Drawable{

	public StaticGameObject() {
	}
	
}
