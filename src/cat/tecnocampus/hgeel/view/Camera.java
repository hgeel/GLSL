package cat.tecnocampus.hgeel.view;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2d;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import cat.tecnocampus.hgeel.input.Keyboard;
import cat.tecnocampus.hgeel.input.Mouse;

public class Camera {
	
	private Vector3f position;
	private Vector3f rotation;
	
	private final float speed = 0.05f;
	private final float sensitivity = 0.2f;
	
	public Camera() {
		this.position = new Vector3f(0, 0, 0);
		this.rotation = new Vector3f(0, 0, 0);
	}
	
	public void update() {
		
		Vector3f delta = new Vector3f(0, 0, 0);
		
		if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_W)) {
			delta.add(forward());
		}
		if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_S)) {
			delta.add(backward());
		}
		if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_D)) {
			delta.add(right());
		}
		if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_A)) {
			delta.add(left());
		}
		if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			delta.add(up());
		}
		if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			delta.add(down());
		}
		
		delta.mul(speed);
		position.add(delta);
		
		Vector2d mouseDelta = Mouse.getDelta();
		rotation.x -= mouseDelta.y * sensitivity;
		rotation.y -= mouseDelta.x * sensitivity;
	}
	
	public void move(Vector3f delta) {
		position.add(delta);
	}
	
	public void rotate(Vector3f delta) {
		rotation.add(delta);
	}
	
	private Vector3f forward() {
		return getRotatedVector(new Vector3f(0, 0, -1));
	}
	
	private Vector3f backward() {
		return getRotatedVector(new Vector3f(0, 0, 1));
	}
	
	private Vector3f right() {
		return getRotatedVector(new Vector3f(-1, 0, 0));
	}
	
	private Vector3f left() {
		return getRotatedVector(new Vector3f(1, 0, 0));
	}
	
	private Vector3f up() {
		return new Vector3f(0, -1, 0);
	}
	
	private Vector3f down() {
		return new Vector3f(0, 1, 0);
	}
	
	private Vector3f getRotatedVector(Vector3f vector) {
		Matrix4f matrix = new Matrix4f().identity().rotateY(-(float) Math.toRadians(rotation.y));
		return vector.mul(matrix);
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public Matrix4f getViewMatrix() {
		Vector3f negPosition = new Vector3f(-position.x, -position.y, -position.z);
		return new Matrix4f().identity()
				.rotateX((float) Math.toRadians(rotation.x))
				.rotateY((float) Math.toRadians(rotation.y))
				.rotateZ((float) Math.toRadians(rotation.z))
				.translate(negPosition);
	}

}
