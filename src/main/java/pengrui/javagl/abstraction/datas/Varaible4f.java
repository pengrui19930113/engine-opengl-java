package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector4f;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Varaible4f extends Varaible<Vector4f,Vector4f>{

	@Override
	public Vector4f get(Vector4f... params);// 这个参数无实际意义，只是为了函数重载

	@Override
	public void set(Vector4f param);

}
