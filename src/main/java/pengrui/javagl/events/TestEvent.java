package pengrui.javagl.events;

import java.awt.Event;

public abstract class TestEvent extends Event
{
	static net.java.games.input.Event evn = new net.java.games.input.Event();
	private static final long serialVersionUID = 1378798375215732699L;

	public TestEvent(Object target, int id, Object arg) {
		super(target, id, arg);
	}

}
