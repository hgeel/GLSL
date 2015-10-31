package cat.tecnocampus.hgeel.input;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import cat.tecnocampus.hgeel.engine.handlers.Display;

import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;

public class Keyboard extends GLFWKeyCallback {
	
	private static HashMap<Integer, Boolean> keys = new HashMap<>();

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
			glfwSetWindowShouldClose(Display.window, GL11.GL_TRUE);
		}
		keyEvent(key, action);
	}
	
	private void keyEvent(int key, int action) {
		if(action == GLFW_PRESS) {
			keys.put(key, true);
		} else if(action == GLFW_RELEASE) {
			keys.put(key, false);
		}
	}
	
	public static boolean isKeyPressed(int glfwKey) {
		if(keys.containsKey(glfwKey)) {
			return keys.get(glfwKey);
		}
		return false;
	}
	
}
