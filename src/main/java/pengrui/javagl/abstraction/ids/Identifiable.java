package pengrui.javagl.abstraction.ids;

//需要 ID 唯一标志的对象实现该接口
public interface Identifiable {
	
	void setID(long id);
	long getID();
	
	public static long genGameObjectID(){
		return ++GameObjectIDHolder.cacheID;
	}
	
	static class GameObjectIDHolder{
		static long cacheID = 0;
	}
	
}
