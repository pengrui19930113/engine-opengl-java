package pengrui.javagl.abstraction.basics;

public interface Varaible<RESULT extends Object,PARAM extends Object> {
	
	RESULT get();
	void set(PARAM param);
	
}
