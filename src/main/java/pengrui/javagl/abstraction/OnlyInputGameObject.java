package pengrui.javagl.abstraction;

import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;

public abstract class OnlyInputGameObject extends GenericGameObject{
	
	
	public OnlyInputGameObject() {
		
		enableInput = true;
		ActionManagerFactory.getInstance().unregister(this);
		DrawManagerFactory.getInstance().unregister(this);
	}
	
	@Override
	public boolean isEnableInput() {
		return false;
	}
	@Override
	public boolean isEnableAction() {
		return false;
	}
	
	@Override
	public void onDraw() {
		//nothing
	}
	
	@Override
	public void onAction(long delteTime) {
		//nothing
	}
	
}
