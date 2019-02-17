package pengrui.javagl.abstraction.loads;

import java.util.Collection;

import org.lwjgl.opengl.GL11;

import pengrui.javagl.abstraction.util.LogUtil;

public interface TextureLoader extends Loadable {
	Collection<Integer> getTextures();
	
	
	public static void cleanup(TextureLoader tl){
		if(null ==tl || null == tl.getTextures()){
			LogUtil.debug("texture loader == null or getTextures == null");
			return;
		}
		
		for(int i:tl.getTextures()){
			GL11.glDeleteTextures(i);
		}
	}
}
