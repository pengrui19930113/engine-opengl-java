package pengrui.javagl.abstraction.context;

import pengrui.javagl.abstraction.cameras.ICameraManager;
import pengrui.javagl.abstraction.collisions.ICollisionableManager;
import pengrui.javagl.abstraction.events.IEventManager;
import pengrui.javagl.abstraction.guis.IGuiableManager;
import pengrui.javagl.abstraction.ids.IGroupableManager;
import pengrui.javagl.abstraction.ids.IIDMajorManager;
import pengrui.javagl.abstraction.lighting.ILightableManager;
import pengrui.javagl.abstraction.managers.IActionableManager;
import pengrui.javagl.abstraction.managers.IDrawableManager;
import pengrui.javagl.abstraction.managers.IInputableManager;
import pengrui.javagl.abstraction.models.IModelableManager;
import pengrui.javagl.abstraction.net.INetComponent;
import pengrui.javagl.abstraction.scenes.IScene;
import pengrui.javagl.abstraction.sounds.ISoundableManager;

/**
 * 关卡上下文， 一个关卡对应一个场景
 * @author Administrator
 *
 */
public interface IContext {
	IScene getScene();
	
	IEventManager getEventManager();
	IContext setEventManager(IEventManager evnm);
	
	IInputableManager getInputManager();
	IContext setInputManager(IInputableManager inputm);
	
	IDrawableManager getDrawManager();
	IContext setDrawManager(IDrawableManager drawm);//每个场景的绘制管理对象 应该是同一套逻辑
	
	IActionableManager getActionManager();//每个场景的动作管理对象 应该是同一套逻辑
	IContext setActionManager(IActionableManager actionm);
	
	ILightableManager getLightManager();
	IContext setLightManager(ILightableManager lightm);
	
	IIDMajorManager getIDManager();
	IContext setIDManager(IIDMajorManager imd);
	
	IGroupableManager getGroupManager();
	IContext setGroupManager(IGroupableManager groupm);
	
	IGuiableManager getGuiManager();
	IContext setGuiManager(IGuiableManager guim);
	
	ICollisionableManager getCollisionManager();
	IContext setCollisionManager(ICollisionableManager collim);
	
	ISoundableManager getSoundsManager();
	IContext setSoundsManager(ISoundableManager soundm);
	
	IModelableManager getModelsManager();
	IContext setModelsManager(IModelableManager modelm);
	
	ICameraManager getCameraManager();
	IContext setCameraManager(ICameraManager cm);
	
	INetComponent getNetComponent();
	IContext setNetComponent(INetComponent nc);
	IContext cache = new IContext() {
		
		@Override
		public IContext setSoundsManager(ISoundableManager soundm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setModelsManager(IModelableManager modelm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setLightManager(ILightableManager lightm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		@Override
		public IContext setInputManager(IInputableManager inputm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		@Override
		public IContext setGuiManager(IGuiableManager guim) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setGroupManager(IGroupableManager groupm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setEventManager(IEventManager evnm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setDrawManager(IDrawableManager drawm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setCollisionManager(ICollisionableManager collim) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IContext setActionManager(IActionableManager actionm) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ISoundableManager getSoundsManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IScene getScene() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IModelableManager getModelsManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ILightableManager getLightManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		@Override
		public IInputableManager getInputManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IGuiableManager getGuiManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IGroupableManager getGroupManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		@Override
		public IDrawableManager getDrawManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ICollisionableManager getCollisionManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IActionableManager getActionManager() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ICameraManager getCameraManager() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IContext setCameraManager(ICameraManager cm) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public INetComponent getNetComponent() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IContext setNetComponent(INetComponent nc) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IEventManager getEventManager() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IIDMajorManager getIDManager() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IContext setIDManager(IIDMajorManager imd) {
			// TODO Auto-generated method stub
			return null;
		}
	};
}
