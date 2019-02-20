package pengrui.javagl.abstraction;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.events.IEvent;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;
import pengrui.javagl.abstraction.factorys.InputManagerFactory;
import pengrui.javagl.abstraction.shaders.Shaderable;

public abstract class GenericGameObject 
					implements  
						HasChildrenable<GenericGameObject>
						,Drawable
						,Actionable
						,Inputable
{
	public GenericGameObject() {
		//TODO enableInput = true;//...
		depth = 1;
		
		DrawManagerFactory.getInstance().register(this);
		ActionManagerFactory.getInstance().register(this);
		InputManagerFactory.getInstance().register(this);
	}
	boolean enableInput;
	boolean enableChildrenInput;
	boolean enableAction;
	boolean enableChildrenAction;
	boolean enableDraw;
	boolean enableDrawChildren;
	boolean enableAnimation;
	
	Collection<Animationable> animations;
	Collection<GenericGameObject> children;
	
	GenericGameObject parent;
	int depth;
	Shaderable shader;
	int vaoID;
	int vertexCount;
	List<Integer> textureIDs;
	
	@Override
	public boolean isEnableInput() {
		return enableInput;
	}

	@Override
	public void setEnableInput(boolean en) {
		enableInput = en;
	}

	@Override
	public final void inputs(IEvent evn) {
		Inputable.inputs(this, evn);
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
	public boolean isEnableAction() {
		return enableAction;
	}

	@Override
	public void setEnableAction(boolean en) {
		enableAction = en;
	}

	@Override
	public final void actions(long delteTime) {
		Actionable.actions(this, delteTime);
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
	public Collection<Animationable> getAnimations() {
		return animations;
	}

	@Override
	public void addAnimation(Animationable animation) {
		if(null == animations)
			animations = new LinkedList<Animationable>();
		
		animations.add(animation);
	}

	@Override
	public boolean isEnableAnimation() {
		return enableAnimation;
	}
	@Override
	public void setEnableAnimation(boolean en) {
		enableAnimation = en;
	}
	@Override
	public boolean isEnableDraw() {
		return enableDraw;
	}

	@Override
	public void setEnableDraw(boolean en) {
		enableDraw = en;
	}

	@Override
	public final void draws() {
		Drawable.draws(this);
	}

	@Override
	public boolean isEnableDrawChildren() {
		return enableDrawChildren;
	}

	@Override
	public void setEnableDrawChildren(boolean en) {
		enableDrawChildren = en;
	}

	@Override
	public Shaderable getShader() {
		return shader;
	}

	@Override
	public void setShader(Shaderable shader) {
		this.shader = shader;
	}

	@Override
	public Collection<GenericGameObject> getChildren() {
		return children;
	}

	@Override
	public void setChildren(Collection<GenericGameObject> c) {
		children = c;
	}
	
	@Override
	public final void addChild(GenericGameObject child) {
		HasChildrenable.addChild(this, child);
	}

	@Override
	public void removeChild(GenericGameObject child) {
		HasChildrenable.removeChild(this, child);
	}

	@Override
	public boolean hasTheChild(GenericGameObject child) {
		return HasChildrenable.hasTheChild(this, child,false);
	}

	@Override
	public GenericGameObject getParent() {
		return parent;
	}

	@Override
	public void setParent(GenericGameObject parent) {
		this.parent = parent;
	}

	@Override
	public void setDepth(int d) {
		depth = d;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public void printInfo() {
		HasChildrenable.printInfo(this);
	}

	@Override
	public String toString() {
		return super.toString();//TODO
	}

}
