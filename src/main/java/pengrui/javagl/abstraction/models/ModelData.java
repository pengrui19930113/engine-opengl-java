package pengrui.javagl.abstraction.models;

import pengrui.javagl.abstraction.shaders.IShaderableManager;
import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.textures.ITextureaManager;

public class ModelData {
	public  Integer vaoID;
	public Integer vertexCount;
	public Integer textureID;
	public Shaderable shader;
	
	
	public ModelData(Integer vaoID, Integer vertexCount, Integer textureID, Shaderable shader) {
		super();
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.textureID = textureID;
		this.shader = shader;
	}


	public static final ModelData cache = new ModelData(VaoPair.cache.vaoID, VaoPair.cache.indices, ITextureaManager.cache, IShaderableManager.cache);
}
