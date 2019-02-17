package pengrui.javagl.abstraction.events;

import pengrui.javagl.abstraction.cores.Inputable;

public class AbstractEvent 
						//extends Event 
						implements IEvent// 
{
	static net.java.games.input.Event evn = new net.java.games.input.Event();
//	private static final long serialVersionUID = 1378798375215732699L;
//
//	public AbstractEvent(Object target, int id, Object arg) {
//		super(target, id, arg);
//	}
	
	Object src;
	Inputable dest;
	int type;
	@Override
	public Inputable getDestination() {
		return dest;
	}
	@Override
	public void setSource(Object evn) {
		src = evn;
	}
	@Override
	public void setDestination(Inputable evn) {
		dest = evn;
	}
	@Override
	public Object getSource() {
		return src;
	}
	@Override
	public Object getEventType() {
		return type;
	}

}
