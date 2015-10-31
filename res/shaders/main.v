#version 400 core

in vec3 position;

out vec3 color;

uniform mat4 transform;
uniform mat4 view;
uniform mat4 camera;

void main(void) {
	gl_Position = view * camera * transform * vec4(position, 1.0);
	color = vec3(position.x + 0.5, position.y + 0.5, position.z + 0.5);
}
