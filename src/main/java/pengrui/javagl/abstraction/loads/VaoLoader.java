package pengrui.javagl.abstraction.loads;

import java.util.Collection;

import org.lwjgl.opengl.GL30;

import pengrui.javagl.abstraction.util.LogUtil;

public interface VaoLoader extends VboLoader {
	Collection<Integer> getVaos();
	
	public static void cleanup(VaoLoader vaol){
		if(null == vaol || null == vaol.getVaos()){
			LogUtil.debug("vao loader == null or getVaos == null");
			return;
		}
		
		VboLoader.cleanup(vaol);
		for(int vaoID:vaol.getVaos()){
			GL30.glDeleteVertexArrays(vaoID);
		}
	}
}
