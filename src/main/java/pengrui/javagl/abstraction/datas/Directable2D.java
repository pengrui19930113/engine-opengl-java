package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector3f;

public interface Directable2D extends Varaible3f{

	@Override
	public Vector3f get(Vector3f... params);

	@Override
	public void set(Vector3f param);
	Vector3f getDirect();
	void setDirect(float x,float y,float z);
}
