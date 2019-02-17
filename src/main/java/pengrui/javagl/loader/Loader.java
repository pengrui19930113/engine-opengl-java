package pengrui.javagl.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import pengrui.javagl.abstraction.AbstractLifecycle;
import pengrui.javagl.texture.ImageType;

public class Loader extends AbstractLifecycle{
	
	List<Integer> vaos = new ArrayList<Integer>();
	List<Integer> vbos = new ArrayList<Integer>();
	List<Integer> textures = new ArrayList<Integer>();
	
	public int loadTexture(InputStream is,ImageType type){
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
	public int loadTexture(InputStream is){
		return loadTexture(is,ImageType.PNG);
	}
	public int loadTexture(String fileName){
		try {
			return loadTexture(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return -1;
	}
	
	private void cleanUp(){
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
	public RawModel loadToVAO(float[] positions) {
		int vaoId = createVAO();
		storeDataInAttributeList(0,positions);
		unbindVAO();
		return new RawModel(vaoId,positions.length/3);
	}
	
	public RawModel loadToVAO(float[] positions,int[] indices) {
		int vaoId = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,positions);
		unbindVAO();
		return new RawModel(vaoId,indices.length);
	}
	
	public RawModel loadToVAO(float[] positions,float[] textureCoords ,int[] indices) {
		int vaoId = createVAO();
		bindIndicesBuffer(indices);
//		storeDataInAttributeList(0,positions);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		unbindVAO();
		return new RawModel(vaoId,indices.length);
	}
	
	public RawModel loadToVAO(float[] positions,float[] textureCoords ,float[]normals,int[] indices) {
		int vaoId = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		unbindVAO();
		return new RawModel(vaoId,indices.length);
	}
	private int createVAO() {
		int vaoid = GL30.glGenVertexArrays();
		vaos.add(vaoid);
		GL30.glBindVertexArray(vaoid);
		return vaoid;
	}
	
	private void storeDataInAttributeList(int attributeNumber,float[] data) {
		int vboid = GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboid);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber,3,GL11.GL_FLOAT,false,0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private void storeDataInAttributeList(int attributeNumber,int coordinateSize,float[] data) {
		int vboid = GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboid);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber,coordinateSize,GL11.GL_FLOAT,false,0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices){
		int vboid = GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboid);
		IntBuffer buffer = storeDatatInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeDatatInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	@Override
	public void init() {
		//nothing
	}
	@Override
	public void destroy() {
		cleanUp();
	}
}
