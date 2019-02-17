package pengrui.javagl.entitys;

import java.util.Collection;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.ActionableObject;
import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;

public class Camera extends ActionableObject{
	Vector3f position = new Vector3f(0,0,3f);
	float pitch; // 倾斜 // 仰角 //相机坐标系中的 X
	float yaw;	// 偏航角 //相机坐标系中的Y
	float roll; // 翻滚 //相机坐标系中的Z
	
	static Camera instance = new Camera();
	public static Camera getInstance(){
		return instance;
	}
	
	public Camera() {
		ActionManagerFactory.getInstance().register(this);
	}

	static float MOVE_SPEED = 0.2f; 
	static float ROTATION_SPEED = 1.1F;
	boolean isFocus = true;
	public void move(){
		// 后续通过缓存键盘状态来实现控制
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=MOVE_SPEED;
			System.out.println("w press");
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			position.z+=MOVE_SPEED;

		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			position.x-=MOVE_SPEED;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			position.x+=MOVE_SPEED;

		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			yaw+=ROTATION_SPEED;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			yaw-=ROTATION_SPEED;
	}
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	@Override
	public void onAction(long delteTime) {
		move();
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
	public int getActionDepth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void setActionDepth(int d) {
		// TODO Auto-generated method stub
		
	}
}
