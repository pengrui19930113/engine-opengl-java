package pengrui.javagl.abstraction.managers;

import pengrui.javagl.abstraction.cores.Actionable;

public interface IActionableManager extends Manageable<Actionable>{
	void actions();
	
	public static void register(IActionableManager am,Actionable bean){
		Manageable.checkManager(am);
		am.getAll().add(bean);
	}
	
	public static void remove(IActionableManager am,Actionable bean){
		Manageable.checkManager(am);
		am.getAll().remove(bean);
	}
	
	public static void destroy(IActionableManager am){
		Manageable.checkManager(am);
		am.getAll().clear();
	}
	
	public static void actions(IActionableManager am,long delteTime){
		Manageable.checkManager(am);
		for (Actionable actionable : am.getAll()) {
			if(actionable.isEnableChildrenAction())
				actionable.actions(delteTime);
			
			if(actionable.isEnableAction())
				actionable.onAction(delteTime);
		}
	}
}
