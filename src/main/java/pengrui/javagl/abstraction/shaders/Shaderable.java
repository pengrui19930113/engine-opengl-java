package pengrui.javagl.abstraction.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.basics.Cleanable;


public interface Shaderable extends Cleanable
//extends Lifecyclable
{
	
	int getProgramID();
	void setProgramID(int proid);
	int getVertexShaderID();
	void setVertexShaderID(int vsid);
	int getFragmentShaderID();
	void setFramentShaderID(int fsid);
	boolean hasGeometryShader();
	int getGeometryShaderID();
	void setGeometryShaderID(int gsid);
	
	void useShader();
	void unuseShader();
	void loadOnceGlslVaraibles(Object ...varaibles);
	void loadGlslVaraibles(Object ...varaibles);
	void bindAttributes();
	void getAllUniformLocations();
	
	public static void init(Shaderable shader,InputStream vsis,InputStream fsis){
		init(shader,vsis,fsis,null);
	}
	
	public static void init(Shaderable shader,InputStream vsis,InputStream fsis,InputStream gsis){
		int vertexShaderID = loadShader(vsis,GL20.GL_VERTEX_SHADER);
		shader.setVertexShaderID(vertexShaderID);
		int fragmentShaderID = loadShader(fsis,GL20.GL_FRAGMENT_SHADER);
		shader.setFramentShaderID(fragmentShaderID);
		int geometryShaderID = 0;
		if(shader.hasGeometryShader()&&null!=gsis){
			geometryShaderID = loadShader(gsis, GL32.GL_GEOMETRY_SHADER);
			shader.setGeometryShaderID(geometryShaderID);
		}
		int programID = GL20.glCreateProgram();
		shader.setProgramID(programID);
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		if(shader.hasGeometryShader()
				&&null != gsis
				&&0 != geometryShaderID){
			GL20.glAttachShader(programID,geometryShaderID);
		}
		shader.bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		shader.getAllUniformLocations();
	}
	
	public static int loadShader(InputStream is,int type){
		StringBuilder sb = null;
		BufferedReader reader = null; 
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String line;
			sb = new StringBuilder();
			while(null != (line = reader.readLine()))
				sb.append(line).append("\n");
		
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID,sb.toString());
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS)==GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(shaderID,500));
			System.out.println("compile shader failure");
			System.exit(-1);
		}
		
		return shaderID;
	}
	public static void useShader(Shaderable shader){
		GL20.glUseProgram(shader.getProgramID());
	}
	public static void unuseShader(Shaderable shader){
		GL20.glUseProgram(0);
	}
	
	public static void destroy(Shaderable shader){
		if(0 == shader.getProgramID())
			return;
		
		shader.unuseShader();
		if(0!= shader.getFragmentShaderID()){
			GL20.glDetachShader(shader.getProgramID(), shader.getFragmentShaderID());
			GL20.glDeleteShader(shader.getFragmentShaderID());
		}
		if(0!= shader.getFragmentShaderID()){
			GL20.glDetachShader(shader.getProgramID(), shader.getVertexShaderID());
			GL20.glDeleteShader(shader.getVertexShaderID());
		}
		
		if(shader.hasGeometryShader()&&0!=shader.getGeometryShaderID()){
			GL20.glDetachShader(shader.getProgramID(), shader.getGeometryShaderID());
			GL20.glDeleteShader(shader.getGeometryShaderID());
		}
		GL20.glDeleteProgram(shader.getProgramID());
	}
	
	public static void bindAttribute(Shaderable shader,int attribute,String varaibleName){
		GL20.glBindAttribLocation(shader.getProgramID(),attribute,varaibleName);
	}
	
	public static final FloatBuffer FLOAT_MATRIX_BUFFER = BufferUtils.createFloatBuffer(16);
	
	public static void loadMatrix4f(int location , Matrix4f matrix){
		matrix.store(FLOAT_MATRIX_BUFFER);
		FLOAT_MATRIX_BUFFER.flip();
		GL20.glUniformMatrix4(location, false, FLOAT_MATRIX_BUFFER);
		FLOAT_MATRIX_BUFFER.clear();
	}
	
	public static void loadBoolean(int location,boolean value){
		float toLoad = 0;
		if(value)
			toLoad = 1;
		GL20.glUniform1f(location, toLoad);
	}
	
	public static void loadFloat(int location,float value){
		GL20.glUniform1f(location, value);
	}
	
	public static void loadVector3f(int location ,Vector3f v){
		GL20.glUniform3f(location, v.x, v.y, v.z);
	}
	
	public static void lodVector3f(int location ,float a,float b,float c){
		GL20.glUniform3f(location, a,b,c);
	}
	
	public static int getUniformLocation(Shaderable shader,String uniformName){
		return GL20.glGetUniformLocation(shader.getProgramID(),uniformName);
	}
}
