package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector3f;

public interface Texture3Dable extends Textureable,Varaible3f{
	boolean isSupport3DTexture();
	Vector3f getTexture2DCoord();
	void setTexture3DCoord(Vector3f v3f);
	void setTexture3DCoord(float x,float y,float z);
	@Override
	Vector3f get(Vector3f... floats);// 这个参数无实际意义，只是为了函数重载
	@Override
	void set(Vector3f param);
}
