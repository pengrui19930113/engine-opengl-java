package pengrui.javagl.abstraction.datas;

import pengrui.javagl.abstraction.basics.Vector2f;

import pengrui.javagl.abstraction.basics.Varaible;

public interface Varaible2f extends Varaible<Vector2f,Vector2f>{

	@Override
	public Vector2f get();

	@Override
	public void set(Vector2f param);

}
