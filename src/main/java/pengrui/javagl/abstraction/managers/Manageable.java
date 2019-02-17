package pengrui.javagl.abstraction.managers;

import java.util.Collection;

import pengrui.javagl.abstraction.cores.Lifecyclable;
import pengrui.javagl.abstraction.util.LogUtil;

public interface Manageable<T> extends Lifecyclable{
	void register(T bean);
	void remove(T bean);
	Collection<T> getAll();
	void init(); //by Lifecyclable
	void destroy();//by Lifecyclable
	
	public static void checkManager(Manageable<?> m){
		if(null == m.getAll())
			throw new RuntimeException(" thie manager must be have a container , manager class:"+m.getClass());
		
	}
	
	static boolean checkParam(Manageable<?> manager,Object bean){
		if(null == bean)
			return false;
		
		if(null == manager || null == manager.getAll()){
			LogUtil.debug("manager == null or getAll == null");
			return false;
		}
		
		return true;
	}
	
	public static<T> void remove(Manageable<T> manager,T bean){
	
		if(checkParam(manager,bean))
			manager.getAll().remove(bean);
	}
	
	public static<T> void register(Manageable<T> manager,T bean){
		if(checkParam(manager,bean))
			manager.getAll().add(bean);
	}
}
