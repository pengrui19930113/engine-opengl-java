package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Texture1Dable extends Textureable,Varaible<Float,Float>{
	boolean isSupport1DTexture();
	float getTexture1DCoord();
	void setTexture1DCoord(float x);
	@Override
	Float get(Float... floats);// 这个参数无实际意义，只是为了函数重载
	@Override
	void set(Float param);
}
