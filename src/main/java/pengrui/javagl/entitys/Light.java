package pengrui.javagl.entitys;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	Vector3f position;
	Vector3f colour;
	
	static Light instance;
	public static void manualInit(Vector3f position, Vector3f colour){
		instance = new Light(position, colour);
	}
	public static Light getInstance(){
		return instance;
	}
	
	public Light(Vector3f position, Vector3f colour) {
		super();
		this.position = position;
		this.colour = colour;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getColour() {
		return colour;
	}
	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
}
