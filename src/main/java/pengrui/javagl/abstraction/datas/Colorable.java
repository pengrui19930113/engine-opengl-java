package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector3f;
import pengrui.javagl.abstraction.basics.Vector4f;

public interface Colorable extends Varaible4f{
	void set(float r,float g,float b,float alpha);
	void set(float r,float g,float b);
	void set(Vector3f color);
	@Override
	public void set(Vector4f param);
	@Override
	public Vector4f get();// 这个参数无实际意义，只是为了函数重载
	Vector3f get3f();
	float getRed();
	void setRed(float r); 
	float getGreen();
	void setGreen(float g); 
	float getBlue();
	void setBlue(float g); 
	float getAlpha();
	void setAlpha(float alpha);
}
