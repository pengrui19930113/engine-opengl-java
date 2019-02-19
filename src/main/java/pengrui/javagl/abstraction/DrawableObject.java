package pengrui.javagl.abstraction;

import java.util.Collection;

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
//	@Override
//	public void addChild(Drawable child) {
//		if(null == child)
//			return;
//		
//		if(null == children)
//			children = new LinkedList<Drawable>();
//		
//		children.add(child);
//		child.setParent(this);
//	}
//	@Override
//	public void removeChild(Drawable child) {
//		if(null == child || null == children)
//			return;
//		
//		children.remove(child);
//	}
}
