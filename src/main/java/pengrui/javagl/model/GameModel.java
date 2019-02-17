package pengrui.javagl.model;

import java.util.Collection;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.NonInputGameObject;
import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.cores.Lifecyclable;
import pengrui.javagl.abstraction.models.VaoPair;
import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.MatrixUtil;
import pengrui.javagl.abstraction.util.ResourceUtil;
import pengrui.javagl.shader.ShaderImpl;
import pengrui.javagl.texture.ImageType;

public class GameModel extends NonInputGameObject implements Lifecyclable{
	
	public GameModel(){
		this((Vector3f)null);
	}
	
	public GameModel(Vector3f position){
		this(position,null);
	}
	public <T extends Shaderable> GameModel(Shaderable shader){
		this(null,shader);
	}
	
	public  <T extends Shaderable>GameModel(Vector3f pos,Shaderable shader){
		this(pos,null,shader);
	}
	public  <T extends Shaderable>GameModel(Vector3f pos,Vector3f rot,Shaderable shader){
		this(pos,rot,null,shader);
	} 
	
	public  <T extends Shaderable>GameModel(Vector3f pos,Vector3f rot,Vector3f scale,Shaderable shader) {
		
		position = pos;
		rotation = rot;
		this.scale = scale;
		textureID = textureCache;
		shineDamper = 10;
		reflectivity = 1;
		this.shader = shader;
		innerInit();//未完成初始化的变量进行初始化
	}
	void innerInit(){
		if(null == position)
			position = new Vector3f(0.f,0.f,0.f);
		
		if(null == rotation)
			rotation = new Vector3f(); 
		
		if(null == scale)
			scale = new Vector3f(1,1,1);
		
		if(null == shader){
			shader = shaderCache;
			LogUtil.debug("use default shader");
		}
		
		shader.loadOnceGlslVaraibles();
	}
	
	public static int vaoID;
	public static int vertexCount;
	public static int textureCache;
	
	int shineDamper;
	int reflectivity;
	static{
		VaoPair vp =LoaderUtil.loadObjModel(ResourceUtil.getResource("loader/box.obj"));
		vaoID = vp.vaoID;
		vertexCount = vp.indices;
		textureCache = LoaderUtil.loadTexture(ResourceUtil.getResource("texture/box.png"), ImageType.PNG);
	}
	
	int textureID;
	Vector3f position;
	Vector3f rotation;
	
	Vector3f scale;
	Shaderable shader;
	static final Shaderable shaderCache = new ShaderImpl(ResourceUtil.getResource("shader/lightvs"),ResourceUtil.getResource("shader/lightfs"));
	
	protected Object[] assembleGlslVaraible(){
		if(null == glslVaraibleHolder)
			glslVaraibleHolder = new Object[3];
		
		Object[] vars = glslVaraibleHolder;
		Matrix4f transformationMatrix = 
				MatrixUtil.createTransformationMatrix(position, rotation.x, rotation.y, rotation.z, scale.x==0?1.f:scale.x);;
				vars[0] = transformationMatrix;
				vars[1] = Float.valueOf(shineDamper);
				vars[2] = Float.valueOf(reflectivity);
		
		return vars;
	}
	Object[] glslVaraibleHolder;
	
	public Object[] getGlslVaraibles(){
		return assembleGlslVaraible();
	}
	@Override
	public void onDraw() {
		this.shader.useShader();
		GL30.glBindVertexArray(vaoID);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		shader.loadGlslVaraibles(assembleGlslVaraible());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glDrawElements(GL11.GL_TRIANGLES,vertexCount,GL11.GL_UNSIGNED_INT,0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		this.shader.unuseShader();
	}
	@Override
	public Actionable getActionableParent() {
		return null;
	}
	@Override
	public void setActionableParent(Actionable parent) {
		
	}
	@Override
	public boolean isEnableAction() {
		return true;
	}
	@Override
	public void setEnableAction(boolean en) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actions(long delteTime) {
		// TODO Auto-generated method stub
	}
	protected void animation1(){
		float rotx = r.nextFloat()*2-1; //震动效果
		float roty = r.nextFloat()*2-1;
		float rotz = r.nextFloat()*2-1;
		rotation.x+=rotx;
		rotation.y+=roty;
		rotation.z+=rotz;
		
	}
	
	
	final Random r= new Random();
	final boolean negative = r.nextBoolean();
	final boolean x = r.nextBoolean(),y=r.nextBoolean(),z=r.nextBoolean();
	private void animation2(){
		float tmp = r.nextFloat()*2;
		float rotx = negative?-tmp:tmp;
		float roty = rotx;
		float rotz = rotx;
		if(x)
			rotation.x+=rotx;
		if(y)
			rotation.y+=roty;
		if(z)
			rotation.z+=rotz;
		
	}
	
	protected void animation3(){
		float rotx = r.nextFloat()*2;
		float roty = r.nextFloat()*2;
		float rotz = r.nextFloat()*2;
		rotation.x+=rotx;
		rotation.y+=roty;
		rotation.z+=rotz;
	}
	private void incrementRotate(){
		animation2();
	}
	@Override
	public void onAction(long delteTime) {
		incrementRotate();
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
	public Collection<Actionable> getActionableChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addActionableChild(Actionable child) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeActionableChild(Actionable child) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Drawable getDrawableParent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDrawableParent(Drawable parent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isEnableDraw() {
		return true;
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
	public boolean isEnableDrawChildren() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setEnableDrawChildren(boolean en) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Drawable> getDrawableChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addDrawableChild(Drawable child) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeDrawableChild(Drawable child) {
		// TODO Auto-generated method stub
	}
	@Override
	public void init() {
	
	}
	@Override
	public void destroy() {
		GL30.glDeleteVertexArrays(vaoID);
		shader.cleanup();
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
	public Shaderable getShader() {
		return shader;
	}

	@Override
	public void setShader(Shaderable shader) {
		this.shader = shader;
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
		return 1;
	}

	@Override
	public void setActionDepth(int d) {
		
	}

	@Override
	public int getDrawDepth() {
		return 1;
	}

	@Override
	public void setDrawDepth(int d) {
		
	}
}
