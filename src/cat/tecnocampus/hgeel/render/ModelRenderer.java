package cat.tecnocampus.hgeel.render;

import cat.tecnocampus.hgeel.models.Mesh;

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

}
