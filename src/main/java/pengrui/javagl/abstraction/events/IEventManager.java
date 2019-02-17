package pengrui.javagl.abstraction.events;

import java.util.Queue;

import pengrui.javagl.abstraction.cores.Inputable;
import pengrui.javagl.abstraction.util.ExceptionUtil;
import pengrui.javagl.abstraction.util.LogUtil;

public interface IEventManager {
	/**
	 * 送到队列中统一处理
	 * @param evn
	 */
	void postEvent(IEvent evn);

	/**
	 * 直接回调目标对象的方法
	 * @param evn
	 */
	void sendEvent(IEvent evn);

	Queue<IEvent> getEventQueue();

	static boolean checkParam(IEventManager iem, IEvent evn ,String msg){
		if(null == iem 
				|| null == evn 
				|| null == evn.getSource()
				|| null == evn.getDestination()
				|| null == evn.getEventType()
				){
			LogUtil.debug(msg);
			return false;
		}
		
		return true;
	}
	
	public static void sendEvent(IEventManager iem, IEvent evn){
		
		if(!checkParam(iem, evn,"send event invalid"))
			return;
		
		evn.getDestination().inputs(evn);
	}
	
	public static void postEvent(IEventManager iem, IEvent evn) {
		
		if(!checkParam(iem, evn,"post event invalid"))
			return;

		Queue<IEvent> queue = iem.getEventQueue();
		ExceptionUtil.ifNullThrowRuntimeException(queue, "message queue must init");
		if (queue.offer(evn)) {
			LogUtil.debug("event post success");
		} else {
			LogUtil.debug(String.format("event put failure, the event class:%s,src class:%s,dest class:%s",
					evn.getClass(), evn.getSource().getClass(), evn.getDestination().getClass()));
		}
	}

	public static void handle(IEventManager em, Object src, Inputable dest, IEvent evn,boolean immediately) {
		if (
				null == dest 
				|| null == src 
				|| src == dest
				|| null == em 
				|| null == evn 
				|| null == evn.getEventType()
				){
			LogUtil.debug("handle invalid");
			return;
		}
		evn.setSource(src);
		evn.setDestination(dest);
		
		if(immediately){
			em.sendEvent(evn);
		}else{
			em.postEvent(evn);
		}

	}
}
