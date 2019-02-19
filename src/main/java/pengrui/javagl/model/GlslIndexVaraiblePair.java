package pengrui.javagl.model;

import pengrui.javagl.abstraction.shaders.VertexAttributeBinding;
import pengrui.javagl.shader.ShaderImpl;

/**
 * glsl 中的定点属性 索引和对应的名称
 * 
 * @author Administrator
 * 
 * //TODO 目前没用到
 * is no use now , 
 * {@link ShaderImpl}
 * {@link VertexAttributeBinding}
 */

public class GlslIndexVaraiblePair {
	public int layoutIndex;
	public String attributeName;
	public GlslIndexVaraiblePair(int layoutIndex, String attributeName) {
		super();
		this.layoutIndex = layoutIndex;
		this.attributeName = attributeName;
	}
}
