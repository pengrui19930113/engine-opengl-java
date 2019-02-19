package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector3f;

public interface Directable3D extends Varaible3f{

	@Override
	public Vector3f get();

	@Override
	public void set(Vector3f param);
//	Vector3f getDirect();
//	void setDirect(float x,float y,float z);
}
