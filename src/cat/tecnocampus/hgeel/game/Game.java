package cat.tecnocampus.hgeel.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import cat.tecnocampus.hgeel.engine.handlers.Display;
import cat.tecnocampus.hgeel.entity.Entity;
import cat.tecnocampus.hgeel.input.Mouse;
import cat.tecnocampus.hgeel.models.ModelLoader;
import cat.tecnocampus.hgeel.render.ModelRenderer;
import cat.tecnocampus.hgeel.shaders.MainShader;
import cat.tecnocampus.hgeel.view.Camera;

public class Game {
	
	Entity entity;
	Camera camera;
	MainShader shader;
	
	Matrix4f viewMatrix = new Matrix4f().perspective(
			80f,
			(float) Display.WIDTH / Display.HEIGHT, 
			0.1f, 
			100f
	);
	
	int[] indices = new int[] {
			0, 1, 2, 2, 0, 3,	//Front
			4, 5, 7, 7, 5, 6,	//Back
			3, 2, 6, 6, 2, 7,	//Right
			4, 5, 1, 1, 4, 0,	//Left
			1, 5, 6, 6, 1, 2,	//Top
			4, 0, 3, 3, 4, 5	//Bottom
	};
	
	float[] vertices = new float[] {
			//Front clockwise
			-0.5f, -0.5f, -0.5f,
			-0.5f, 0.5f, -0.5f,
			0.5f, 0.5f, -0.5f,
			0.5f, -0.5f, -0.5f,
			
			//Back clockwise
			-0.5f, -0.5f, 0.5f,
			-0.5f, 0.5f, 0.5f,
			0.5f, 0.5f, 0.5f,
			0.5f, -0.5f, 0.5f,
	};
	
	public void init() {
		Mouse.hide();
		entity = new Entity(ModelLoader.load(indices, vertices));
		entity.setPosition(new Vector3f(0, 0, -1));
		camera = new Camera();
		shader = new MainShader();
	}
	
	public void update() {
		Mouse.update();
		camera.update();
	}
	
	public void render() {
		shader.start();
		shader.setViewMatrix(viewMatrix);
		shader.setCameraMatrix(camera.getViewMatrix());
		ModelRenderer.render(entity, shader);
		shader.stop();
	}
	
	public void clean() {
		Mouse.show();
		shader.delete();
		ModelLoader.cleanUp();
	}

}
