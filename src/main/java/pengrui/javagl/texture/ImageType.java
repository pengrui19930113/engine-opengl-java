package pengrui.javagl.texture;

public enum ImageType {
	PNG("PNG")
	,JPG("JPG")
	;
	public final String TYPE_NAME;
	private ImageType(String typeName){
		TYPE_NAME = typeName;
	}
}
