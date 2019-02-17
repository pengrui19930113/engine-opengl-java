package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.animation.Animationable;

/**
 * 每一帧改变对象的成员变量
 * @author Administrator
 *
 */
public interface Actionable {
	Actionable getActionParent();
	void setActionParent(Actionable parent);
	boolean isEnableAction();
	void setEnableAction(boolean en);
	void actions(long delteTime);//循环处理子节点
	void onAction(long delteTime); // 更新显示状态， 比如做动画的时候就需要通过该函数改变当前显示动画模型的哪一帧
	boolean isEnableChildrenAction();
	void setEnableChildrenAction(boolean en);
	Collection<Actionable> getActionableChildren();
	void addActionableChild(Actionable child);
	void removeActionableChild(Actionable child);
	boolean hasAnimatation();
	Collection<Animationable> getAnimations();
	void setAnimation(Animationable animation);
	boolean isEnableAnimation();
	public static void actions(Actionable action,long delteTime){
		if(null == action ||delteTime <0L)
			return ;
		
		Collection<Actionable> children = action.getActionableChildren();
		if(null!=children && !children.isEmpty()&&action.isEnableChildrenAction())
			for (Actionable child : children) 
				if(child.isEnableAction())
					child.actions(delteTime);
		
		if(action.isEnableAction()){
			
			if(action.isEnableAnimation())
				for(Animationable ani:action.getAnimations())
					ani.onAnimation();
				
			action.onAction(delteTime);
		}
	}
}
