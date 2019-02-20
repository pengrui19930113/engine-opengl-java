package pengrui.javagl.entitys;


import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.OnlyInputGameObject;
import pengrui.javagl.abstraction.events.IEvent;

public class Camera extends OnlyInputGameObject{
	Vector3f position = new Vector3f(0,0,-10f);
	float pitch; // 倾斜 // 仰角 //相机坐标系中的 X
	float yaw;	// 偏航角 //相机坐标系中的Y
	float roll; // 翻滚 //相机坐标系中的Z
	
	static Camera instance = new Camera();
	public static Camera getInstance(){
		return instance;
	}
	
	public Camera() {
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
	public void onInput(IEvent evn) {
		move();
	}


}
