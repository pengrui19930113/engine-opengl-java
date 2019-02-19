package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector4f;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Varaible4f extends Varaible<Vector4f,Vector4f>{

	@Override
	public Vector4f get();

	@Override
	public void set(Vector4f param);

}
