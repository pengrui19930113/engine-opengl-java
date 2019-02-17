package pengrui.javagl.abstraction;

import java.util.Collection;
import java.util.LinkedList;

import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;

/**
 * 
 * @author Administrator
 *
 */
public abstract class DrawableObject implements Drawable{
	Drawable parent;
	Collection<Drawable> children;
	boolean enableDraw = true;
	boolean enableChildrenDraw = true;
	public DrawableObject() {
		DrawManagerFactory.getInstance().register(this);
	}
	@Override
	public boolean isEnableDraw() {
		return enableDraw;
	}
	@Override
	public void draws() {
		Drawable.draws(this);
	}
	
	@Override
	public void setEnableDraw(boolean en) {
		enableDraw = en;
	}
	@Override
	public Collection<Drawable> getDrawableChildren() {
		return children;
	}
	@Override
	public Drawable getDrawableParent() {
		return parent;
	}
	@Override
	public void setDrawableParent(Drawable parent) {
		this.parent = parent;
	}
	
	@Override
	public boolean isEnableDrawChildren() {
		return enableChildrenDraw;
	}
	@Override
	public void setEnableDrawChildren(boolean en) {
		enableChildrenDraw = en;
	}
	@Override
	public void addDrawableChild(Drawable child) {
		if(null == child)
			return;
		
		if(null == children)
			children = new LinkedList<Drawable>();
		
		children.add(child);
		child.setDrawableParent(this);
	}
	@Override
	public void removeDrawableChild(Drawable child) {
		if(null == child || null == children)
			return;
		
		children.remove(child);
	}
}
