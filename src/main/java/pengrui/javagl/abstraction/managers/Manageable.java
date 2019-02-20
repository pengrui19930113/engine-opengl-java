package pengrui.javagl.abstraction.managers;

import java.util.Collection;

import pengrui.javagl.abstraction.basics.Lifecyclable;
import pengrui.javagl.abstraction.util.CheckUtil;

public interface Manageable<T> extends Lifecyclable {
	
	/**
	 * 当前设计register 所注册的管理对象必须是1级别的 否则抛异常 后续可能更改
	 * @param bean
	 */
	default void register(T bean){
		Manageable.register(this, bean);
	}
	default void unregister(T bean){
		Manageable.unregister(this, bean);
	}
	Collection<T> getAll();
//	void setAll(Collection<T> all);//TODO 后续看是否需要
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
