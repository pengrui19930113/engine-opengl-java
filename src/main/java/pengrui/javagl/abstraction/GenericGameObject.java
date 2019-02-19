package pengrui.javagl.abstraction;

import java.util.Collection;

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

public class GenericGameObject 
					implements  
						HasChildrenable<GenericGameObject>
						,Drawable
						,Actionable
						,Inputable
{
	public GenericGameObject() {
		DrawManagerFactory.getInstance().register(this);
		ActionManagerFactory.getInstance().register(this);
		InputManagerFactory.getInstance().register(this);
		
		//TODO enableInput = true;//...
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
	public void onInput(IEvent evn) {
		// TODO Auto-generated method stub
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
	public void actions(long delteTime) {
		Actionable.actions(this, delteTime);
	}

	@Override
	public void onAction(long delteTime) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
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
	public void draws() {
		Drawable.draws(this);
	}

	@Override
	public void onDraw() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShader(Shaderable shader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<GenericGameObject> getChildren() {
		return children;
	}

	@Override
	public void addChild(GenericGameObject child) {
		HasChildrenable.addChild(this, child);
	}

	@Override
	public void removeChild(GenericGameObject child) {
		HasChildrenable.removeChild(this, child);
	}

	@Override
	public boolean hasTheChild(GenericGameObject child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GenericGameObject getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(GenericGameObject parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDepth(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}

}
