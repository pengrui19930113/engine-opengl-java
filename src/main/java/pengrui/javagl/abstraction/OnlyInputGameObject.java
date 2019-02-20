package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;

public abstract class OnlyInputGameObject extends GenericGameObject{
	
	
	public OnlyInputGameObject() {
		ActionManagerFactory.getInstance().unregister(this);
		DrawManagerFactory.getInstance().unregister(this);
	}

	@Override
	public void onDraw() {
		//nothing
	}
	
	@Override
	public void onAction(long delteTime) {
		//nothing
	}
	
	@Override
	public boolean isEnableInput() {
		return true;
	}
	
	@Override
	public boolean isEnableChildrenInput() {
		return true;
	}
}
