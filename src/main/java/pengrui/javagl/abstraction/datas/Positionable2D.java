package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector2f;

public interface Positionable2D extends Varaible2f{
	Vector2f getPosition();
	void setPosition(float x,float y);
	void setPosition(Vector2f pos);
	@Override
	public Vector2f get();// 这个参数无实际意义，只是为了函数重载
	@Override
	public void set(Vector2f param);
}
