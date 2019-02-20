package pengrui.javagl.abstraction.models;

import org.lwjgl.util.vector.Vector3f;

public class SpaceData {
	public Vector3f position;
	public Vector3f rotation;
	public Vector3f scale;
	public SpaceData(Vector3f position, Vector3f rotation, Vector3f scale) {
		super();
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public static final SpaceData cache = 
			new SpaceData(new Vector3f(0,0,-20), new Vector3f(0,0,0), new Vector3f(1,1,1));
}
