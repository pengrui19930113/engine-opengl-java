package pengrui.javagl.abstraction.datas;

import org.lwjgl.util.vector.Vector2f;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Varaible2f extends Varaible<Vector2f,Vector2f>{

	@Override
	public Vector2f get(Vector2f... params);// 这个参数无实际意义，只是为了函数重载

	@Override
	public void set(Vector2f param);

}
