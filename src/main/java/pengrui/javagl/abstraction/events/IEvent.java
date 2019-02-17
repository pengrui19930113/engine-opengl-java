package pengrui.javagl.abstraction.events;

import pengrui.javagl.abstraction.cores.Inputable;

public interface IEvent {
	Inputable getDestination();
	void setDestination(Inputable dest);
	Object getSource();
	void setSource(Object src);
	Object getEventType();
}
