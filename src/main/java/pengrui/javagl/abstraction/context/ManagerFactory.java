package pengrui.javagl.abstraction.context;
/**
 * 游戏 关卡 场景 的层次关系还需要缕一缕 TODO
 */
import pengrui.javagl.abstraction.collisions.ICollisionableManager;
import pengrui.javagl.abstraction.events.IEventManager;
import pengrui.javagl.abstraction.guis.IGuiableManager;
import pengrui.javagl.abstraction.ids.IGroupableManager;
import pengrui.javagl.abstraction.ids.IIdentifiableManager;
import pengrui.javagl.abstraction.managers.IActionableManager;
import pengrui.javagl.abstraction.managers.IDrawableManager;
import pengrui.javagl.abstraction.managers.IInputableManager;
import pengrui.javagl.abstraction.managers.ILifecyclableManager;
import pengrui.javagl.abstraction.models.IModelableManager;
/**
 * 每个关卡都有一个场景与之对应
 * 每个关卡 都有一个上下文来获取相关的管理类 
 * 管理类 都是通过Context.getManagerFactory() 来获取
 * @author Administrator
 *
 */
import pengrui.javagl.abstraction.sounds.ISoundableManager;
public interface ManagerFactory {//TODO 后续可能还有变更
	
	IEventManager getEventManager();
	ILifecyclableManager getLifecycleManager();
	IInputableManager getInputManager();
	IDrawableManager getDrawManager();
	IActionableManager getActionManager();
	ILifecyclableManager getLightManager();
	IIdentifiableManager getIDManager();
	IGroupableManager getGroupManager();
	IGuiableManager getGuiManager();
	ICollisionableManager getCollisionManager();
	ISoundableManager getSoundsManager();
	IModelableManager getModelsManager();
}
