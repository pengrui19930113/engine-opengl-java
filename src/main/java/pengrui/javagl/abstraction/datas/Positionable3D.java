package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector3f;

public interface Positionable3D extends Varaible3f{
	Vector3f getPosition();
	void setPosition(float x,float y,float z);
	void setPosition(Vector3f pos);
	@Override
	public Vector3f get(Vector3f... params);// 这个参数无实际意义，只是为了函数重载
	@Override
	public void set(Vector3f param);
}
