package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.LogUtil;

/**
 * 更新每一帧画面有关的行为
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public interface Drawable{
	
	boolean isEnableDraw();
	void setEnableDraw(boolean en);
	void draws();
	void onDraw();
	boolean isEnableDrawChildren();
	void setEnableDrawChildren(boolean en);
	
	Shaderable getShader();
	void setShader(Shaderable shader);
	
	public static void draws(Drawable draw){
		if(null == draw){
			LogUtil.debug("draw is null, invalid draws");
			return ;
		}
		
		if(!(draw instanceof HasChildrenable)){
			LogUtil.info("draw not instance of the HasChildrenable ,return");
			return;
		}
		Collection<Drawable> children = (Collection<Drawable>) ((HasChildrenable<?>)draw).getChildren();
		if(null!=children && !children.isEmpty()&&draw.isEnableDrawChildren()){
			for (Drawable child : children){
				if(!(child instanceof Drawable)){
					LogUtil.info("child is not instance of the Drawable , return");
					return;
				}				
				if(child.isEnableDraw())
					child.draws();
			} 
		}
		
		DebugUtil.depthInfo(((HasChildrenable<?>)draw).getDepth(), draw.getClass());
		
		if(draw.isEnableDraw())
			draw.onDraw();
	}
}
