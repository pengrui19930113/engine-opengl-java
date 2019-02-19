package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector3f;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Varaible3f extends Varaible<Vector3f,Vector3f>{

	@Override
	public Vector3f get();

	@Override
	public void set(Vector3f param);

}
