package cat.tecnocampus.hgeel.game;

import org.joml.Vector3f;

import cat.tecnocampus.hgeel.entity.Entity;
import cat.tecnocampus.hgeel.models.ModelLoader;
import cat.tecnocampus.hgeel.render.ModelRenderer;
import cat.tecnocampus.hgeel.shaders.MainShader;

public class Game {
	
	Entity entity;
	MainShader shader;
	
	int[] indices = new int[] {
			0, 1, 3, 3, 1, 2
	};
	
	float[] vertices = new float[] {
			-0.5f, 0.5f, 0f,
			-0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
			0.5f, 0.5f, 0f
	};
	
	public void init() {
		entity = new Entity(ModelLoader.load(indices, vertices));
		shader = new MainShader();
	}
	
	public void update() {
		entity.rotate(new Vector3f(0, 0.5f, 0));
	}
	
	public void render() {
		shader.start();
		ModelRenderer.render(entity, shader);
		shader.stop();
	}
	
	public void clean() {
		shader.delete();
		ModelLoader.cleanUp();
	}

}
