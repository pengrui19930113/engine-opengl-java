package pengrui.javagl.abstraction.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ReflectionUtil {

	public static<T extends Annotation> 
			List<Field> findFieldByAnnotation(Class<?> clazz ,Class<T> annotationClass){
		List<Field> lists = new LinkedList<Field>();
		for(Field f:clazz.getDeclaredFields()){
			if(null!= f.getAnnotation(annotationClass))
				lists.add(f);
		}
		
		return  lists;
	} 
	public static<T extends Annotation> 
			T getTargetAnnotation(Class<?> clazz,Class<T> annotationClass){
		return clazz.getAnnotation(annotationClass);
	}
}
