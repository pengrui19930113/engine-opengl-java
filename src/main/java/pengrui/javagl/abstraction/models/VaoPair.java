package pengrui.javagl.abstraction.models;

/**
 * vao id 和 vao id 对应的顶点数
 * @author Administrator
 *
 */
public class VaoPair {
	public int vaoID;
	public int indices;
	public VaoPair(int vaoID, int indices) {
		super();
		this.vaoID = vaoID;
		this.indices = indices;
	}
}
