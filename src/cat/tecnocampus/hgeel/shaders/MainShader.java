package cat.tecnocampus.hgeel.shaders;

public class MainShader extends ShaderProgram {
	
	private static final String vertexFile = "res/shaders/main.v";
	private static final String fragmentFile = "res/shaders/main.f";

	public MainShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getUniformLocations() {
		
	}

}
