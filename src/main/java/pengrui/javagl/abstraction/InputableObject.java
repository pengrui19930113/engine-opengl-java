package pengrui.javagl.abstraction;

import java.util.Collection;
import java.util.LinkedList;

import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.events.IEvent;
import pengrui.javagl.abstraction.factorys.InputManagerFactory;

public abstract class InputableObject implements Inputable{
	Inputable parent;
	Collection<Inputable> children;
	boolean enableInput = true;
	boolean enableChildrenInput = true;
	public InputableObject() {
		InputManagerFactory.getInstance().register(this);
	}
	@Override
	public boolean isEnableInput() {
		return enableInput;
	}
	@Override
	public void setEnableInput(boolean en) {
		enableInput = en;
	}
	@Override
	public void inputs(IEvent evn) {
		Inputable.inputs(this, evn);
	}
	@Override
	public Inputable getInputableParent() {
		return parent;
	}
	@Override
	public void setInputableParent(Inputable parent) {
		this.parent = parent;
	}
	@Override
	public boolean isEnableChildrenInput() {
		return enableChildrenInput;
	}
	@Override
	public void setEnableChildrenInput(boolean en) {
		enableChildrenInput = en;
	}
	@Override
	public Collection<Inputable> getInputableChildren() {
		return children;
	}
	@Override
	public void addInpuablChild(Inputable input) {
		if(null == input)
			return;
		
		if(null == children)
			children = new LinkedList<>();
		
		children.add(input);
		input.setInputableParent(this);
	}
	@Override
	public void removeInputableChild(Inputable input) {
		if(null==children||null == input)
			return ;
		
		children.remove(input);
	}
}
