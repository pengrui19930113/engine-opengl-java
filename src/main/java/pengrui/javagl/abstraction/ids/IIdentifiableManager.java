package pengrui.javagl.abstraction.ids;

public interface IIdentifiableManager
{
	Identifiable getObjectByID(long id);
	public static Identifiable getObjectByID(IIdentifiableManager im,long id){
		return im.getObjectByID(id);
	}
}
