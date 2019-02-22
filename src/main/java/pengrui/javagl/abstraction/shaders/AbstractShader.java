package pengrui.javagl.abstraction.shaders;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import pengrui.javagl.abstraction.util.GlobalConfig;
import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.ReflectionUtil;

/**
 * 
 * @author Administrator
 * 
 * 索引和名称的绑定是根据下面这个函数决定的
 *{@link LoaderUtil#loadVAO(float[], float[], float[], int[])}.
 */
//@VertexAttributeBinding( // 子类需要使用这样的注解 来标明顶点属性的layout
//					layoutIndex = 	{
//							0
//							,1
//							,2
//							}
//					, attrName = {
//							"position"
//							,"textureCoords"
//							,"normal"
//							}
//					)
public abstract class AbstractShader implements Shaderable {
	
	private void printUniformMessage(){
		LogUtil.debug(
				String.format("**************print %s uniform message begin************"
						, this.getClass()));
		for(Field f :
			ReflectionUtil
				.findFieldByAnnotation(this.getClass()
						, UniformLocationBinding.class)){
			try {
				f.setAccessible(true);
				LogUtil.info(String.format("field:%s,value:%s", f.getName(),f.get(this)));
				LogUtil.info(String.format("field's annotation:%s", Arrays.asList(f.getAnnotations())));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		LogUtil.info(
				String.format("**************print %s uniform message end************"
						, this.getClass()));
	}
	private void printVertexAttributeMessage(){
		LogUtil.info(
				String.format("**************print %s attribute message begin************"
						, this.getClass()));
		VertexAttributeBinding annotation = 
				this.getClass().getAnnotation(VertexAttributeBinding.class);
		LogUtil.info(String.format("%s annotation:%s", this.getClass(),annotation));
		LogUtil.info(
				String.format("**************print %s attribute message end************"
						, this.getClass()));
	}
	private void printInitLaterMessage(){
		if(GlobalConfig.SHADER_DEBUG_ENABLE){
			printUniformMessage();
			LogUtil.reline();
			printVertexAttributeMessage();
			LogUtil.reline();
		}
	}
	
	public AbstractShader(InputStream vsis,InputStream fsis) {
		Shaderable.init(this,vsis,fsis);
		enableGeometryShader = false;
		loadOnceGlslVaraibles();
		printInitLaterMessage();
	}
	
	public AbstractShader(InputStream vsis,InputStream fsis,InputStream gsis) {
		Shaderable.init(this, vsis, fsis,gsis);
		enableGeometryShader = true;
		loadOnceGlslVaraibles();
		printInitLaterMessage();
	}
	boolean enableGeometryShader;
	
	int programID;
	int vertexShaderID;
	int fragmentShaderID;
	int geometryShaderID;
//
//	@UniformLocationBinding("transformationMatrix")
//	int locationTransformationMatrix;
//	@UniformLocationBinding("projectionMatrix")
//	int locationProjectionMatrix;
//	@UniformLocationBinding("viewMatrix")
//	int locationViewMatrix;
//	@UniformLocationBinding("lightPosition")
//	int locationLightPosition;
//	@UniformLocationBinding("lightColour")
//	int locationLightColour;

	@Override
	public boolean hasGeometryShader() {
		return enableGeometryShader;
	}

	@Override
	public int getProgramID() {
		return programID;
	}
	
	@Override
	public void setProgramID(int proid) {
		this.programID = proid;
	}
	
	@Override
	public int getVertexShaderID() {
		return vertexShaderID;
	}
	
	@Override
	public void setVertexShaderID(int vsid) {
		this.vertexShaderID = vsid;
	}
	
	@Override
	public int getFragmentShaderID() {
		return fragmentShaderID;
	}
	
	@Override
	public void setFramentShaderID(int fsid) {
		this.fragmentShaderID = fsid;
	}
	
	@Override
	public int getGeometryShaderID() {
		return geometryShaderID;
	}
	
	@Override
	public void setGeometryShaderID(int gsid) {
		this.geometryShaderID = gsid;
	}
}
