package pengrui.javagl.abstraction.util;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import pengrui.javagl.entitys.Camera;

public class MatrixUtil {
	static final Vector3f xNormalizVec = new Vector3f(1,0,0);
	static final Vector3f yNormalizVec = new Vector3f(0,1,0);
	static final Vector3f zNormalizVec = new Vector3f(0,0,1);
	public static Matrix4f createTransformationMatrix(Vector3f translation,float rx,float ry, float rz,float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rx),xNormalizVec,matrix,matrix);
		Matrix4f.rotate((float)Math.toRadians(ry),yNormalizVec,matrix,matrix);
		Matrix4f.rotate((float)Math.toRadians(rz),zNormalizVec,matrix,matrix);
		Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(-/*注意此处的负号  和位移同理*/camera.getPitch()),xNormalizVec,matrix,matrix); //
		Matrix4f.rotate((float)Math.toRadians(-camera.getYaw()),yNormalizVec,matrix,matrix);
		// 这里没有使用 roll 的旋转， 因为不需要
		Vector3f camPos = camera.getPosition();
		Vector3f negativeCamerPos = new Vector3f(-camPos.x,-camPos.y,-camPos.z);// 由于相机和物体是相对的  ，所以 对于模型视图矩阵 相机的位移操作与物体是相反的
		Matrix4f.translate(negativeCamerPos, matrix, matrix);
		return matrix;
	}
	
	static final float FOV = 70.f;
	static final float NEAR = 0.1F;
	static final float FAR = 1000.f;
	
	static final Matrix4f PROJECTION_MATRIX = new Matrix4f();
	
	public static Matrix4f createProjectionMatrix(){
		float aspectRatio = (float)Display.getWidth()/(float)Display.getHeight();
		float y_scale = (float)((1.f/Math.tan(Math.toRadians(FOV/2.F)))*aspectRatio);
		float x_scale = y_scale/aspectRatio;
		float frustum_length = FAR-NEAR;
		Matrix4f projectionMatrix = PROJECTION_MATRIX;
		projectionMatrix.setIdentity();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR+NEAR)/frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2*NEAR*FAR)/frustum_length);
		projectionMatrix.m33 = 0;
		return projectionMatrix;
	}
}
