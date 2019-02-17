package pengrui.javagl.abstraction.basics;

public interface Varaible<RESULT extends Object,PARAM extends Object> {
	
	@SuppressWarnings("unchecked")
	RESULT get(PARAM... params);
	void set(PARAM param);
	
}
