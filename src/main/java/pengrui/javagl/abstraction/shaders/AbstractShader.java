package pengrui.javagl.abstraction.shaders;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL20;

import pengrui.javagl.abstraction.util.ExceptionUtil;
import pengrui.javagl.abstraction.util.GlslUtil;
import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.GlobalConfig;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.ReflectionUtil;

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
		Shaderable.init(this,vsis,fsis,null);
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
	public void unuseShader() {
		Shaderable.unuseShader(this);
	}

	@Override
	public void cleanup() {
		Shaderable.destroy(this);
	}

	@Override
	public void getAllUniformLocations() {
		GlslUtil.processLocation(
				this
				, this.getClass()
				, UniformLocationBinding.class
				, x-> x.value()
				);
		
		
//		Class<UniformLocationBinding> annotationClass = UniformLocationBinding.class;
//		for (Field f : ReflectionUtil.findFieldByAnnotation(this.getClass(), annotationClass)) {
//			try {
//				f.setAccessible(true);
//				UniformLocationBinding annotation = f.getAnnotation(annotationClass);
//				ExceptionUtil.ifNullThrowRuntimeException(annotation
//						, String.format("%s missing %s annotation"
//						,this.getClass()
//						, UniformLocationBinding.class));//
//				String glslVaraibleName = annotation.value();
//				f.set(this , Shaderable.getUniformLocation(this , glslVaraibleName));
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				e.printStackTrace();
//				System.exit(-1);
//			}
//		}
	}

	private void checkInvalidAnnotParam(int[] layoutIndex, String[] attrVaribles){
		if(layoutIndex.length!=attrVaribles.length){
			class LayoutIndexNotMatchGLSLVaraible extends RuntimeException{
				private static final long serialVersionUID = 7375067513142652672L;
				public LayoutIndexNotMatchGLSLVaraible(String msg) {
					super(msg);
				}
			}
			throw new LayoutIndexNotMatchGLSLVaraible
					(String.format(
							"index count not match the glsl attribute varaible\n:layoutIndex:%s\nglsl varaibles:%s"
							,Arrays.stream(layoutIndex).mapToObj(i->i).collect(Collectors.toList()).toString()
							,Arrays.asList(attrVaribles).toString()));
		}
	}
	
	@Override
	public void bindAttributes() {
		VertexAttributeBinding vab = ReflectionUtil.getTargetAnnotation(this.getClass(),VertexAttributeBinding.class );
		ExceptionUtil.ifNullThrowRuntimeException(vab
				, String.format("%s missing %s annotation"
						,this.getClass()
						, VertexAttributeBinding.class));
		
		int[] layoutIndex = vab.layoutIndex();
		String[] attrVaribles = vab.attrName();
		checkInvalidAnnotParam(layoutIndex, attrVaribles);
		for(int i=0;i<attrVaribles.length;++i){
			Shaderable.bindAttribute(this,layoutIndex[i], attrVaribles[i]);
		}
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
	public void useShader() {
		GL20.glUseProgram(this.getProgramID());
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
