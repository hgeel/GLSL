package cat.tecnocampus.hgeel.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import cat.tecnocampus.hgeel.models.Mesh;

public class Entity {
	
	private Mesh model;
	private Vector3f position = new Vector3f(0, 0, 0);
	private Vector3f rotation = new Vector3f(0, 0, 0);
	private Vector3f scale = new Vector3f(1, 1, 1);
	
	public Entity(Mesh model) {
		this.model = model;
	}
	
	public void move(Vector3f delta) {
		position.add(delta);
	}
	
	public void rotate(Vector3f delta) {
		rotation.add(delta);
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public Mesh getModel() {
		return model;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public Vector3f getScale() {
		return scale;
	}
	
	public Matrix4f getTransformMatrix() {
		return new Matrix4f().identity()
				.translate(position)
				.rotateX((float) Math.toRadians(rotation.x))
				.rotateY((float) Math.toRadians(rotation.y))
				.rotateZ((float) Math.toRadians(rotation.z))
				.scale(scale);
	}

}
