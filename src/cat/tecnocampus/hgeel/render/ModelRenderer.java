package cat.tecnocampus.hgeel.render;

import cat.tecnocampus.hgeel.entity.Entity;
import cat.tecnocampus.hgeel.models.Mesh;
import cat.tecnocampus.hgeel.shaders.MainShader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class ModelRenderer {
	
	public static void render(Mesh mesh) {
		glBindVertexArray(mesh.getVaoID());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
	public static void render(Entity entity, MainShader shader) {
		glBindVertexArray(entity.getModel().getVaoID());
		glEnableVertexAttribArray(0);
		shader.setTransformMatrix(entity.getTransformMatrix());
		glDrawElements(GL_TRIANGLES, entity.getModel().getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}

}
