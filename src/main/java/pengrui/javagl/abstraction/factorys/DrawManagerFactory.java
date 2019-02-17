package pengrui.javagl.abstraction.factorys;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import pengrui.javagl.abstraction.basics.Factoryable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.managers.AbstractDrawManager;
import pengrui.javagl.abstraction.managers.IDrawableManager;

public interface DrawManagerFactory extends Factoryable<IDrawableManager>{
	public  IDrawableManager instance();
	
	static class Impl extends AbstractDrawManager{
		public Impl() {
			GL11.glClearColor(.1f,0.12f,0.1f, 1f);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glCullFace(GL11.GL_BACK);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		Set<Drawable> beans = new HashSet<Drawable>();
		@Override
		public void init() {
			//nothing
		}
		
		@Override
		public Collection<Drawable> getAll() {
			return beans;
		}
		
		@Override
		public void destroy() {
			super.destroy();
			beans = null; //help GC
		};

	}
	IDrawableManager INSTANCE = new Impl();
	public static IDrawableManager getInstance(){
		return INSTANCE;
	}
	
}
