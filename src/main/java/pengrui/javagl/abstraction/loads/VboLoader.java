package pengrui.javagl.abstraction.loads;

import java.util.Collection;

import org.lwjgl.opengl.GL15;

import pengrui.javagl.abstraction.util.LogUtil;

public interface VboLoader extends Loadable {
	Collection<Integer> getVbos();
	
	
	public static void cleanup(VboLoader vbol){
		if(null == vbol || null == vbol.getVbos()){
			LogUtil.debug("vbo loader == null or getVbos == null");
			return;
		}
		
		for(int vboID:vbol.getVbos()){
			GL15.glDeleteBuffers(vboID);
		}
	}
}
