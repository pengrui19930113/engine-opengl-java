package pengrui.javagl.shader;

import java.io.InputStream;

import org.lwjgl.util.vector.Matrix4f;

import pengrui.javagl.abstraction.shaders.AbstractShader;
import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.shaders.UniformLocationBinding;
import pengrui.javagl.abstraction.shaders.VertexAttributeBinding;
import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.MatrixUtil;
import pengrui.javagl.entitys.Camera;
import pengrui.javagl.entitys.Light;

/**
 * 
 * @author Administrator
 * 
 * 索引和名称的绑定是根据下面这个函数决定的
 *{@link LoaderUtil#loadVAO(float[], float[], float[], int[])}.
 */
@VertexAttributeBinding(
		layoutIndex = 	{
				0
				,1
				,2
				}
		, attrName = {
				"position"
				,"textureCoords"
				,"normal"
				}
		)
public class ShaderImpl extends AbstractShader{

	public ShaderImpl(InputStream vsis, InputStream fsis) {
		super(vsis, fsis);
	}
	public ShaderImpl(InputStream vsis, InputStream fsis, InputStream gsis) {
		super(vsis, fsis, gsis);
	}
	@UniformLocationBinding("transformationMatrix")
	int locationTransformationMatrix;
	@UniformLocationBinding("projectionMatrix")
	int locationProjectionMatrix;
	@UniformLocationBinding("viewMatrix")
	int locationViewMatrix;
	@UniformLocationBinding("lightPosition")
	int locationLightPosition;
	@UniformLocationBinding("lightColour")
	int locationLightColour;
	@UniformLocationBinding("shineDamper")
	int locationShineDamper;
	@UniformLocationBinding("reflectivity")
	int locationReflectivity;
	@Override
	public void loadGlslVaraibles(Object... varaibles) {
		Matrix4f tfm = (Matrix4f) varaibles[0];
		float shineDamper = (float) varaibles[1];
		float reflectivity = (float) varaibles[2];
		Shaderable.loadMatrix4f(locationTransformationMatrix, tfm);		
		Camera camera = Camera.getInstance();//TODO 后续相机有相机管理器来获取
		Shaderable.loadMatrix4f(locationViewMatrix, MatrixUtil.createViewMatrix(camera));
		Shaderable.loadFloat(locationShineDamper, shineDamper);
		Shaderable.loadFloat(locationReflectivity, reflectivity);
		Light light = Light.getInstance();// TODO 后续光源 由光源管理器来获取
		Shaderable.loadVector3f(locationLightPosition, light.getPosition());
		Shaderable.loadVector3f(locationLightColour, light.getColour());
	}
	
	@Override
	public void loadOnceGlslVaraibles(Object... varaibles) {
		this.useShader();
		Matrix4f projectionMatrix = null;
		if(null !=varaibles 
				&& varaibles instanceof Object[]
				&& varaibles.length > 0
				&& varaibles[0] instanceof Matrix4f
				){
			projectionMatrix = (Matrix4f)varaibles[0];
		}else{
			projectionMatrix = MatrixUtil.createProjectionMatrix();
			LogUtil.debug("use defaule projection matrix");
		}
		LogUtil.debug(String.format("projection matrix:\n%s", projectionMatrix));
		Shaderable.loadMatrix4f(locationProjectionMatrix, projectionMatrix);
		this.unuseShader();
	}
}
