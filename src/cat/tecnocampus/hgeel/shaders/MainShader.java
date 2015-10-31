package cat.tecnocampus.hgeel.shaders;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class MainShader extends ShaderProgram {
	
	private static final String vertexFile = "res/shaders/main.v";
	private static final String fragmentFile = "res/shaders/main.f";
	
	private int loc_transform;
	
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
	}
	
	public void setTransformMatrix(Matrix4f matrix) {
		matrixBuffer.clear();
		matrix.get(matrixBuffer);
		GL20.glUniformMatrix4fv(loc_transform, false, matrixBuffer);
	}

}
