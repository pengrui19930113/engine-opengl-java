package pengrui.javagl.abstraction.shaders;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(value={TYPE})
@Inherited
public @interface VertexAttributeBinding {
	int[] layoutIndex();
	String[] attrName();
}
