package pengrui.javagl.abstraction.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import pengrui.javagl.abstraction.models.VaoPair;
import pengrui.javagl.abstraction.shaders.AbstractShader;
import pengrui.javagl.abstraction.shaders.VertexAttributeBinding;
import pengrui.javagl.shader.ShaderImpl;
import pengrui.javagl.texture.ImageType;

public class LoaderUtil {
	
	static List<Integer> vaos = new ArrayList<Integer>();
	static List<Integer> vbos = new ArrayList<Integer>();
	static List<Integer> textures = new ArrayList<Integer>();
	public static void clearup(){
		for(int vao:vaos)
			GL30.glDeleteVertexArrays(vao);
		
		for(int vbo:vbos)
			GL15.glDeleteBuffers(vbo);
		
		for(int texture:textures)
			GL11.glDeleteTextures(texture);
		
		vaos.clear();
		vbos.clear();
		textures.clear();
	}
	

	/**
	 * 
	 * @param positions
	 * @param textureCoords
	 * @param normals
	 * @param indices
	 * @return
	 * 顶点的绑定与下面2个类相关
	 * {@link VertexAttributeBinding}
	 * {@link AbstractShader}
	 * 
	 * 具体的实现可以查看
	 * {@link ShaderImpl}
	 */
	public static VaoPair loadVAO(float[] positions,float[] textureCoords ,float[]normals,int[] indices) {
		int vaoid = GL30.glGenVertexArrays();
		vaos.add(vaoid);//must be clear up
		GL30.glBindVertexArray(vaoid);
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions); //该处决定了着色器的 VertexAttributeBinding 如何配置
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		GL30.glBindVertexArray(0);// unbind vao
		return new VaoPair(vaoid, indices.length);
	}

	
	public static int loadTexture(InputStream is,ImageType type){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture(type.TYPE_NAME, is);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	static final String VERTEX_START_STRING = "v ";
	static final String VERTEX_TEXTURE_COORDINATION_START_STRING = "vt ";
	static final String VERTEX_NORMAL_START_STRING = "vn ";
	static final String FACE_START_STRING = "f ";
	static final String VALID_START_STRING[] = {
			VERTEX_START_STRING
			,VERTEX_TEXTURE_COORDINATION_START_STRING
			,VERTEX_NORMAL_START_STRING
			,FACE_START_STRING
	};
	static final String LINE_SPERATOR = " ";
	static final String FACE_SPERATOR = "/";
	static final String COMMENT_START = "#";
	public static VaoPair loadObjModel(InputStream is){
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textureCoords = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		int[]indicesArray = null;
		float[]verticesArray = null;
		float[]normalsArray = null;
		float[]textureArray = null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
			String line;
			LABEL:
			while(null!=(line = br.readLine()))
			{
				if(line.startsWith(COMMENT_START)||line.trim().length()<1){
					continue;
				}
				String[] linePart = line.split(LINE_SPERATOR);
				if(line.startsWith(VERTEX_START_STRING)){
					vertices.add(new Vector3f(
							Float.valueOf(linePart[1])
							,Float.valueOf(linePart[2])
							,Float.valueOf(linePart[3])
							));
				}else if(line.startsWith(VERTEX_TEXTURE_COORDINATION_START_STRING)){
					textureCoords.add(new Vector2f(
							Float.valueOf(linePart[1])
							,Float.valueOf(linePart[2])
							));
				}else if(line.startsWith(VERTEX_NORMAL_START_STRING)){
					normals.add(new Vector3f(
							Float.valueOf(linePart[1])
							,Float.valueOf(linePart[2])
							,Float.valueOf(linePart[3])
							));
				}else if(line.startsWith(FACE_START_STRING)){
					textureArray = new float[vertices.size()*2];
					normalsArray= new float[vertices.size()*3];
					break LABEL;
				}
			}
			
			do{
				if(!line.startsWith(FACE_START_STRING))
					continue;
				String[] currLine = line.split(LINE_SPERATOR);
				String[] vertex1 = currLine[1].split(FACE_SPERATOR);
				String[] vertex2 = currLine[2].split(FACE_SPERATOR);
				String[] vertex3 = currLine[3].split(FACE_SPERATOR);
				processVertex(vertex1,indices,textureCoords,normals,textureArray,normalsArray);
				processVertex(vertex2,indices,textureCoords,normals,textureArray,normalsArray);
				processVertex(vertex3,indices,textureCoords,normals,textureArray,normalsArray);
			}while(null!=(line = br.readLine()));
			
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		verticesArray = new float[vertices.size()*3];
		indicesArray = new int[indices.size()];
		int vertPointer = 0;
		for(Vector3f vert:vertices){
			verticesArray[vertPointer++]= vert.x;
			verticesArray[vertPointer++]= vert.y;
			verticesArray[vertPointer++]= vert.z;
		}
		
		for(int i=0;i<indices.size();++i)
			indicesArray[i] = indices.get(i);
		
		return loadVAO(verticesArray, textureArray,normalsArray, indicesArray);
	}
	private static void processVertex(
			String[] vertexData
			,List<Integer> indices
			,List<Vector2f> textureCoords
			,List<Vector3f> normals
			,float[] textureArray
			,float[] normalsArray
			){
		int currVertPointer = Integer.valueOf(vertexData[0])-1;
		indices.add(currVertPointer);
		Vector2f currTex = textureCoords.get(Integer.valueOf(vertexData[1])-1);
		textureArray[currVertPointer*2] = currTex.x;
		textureArray[currVertPointer*2+1] = 1-currTex.y;// 因为纹理坐标的原点在纹理的左上角
		Vector3f currNorm = normals.get(Integer.valueOf(vertexData[2])-1);
		normalsArray[currVertPointer*3] = currNorm.x;
		normalsArray[currVertPointer*3+1] = currNorm.y;
		normalsArray[currVertPointer*3+2] = currNorm.z;
	}
	
	
	private static void bindIndicesBuffer(int[] indices){
		int vboid = GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboid);
		IntBuffer buffer = storeDatatInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private static void storeDataInAttributeList(int attributeNumber,int coordinateSize,float[] data) {
		int vboid = GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboid);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber,coordinateSize,GL11.GL_FLOAT,false,0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private static IntBuffer storeDatatInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private static FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
