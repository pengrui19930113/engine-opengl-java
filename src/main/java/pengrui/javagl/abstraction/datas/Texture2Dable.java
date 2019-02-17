package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector2f;

public interface Texture2Dable extends Textureable,Varaible2f{
	boolean isSupport2DTexture();
	Vector2f getTexture2DCoord();
	void setTexture2DCoord(Vector2f v2f);
	void setTexture2DCoord(float x,float y);
	@Override
	Vector2f get(Vector2f... floats);// 这个参数无实际意义，只是为了函数重载
	@Override
	void set(Vector2f param);
}
