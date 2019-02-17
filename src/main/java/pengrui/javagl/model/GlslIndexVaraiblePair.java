package pengrui.javagl.model;

/**
 * glsl 中的定点属性 索引和对应的名称
 * @author Administrator
 *
 */
public class GlslIndexVaraiblePair {//TODO
	public int layoutIndex;
	public String attributeName;
	public GlslIndexVaraiblePair(int layoutIndex, String attributeName) {
		super();
		this.layoutIndex = layoutIndex;
		this.attributeName = attributeName;
	}
}
