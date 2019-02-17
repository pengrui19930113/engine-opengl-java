package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.LogUtil;

/**
 * 每一帧改变对象的成员变量
 * @author Administrator
 *
 */
public interface Actionable {
	
	Actionable getActionableParent();
	void setActionableParent(Actionable parent);
	boolean isEnableAction();
	void setEnableAction(boolean en);
	void actions(long delteTime);//循环处理子节点
	void onAction(long delteTime); // 更新显示状态， 比如做动画的时候就需要通过该函数改变当前显示动画模型的哪一帧
	boolean isEnableChildrenAction();
	void setEnableChildrenAction(boolean en);
	Collection<Actionable> getActionableChildren();
	void addActionableChild(Actionable child);
	void removeActionableChild(Actionable child);
	int getActionDepth(); // 通常 深度 >= 1
	void setActionDepth(int d);
	
	boolean hasAnimatation();
	Collection<Animationable> getAnimations();
	void setAnimation(Animationable animation);
	boolean isEnableAnimation();
	
	public static void actions(Actionable action,long delteTime){
		
		if(null == action ||delteTime <0L){
			LogUtil.debug("action is null or delte time less than zero, ignore invalid actions");
			return ;
		}
		
		Collection<Actionable> children = action.getActionableChildren();
		if(null!=children && !children.isEmpty()&&action.isEnableChildrenAction())
			for (Actionable child : children) 
				if(child.isEnableAction())
					child.actions(delteTime);
		
		DebugUtil.depthInfo(action.getActionDepth(), action.getClass());
		
		if(action.isEnableAction()){
			
			if(action.isEnableAnimation())
				for(Animationable ani:action.getAnimations())
					ani.onAnimation();
				
			action.onAction(delteTime);
		}
	}
	
	public static void setActionableParent(Actionable child,Actionable parent){
		
		if(null == child||null == parent){
			LogUtil.debug("set parent param invalid!");
			return;
		}
		
		Actionable pparent;
		while(null!=(pparent = parent.getActionableParent())){
			if(child == pparent){//避免成环
				LogUtil.info("warning! parent's parents has child , that is loop , invalid set parent , stop it");
				return;
			}
		}
		child.setActionableParent(parent);
		if(parent.getActionDepth() < 1)
			LogUtil.info("warning! parent's depth is less than 1 , there is error maybe!");
		
		child.setActionDepth(parent.getActionDepth()+1);
	}
}
