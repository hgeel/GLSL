package cat.tecnocampus.hgeel.game;

import cat.tecnocampus.hgeel.models.Mesh;
import cat.tecnocampus.hgeel.models.ModelLoader;
import cat.tecnocampus.hgeel.render.ModelRenderer;
import cat.tecnocampus.hgeel.shaders.MainShader;

public class Game {
	
	Mesh mesh;
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
		shader = new MainShader();
		mesh = ModelLoader.load(indices, vertices);
	}
	
	public void update() {
		
	}
	
	public void render() {
		shader.start();
		ModelRenderer.render(mesh);
		shader.stop();
	}
	
	public void clean() {
		shader.delete();
		ModelLoader.cleanUp();
	}

}
