package pengrui.javagl.abstraction.managers;

import org.lwjgl.opengl.GL11;

import pengrui.javagl.abstraction.cores.Drawable;

public interface IDrawableManager extends Manageable<Drawable>{
	void draws();
	
	public static void remove(IDrawableManager dm,Drawable bean) {
		Manageable.checkManager(dm);
		dm.getAll().remove(bean);
	}
	
	public static void register(IDrawableManager dm,Drawable bean) {
		Manageable.checkManager(dm);
		dm.getAll().add(bean);
	}
	
	public static void draws(IDrawableManager dm) {
		Manageable.checkManager(dm);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT
				|GL11.GL_DEPTH_BUFFER_BIT
				);
		
		for (Drawable drawable : dm.getAll()) {
			if(drawable.isEnableDrawChildren())
				drawable.draws();
			
			if(drawable.isEnableDraw())
				drawable.onDraw();
		}
	}
	
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
}
