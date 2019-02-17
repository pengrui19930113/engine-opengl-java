package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.LogUtil;

/**
 * 更新每一帧画面有关的行为
 * @author Administrator
 *
 */
public interface Drawable {
	
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
	int getDrawDepth();
	void setDrawDepth(int d);
	
	Shaderable getShader();
	void setShader(Shaderable shader);
	boolean isEnableCulling();
	void senEnableCulling();
	
	public static void draws(Drawable draw){
		if(null == draw){
			LogUtil.debug("draw is null, invalid draws");
			return ;
		}
		
		Collection<Drawable> children = draw.getDrawableChildren();
		if(null!=children && !children.isEmpty()&&draw.isEnableDrawChildren())
			for (Drawable child : children) 
				if(child.isEnableDraw())
					child.draws();
		
		DebugUtil.depthInfo(draw.getDrawDepth(), draw.getClass());
		
		if(draw.isEnableDraw())
			draw.onDraw();
	}
	
	public static void setDrawableParent(Drawable child,Drawable parent){
		if(null == child||null == parent){
			LogUtil.debug("set parent param invalid!");
			return;
		}
		Drawable pparent;
		while(null!=(pparent = parent.getDrawableParent())){
			if(child == pparent){//避免成环
				LogUtil.info("parent's parents has child , that is loop , invalid set parent , stop it");
				return;
			}
		}
		child.setDrawableParent(parent);
		if(parent.getDrawDepth() < 1)
			LogUtil.info("warning! parent's depth is less than 1 , there is error maybe!");
		
		child.setDrawDepth(1+parent.getDrawDepth());
	}
	
}
