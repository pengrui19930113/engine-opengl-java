package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.cores.Inputable;

public abstract class GenericGameObject 
					implements  HasChildrenable<GenericGameObject>{
	Inputable input;
	Drawable draw;
	Actionable action;

}
