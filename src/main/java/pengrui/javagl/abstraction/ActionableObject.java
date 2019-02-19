package pengrui.javagl.abstraction;

import java.util.Collection;
import java.util.LinkedList;

import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;

public class ActionableObject implements Actionable,HasChildrenable<ActionableObject>{
	
	ActionableObject parent;
	boolean enableAction = true;
	boolean enableChildrenAction = true;
	
	Collection<ActionableObject> children;
	
	public ActionableObject() {
		ActionManagerFactory.getInstance().register(this);
	}
	@Override
	public boolean isEnableAction() {
		return enableAction;
	}
	@Override
	public void actions(long delteTime) {
		Actionable.actions(this, delteTime);
	}
	@Override
	public void setEnableAction(boolean en) {
		enableAction = en;
	}
	@Override
	public void addChild(ActionableObject child) {
		if(null == children)
			children = new LinkedList<>();
		
		children.add(child);
		child.setParent(this);
	}
	@Override
	public ActionableObject getParent() {
		return parent;
	}
	@Override
	public void setParent(ActionableObject parent) {
		this.parent = parent;
	}
	@Override
	public Collection<ActionableObject> getChildren() {
		return children;
	}
	@Override
	public void removeChild(ActionableObject child) {
	
		if(null == child || null == children)
			return;
		
		children.remove(child);
	}
	@Override
	public boolean isEnableChildrenAction() {
		return enableChildrenAction;
	}
	@Override
	public void setEnableChildrenAction(boolean en) {
		enableChildrenAction = en;
	}
	@Override
	public void setDepth(int d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public boolean hasTheChild(ActionableObject child) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAction(long delteTime) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean hasAnimatation() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Collection<Animationable> getAnimations() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAnimation(Animationable animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isEnableAnimation() {
		// TODO Auto-generated method stub
		return false;
	}
}
