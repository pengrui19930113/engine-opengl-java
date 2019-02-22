package pengrui.javagl.abstraction.shaders;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 
 * TODO 该注解是否使用还在考虑
 * 目前觉得用 {@link Shaderable#loadOnceGlslVaraibles(Object...)}
 * 在具体的实现类里面来绑定纹理索引比较好
 * 
 * 如果后续使用该注解
 * 使用该注解的 field 需要有
 * link {@link UniformLocationBinding}
 * 因为只有获取到了shader 的location 后才能进行纹理单元的绑定
 * 
 * 
 * @author Administrator
 *
 */
@Retention(RUNTIME)
@Target(value={TYPE})
@Inherited
public @interface TextureUnitBinding {
	int unitIndex();
}
