package cat.tecnocampus.hgeel.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import cat.tecnocampus.hgeel.engine.handlers.Display;
import cat.tecnocampus.hgeel.game.Game;

public class Engine {
	
	Game game = new Game();
	
	public Engine() {
		init();
		loop();
		clean();
	}
	
	private void init() {
		Display.create();
		game.init();
	}
	
	private void loop() {
		while(glfwWindowShouldClose(Display.window) != GL_TRUE) {
			update();
			render();
		}
	}
	
	private void update() {
		game.update();
	}
	
	private void render() {
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		game.render();
		
		glfwSwapBuffers(Display.window);
		glfwPollEvents();
		
	}
	
	private void clean() {
		game.clean();
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Engine();
	}

}
