package pengrui.javagl.abstraction.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import pengrui.javagl.abstraction.shaders.Shaderable;

public class GlslUtil {
	public static interface ValueHolder<T extends Annotation>{
		String value(T u);
	}
	
	/**
	 * 获取色器的location变量保存在shader中，为后面的绑定uniform做准备
	 * @param target
	 * @param targetClazz
	 * @param annoClazz
	 * @param t
	 */
	public static
	<T extends Annotation> 
	void processLocation(
			Shaderable target
			,Class<?> targetClazz
			,Class<T> annoClazz
			,ValueHolder<T> t
			){
		for (Field f : ReflectionUtil.findFieldByAnnotation(targetClazz, annoClazz)) {
			try {
				f.setAccessible(true);
				T annotation = f.getAnnotation(annoClazz);
				ExceptionUtil.ifNullThrowRuntimeException(annotation
						, String.format("%s missing %s annotation"
						,targetClazz
						, annoClazz));
				f.set(target , Shaderable.getUniformLocation(target , t.value(annotation)));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
