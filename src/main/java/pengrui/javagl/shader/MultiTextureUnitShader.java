package pengrui.javagl.shader;

import java.io.InputStream;

import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.shaders.UniformLocationBinding;
import pengrui.javagl.abstraction.util.LoaderUtil;

/**
 * 
 * TODO  glsl 着色器还没有写
 * @author Administrator
 * 
 * 索引和名称的绑定是根据下面这个函数决定的
 *{@link LoaderUtil#loadVAO(float[], float[], float[], int[])}.
 */
//@VertexAttributeBinding( // 父类已经使用了一模一样的注解
//		layoutIndex = 	{
//				0
//				,1
//				,2
//				}
//		, attrName = {
//				"position"
//				,"textureCoords"
//				,"normal"
//				}
//		)
public class MultiTextureUnitShader extends ShaderImpl{

	public MultiTextureUnitShader(InputStream vsis, InputStream fsis) {
		super(vsis, fsis);
	}
	public MultiTextureUnitShader(InputStream vsis, InputStream fsis, InputStream gsis) {
		super(vsis, fsis, gsis);
	}
//	@UniformLocationBinding("transformationMatrix")// 已经被父类持有了
//	int locationTransformationMatrix;
//	@UniformLocationBinding("projectionMatrix")
//	int locationProjectionMatrix;
//	@UniformLocationBinding("viewMatrix")
//	int locationViewMatrix;
//	@UniformLocationBinding("lightPosition")
//	int locationLightPosition;
//	@UniformLocationBinding("lightColour")
//	int locationLightColour;
//	@UniformLocationBinding("shineDamper")
//	int locationShineDamper;
//	@UniformLocationBinding("reflectivity")
//	int locationReflectivity;
	@UniformLocationBinding("backGroundMap")
	int location_backGroundMap;
	@UniformLocationBinding("blendMap")
	int location_blendMap;
	@UniformLocationBinding("rTextureMap")
	int location_rTextureMap;
	@UniformLocationBinding("gTextureMap")
	int location_gTextureMap;
	@UniformLocationBinding("bTextureMap")
	int location_bTextureMap;
	
	@Override
	public void loadOnceGlslVaraibles(Object... varaibles) {
		super.loadOnceGlslVaraibles(varaibles);
		this.useShader();
		// 在glsl中使用的 纹理单元0 就所指向的采样变量 backGroundMap 在客户端中bind active 的纹理单元也就与之绑定
		Shaderable.loadInteger(location_backGroundMap, 0);
		// 这个与纹理单元1绑定的 glsl采样器变量名就是blendMap 后面以此类推
		Shaderable.loadInteger(location_blendMap, 1);
		Shaderable.loadInteger(location_rTextureMap, 2);
		Shaderable.loadInteger(location_gTextureMap, 3);
		Shaderable.loadInteger(location_bTextureMap, 4);
		this.unuseShader();
	}
}
