package pengrui.javagl.abstraction.cores;

import java.util.Collection;

import pengrui.javagl.abstraction.events.AbstractEvent;
import pengrui.javagl.abstraction.events.IEvent;
import pengrui.javagl.abstraction.events.IEventManager;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.LogUtil;

/**
 * 每一个对象具备处理输入的能力
 * @author Administrator
 *
 */
public interface Inputable {
	
	Inputable getInputableParent();
	void setInputableParent(Inputable parent);
	boolean isEnableInput();
	void setEnableInput(boolean en);
	void inputs(IEvent evn);
	void onInput(IEvent evn);
	boolean isEnableChildrenInput();
	void setEnableChildrenInput(boolean en);
	Collection<Inputable> getInputableChildren();
	void addInpuablChild(Inputable input);
	void removeInputableChild(Inputable input);
	int getInputDepth();
	void setInputDepth(int d);
	/**
	 * {@link Inputable#onInput(AbstractEvent)}
	 * @param bean 
	 */
	public static void testInput(Inputable bean){//TODO 测试代码
		// 通常处理方式为 接受到外部事件 后进行内部数据变更，如果需要和其他组件交互
		// bean.innerInputProcess();
		IEvent evn = new AbstractEvent(){};
		IEventManager.handle(null,bean,bean,evn,false);
	}
	
	public static void inputs(Inputable input,IEvent evn){
		
		if(
				null == input 
				|| null == evn //事件处理源 或 事件为空 则不处理
				|| null==evn.getEventType()// 事件类型为空 则不处理
				|| null == evn.getDestination() //事件的目标对象为空 则不处理
				|| null == evn.getSource() // 事件的事件源为空 则不处理
				|| evn.getDestination() == evn  //事件的目标对象是源对象 则不处理 避免递归
				|| evn.getSource()!=input // 事件的事件源不是事件发生者自身 则不处理
				) {
			LogUtil.debug("input param invalid");
			return ;
		}
		
		Collection<Inputable> children = input.getInputableChildren();
		if(null!=children && !children.isEmpty()&&input.isEnableChildrenInput())
			for (Inputable child : children) 
				if(child.isEnableInput())
					child.inputs(evn);
		
		DebugUtil.depthInfo(input.getInputDepth(), input.getClass());
		
		if(input.isEnableInput())
			input.onInput(evn);
	}
	
	public static void setInputableParent(Inputable child,Inputable parent){
		if(null == child||null == parent){
			LogUtil.debug("set parent param invalid!");
			return;
		}
		Inputable pparent;
		while(null!=(pparent = parent.getInputableParent())){
			if(child == pparent){//避免成环
				LogUtil.info("parent's parents has child , that is loop , invalid set parent , stop it");
				return;
			}
		}
		child.setInputableParent(parent);
		if(parent.getInputDepth() < 1)
			LogUtil.info("warning! parent's depth is less than 1 , there is error maybe!");
		
		child.setInputDepth(1+parent.getInputDepth());
	}
}
