package pengrui.javagl.abstraction.managers;

import java.util.Collection;

import pengrui.javagl.abstraction.cores.Lifecyclable;
import pengrui.javagl.abstraction.util.CheckUtil;

public interface Manageable<T> extends Lifecyclable {
	
	void register(T bean);
	void unregister(T bean);
	Collection<T> getAll();
	void init(); // by Lifecyclable
	void destroy();// by Lifecyclable

	public static void checkManager(Manageable<?> m) {
		if (null == m || null == m.getAll())
			throw new RuntimeException(
					"manager must is not null and must be have a container , manager class:" + m.getClass());

	}

	public static <T> void unregister(Manageable<T> manager, T bean) {
		checkManager(manager);
		if (CheckUtil.paramNotNull(bean)) 
			manager.getAll().remove(bean);
	}

	public static <T> void register(Manageable<T> manager, T bean) {
		checkManager(manager);
		if (CheckUtil.paramNotNull(bean)) 
			manager.getAll().add(bean);
	}
	
	public static <T> void destroy(Manageable<T> manager) {
		Manageable.checkManager(manager);
		manager.getAll().clear();
	}
}
