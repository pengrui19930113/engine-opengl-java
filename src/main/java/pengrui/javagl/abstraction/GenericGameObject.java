package pengrui.javagl.abstraction;

import java.util.Collection;
import java.util.Map;

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
		
		depth = 1;
		enableInput = true;
		enableChildrenInput = true;
		enableAction = true;
		enableChildrenAction = true;
		enableDraw = true;
		enableDrawChildren = true;
		enableAnimation = true;
		parent = null;
		
		DrawManagerFactory.getInstance().register(this);//后续由上下文来获取管理器注册
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
	int vaoID;//TODO
	int vertexCount;
	Map<String,Integer> textureIDs;//多重 纹理 的纹理ID
//	List<Integer> textureIDs;
	
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
	public String toString() {
		return super.toString();//TODO
	}

	@Override
	public void setAnimations(Collection<Animationable> animations) {
		this.animations = animations;
	}
}
