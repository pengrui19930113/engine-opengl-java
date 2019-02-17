package pengrui.javagl.abstraction.ids;


public interface Groupable {
	
	long getGroupID();
	void setGroupID(long groupID);
	
	public static long NO_GROUP  = -1;
	public static long genGroupID(){
		return ++GroupIDHolder.cacheID;
	}
	
	static class GroupIDHolder{
		static long cacheID = 0;
	}
}
