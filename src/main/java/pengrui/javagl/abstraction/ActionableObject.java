package pengrui.javagl.abstraction;

import java.util.Collection;
import java.util.LinkedList;

import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;

public abstract class ActionableObject implements Actionable{
	
	Actionable parent;
	boolean enableAction = true;
	boolean enableChildrenAction = true;
	
	Collection<Actionable> children;
	
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
	public void addActionableChild(Actionable child) {
		if(null == children)
			children = new LinkedList<>();
		
		children.add(child);
		child.setActionParent(this);
	}
	@Override
	public Actionable getActionParent() {
		return parent;
	}
	@Override
	public void setActionParent(Actionable parent) {
		this.parent = parent;
	}
	@Override
	public Collection<Actionable> getActionableChildren() {
		return children;
	}
	@Override
	public void removeActionableChild(Actionable child) {
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
}
