package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.LogUtil;

/**
 * 每一帧改变对象的成员变量
 * @author Administrator
 *
 */

@SuppressWarnings("unchecked")
public interface Actionable {
	
	boolean isEnableAction();
	void setEnableAction(boolean en);
	void actions(long delteTime);//循环处理子节点
	void onAction(long delteTime); // 更新显示状态， 比如做动画的时候就需要通过该函数改变当前显示动画模型的哪一帧
	boolean isEnableChildrenAction();
	void setEnableChildrenAction(boolean en);
	
	boolean hasAnimatation();
	Collection<Animationable> getAnimations();
	void setAnimation(Animationable animation);
	boolean isEnableAnimation();
	
	public static void actions(Actionable action,long delteTime){
		
		if(null == action ||delteTime <0L){
			LogUtil.debug("action is null or delte time less than zero, ignore invalid actions");
			return ;
		}
		
		if(!(action instanceof HasChildrenable)){
			LogUtil.info("action not instance of the HasChildrenable , return");
			return;
		}
		Collection<Actionable> children = (Collection<Actionable>) ((HasChildrenable<?>)action).getChildren();
		if(null!=children && !children.isEmpty()&&action.isEnableChildrenAction()){
			for (Actionable child : children){
				if(!(child instanceof Actionable)){
					LogUtil.info("child is not instance of the Actionable , return");
					return;
				}
				if(child.isEnableAction())
					child.actions(delteTime);
			}
		}
		
		DebugUtil.depthInfo(((HasChildrenable<?>)action).getDepth(), action.getClass());
		
		if(action.isEnableAction()){
			
			if(action.isEnableAnimation())
				for(Animationable ani:action.getAnimations())
					ani.onAnimation();
				
			action.onAction(delteTime);
		}
	}
	
}
