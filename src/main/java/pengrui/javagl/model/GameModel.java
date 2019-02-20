package pengrui.javagl.model;

import java.util.Collection;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.abstraction.animation.Animationable;
import pengrui.javagl.abstraction.basics.HasChildrenable;
import pengrui.javagl.abstraction.cores.Actionable;
import pengrui.javagl.abstraction.cores.Drawable;
import pengrui.javagl.abstraction.cores.Lifecyclable;
import pengrui.javagl.abstraction.factorys.ActionManagerFactory;
import pengrui.javagl.abstraction.factorys.DrawManagerFactory;
import pengrui.javagl.abstraction.models.ModelData;
import pengrui.javagl.abstraction.models.SpaceData;
import pengrui.javagl.abstraction.shaders.IShaderableManager;
import pengrui.javagl.abstraction.shaders.Shaderable;
import pengrui.javagl.abstraction.util.LogUtil;
import pengrui.javagl.abstraction.util.MatrixUtil;

public class GameModel implements HasChildrenable<GameModel>,Drawable,Actionable,Lifecyclable{
	
	public GameModel(){
		this(SpaceData.cache,ModelData.cache);
	}
	
	public  <T extends Shaderable>GameModel(SpaceData sd,ModelData md) {
		vaoID = md.vaoID;
		vertexCount = md.vertexCount;
		textureID = md.textureID;
		shader = md.shader;
		position = sd.position;
		rotation = sd.rotation;
		scale = sd.scale;
		shineDamper = 10;
		reflectivity = 1;
		innerInit();//未完成初始化的变量进行初始化
		DrawManagerFactory.getInstance().register(this);
		ActionManagerFactory.getInstance().register(this);
	}
	void innerInit(){
		if(null == position)
			position = new Vector3f(0.f,0.f,0.f);
		
		if(null == rotation)
			rotation = new Vector3f(); 
		
		if(null == scale)
			scale = new Vector3f(1,1,1);
		
		if(null == shader){
			shader = IShaderableManager.cache;
			LogUtil.debug("use default shader");
		}
		
		shader.loadOnceGlslVaraibles();
	}
	
	public int vaoID;
	public int vertexCount;
	
	int shineDamper;
	int reflectivity;
	
	int textureID;
	Vector3f position;
	Vector3f rotation;
	
	Vector3f scale;
	Shaderable shader;
	
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
	protected void animation2(){
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
	public boolean isEnableDraw() {
		return true;
	}
	@Override
	public void setEnableDraw(boolean en) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public final void draws() {
		Drawable.draws(this);
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
	public void init() {
	
	}
	@Override
	public void destroy() {
		GL30.glDeleteVertexArrays(vaoID);
		shader.cleanup();
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
	public void setDepth(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 1;
	}


	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<GameModel> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(GameModel child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild(GameModel child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameModel getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(GameModel parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasTheChild(GameModel child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnableAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setEnableAction(boolean en) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void actions(long delteTime) {
		Actionable.actions(this,delteTime);
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
	public Collection<Animationable> getAnimations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAnimation(Animationable animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isEnableAnimation() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setEnableAnimation(boolean en) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildren(Collection<GameModel> c) {
	}

	@Override
	public void setAnimations(Collection<Animationable> animations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAnimation(Animationable animation) {
		// TODO Auto-generated method stub
		
	}

}
