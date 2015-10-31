package cat.tecnocampus.hgeel.input;

import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import cat.tecnocampus.hgeel.engine.handlers.Display;

public class Mouse extends GLFWCursorPosCallback {
	
	private static double invokePosX = 0;
	private static double invokePosY = 0;
	
	private static double posX;
	private static double posY;
	
	private static double deltaX;
	private static double deltaY;

	@Override
	public void invoke(long window, double xpos, double ypos) {
		invokePosX = xpos;
		invokePosY = ypos;
	}
	
	public static void update() {
		deltaX = invokePosX - posX;
		deltaY = invokePosY - posY;
		posX = invokePosX;
		posY = invokePosY;
	}
	
	public static Vector2d getPos() {
		return new Vector2d(posX, posY);
	}
	
	public static Vector2d getDelta() {
		return new Vector2d(deltaX, deltaY);
	}
	
	public static void hide() {
		GLFW.glfwSetInputMode(Display.window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
	}
	
	public static void show() {
		GLFW.glfwSetInputMode(Display.window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}

}
