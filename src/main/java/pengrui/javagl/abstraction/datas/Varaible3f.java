package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Varaible3f extends Varaible<Vector3f,Vector3f>{

	@Override
	public Vector3f get(Vector3f... params);// 这个参数无实际意义，只是为了函数重载

	@Override
	public void set(Vector3f param);

}
