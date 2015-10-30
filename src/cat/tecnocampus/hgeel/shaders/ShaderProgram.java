package cat.tecnocampus.hgeel.shaders;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public abstract class ShaderProgram {
	
	private int programID;
	private int vertexID;
	private int fragmentID;
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		programID = glCreateProgram();
		vertexID = loadSource(vertexFile, GL_VERTEX_SHADER);
		fragmentID = loadSource(fragmentFile, GL_FRAGMENT_SHADER);
		glAttachShader(programID, vertexID);
		glAttachShader(programID, fragmentID);
		bindAttributes();
		glLinkProgram(programID);
		glValidateProgram(programID);
		getUniformLocations();
	}
	
	protected abstract void bindAttributes();
	protected abstract void getUniformLocations();
	
	protected void bindAttribute(int attribute, String name) {
		glBindAttribLocation(programID, attribute, name);
	}
	
	protected int getUniformLocation(String name) {
		return glGetUniformLocation(programID, name);
	}
	
	protected void loadFloat(int location, float value) {
		glUniform1f(location, value);
	}
	
	protected void loadVector3f(int location, Vector3f vector) {
		glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	protected void loadMatrix4f(int location, Matrix4f matrix) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4*4);
		matrix.get(buffer);
		buffer.flip();
		glUniformMatrix4fv(location, false, buffer);
	}
	
	public void start() {
		glUseProgram(programID);
	}
	
	public void stop() {
		glUseProgram(0);
	}
	
	public void delete() {
		glDetachShader(programID, vertexID);
		glDetachShader(programID, fragmentID);
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		glDeleteProgram(programID);
	}
	
	protected int loadSource(String file, int type) {
		StringBuilder source = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine()) != null) {
				source.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, source);
		glCompileShader(shaderID);
		if(glGetShaderi(shaderID, GL_COMPILE_STATUS) != GL_TRUE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader.");
			System.exit(-1);
		}
		return shaderID;
	}

}
