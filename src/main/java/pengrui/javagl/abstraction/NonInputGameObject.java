package pengrui.javagl.abstraction;

import java.util.Collection;

import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.shaders.Shaderable;
/**
 * 不会有输入处理 只有动画效果的游戏对象
 * @author Administrator
 *
 */
public class NonInputGameObject 
		implements HasChildrenable<NonInputGameObject>,Actionable,Drawable{
	
	public NonInputGameObject() {
//		ActionManagerFactory.getInstance().register(this);
//		DrawManagerFactory.getInstance().register(this);
	}

	@Override
	public boolean isEnableDraw() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnableDraw(boolean en) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draws() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnableDrawChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnableDrawChildren(boolean en) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shaderable getShader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShader(Shaderable shader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnableCulling() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void senEnableCulling() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnableAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnableAction(boolean en) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actions(long delteTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAction(long delteTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnableChildrenAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnableChildrenAction(boolean en) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasAnimatation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Animationable> getAnimations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAnimation(Animationable animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnableAnimation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<NonInputGameObject> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(NonInputGameObject child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild(NonInputGameObject child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NonInputGameObject getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(NonInputGameObject parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDepth(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasTheChild(NonInputGameObject child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}
}
