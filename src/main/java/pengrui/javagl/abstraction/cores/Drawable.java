package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.shaders.Shaderable;

/**
 * 更新每一帧画面有关的行为
 * @author Administrator
 *
 */
public interface Drawable {
	Shaderable getShader();
	void setShader(Shaderable shader);
	Drawable getDrawableParent();
	void setDrawableParent(Drawable parent);
	boolean isEnableDraw();
	void setEnableDraw(boolean en);
	void draws();
	void onDraw();
	boolean isEnableDrawChildren();
	void setEnableDrawChildren(boolean en);
	Collection<Drawable> getDrawableChildren();
	void addDrawableChild(Drawable child);
	void removeDrawableChild(Drawable child);
	boolean isEnableCulling();
	void senEnableCulling();
	
	public static void draws(Drawable draw){
		if(null == draw)
			return ;
		
		Collection<Drawable> children = draw.getDrawableChildren();
		if(null!=children && !children.isEmpty()&&draw.isEnableDrawChildren())
			for (Drawable child : children) 
				if(child.isEnableDraw())
					child.draws();
		
		if(draw.isEnableDraw())
			draw.onDraw();
	}
}
