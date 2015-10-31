package cat.tecnocampus.hgeel.shaders;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class MainShader extends ShaderProgram {
	
	private static final String vertexFile = "res/shaders/main.v";
	private static final String fragmentFile = "res/shaders/main.f";
	
	private int loc_transform;
	private int loc_view;
	private int loc_camera;
	
	FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(4*4);

	public MainShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getUniformLocations() {
		loc_transform = GL20.glGetUniformLocation(this.programID, "transform");
		loc_view = GL20.glGetUniformLocation(this.programID, "view");
		loc_camera = GL20.glGetUniformLocation(this.programID, "camera");
	}
	
	public void setTransformMatrix(Matrix4f matrix) {
		matrixBuffer.clear();
		matrix.get(matrixBuffer);
		GL20.glUniformMatrix4fv(loc_transform, false, matrixBuffer);
	}
	
	public void setCameraMatrix(Matrix4f matrix) {
		matrixBuffer.clear();
		matrix.get(matrixBuffer);
		GL20.glUniformMatrix4fv(loc_camera, false, matrixBuffer);
	}
	
	public void setViewMatrix(Matrix4f matrix) {
		matrixBuffer.clear();
		matrix.get(matrixBuffer);
		GL20.glUniformMatrix4fv(loc_view, false, matrixBuffer);
	}

}
