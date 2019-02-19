package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector3f;

public interface Normalable extends Varaible3f{
	boolean isSupportNormal();
	Vector3f getNormal();
	void setNormal(float x,float y,float z);
	void setNornam(Vector3f normal);
	@Override
	public Vector3f get();// 这个参数无实际意义，只是为了函数重载
	@Override
	public void set(Vector3f param);
}
