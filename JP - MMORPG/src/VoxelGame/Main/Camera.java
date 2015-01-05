package VoxelGame.Main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	// 3d vector to store the camera's position in
	public Vector3f position = null;
	// the rotation around the Y axis of the camera
	public float yaw = 0f;
	// the rotation around the X axis of the camera
	public float pitch = 0f;

	// Constructor that takes the starting x, y, z
	// location of the camera
	public Camera(float x, float y, float z) {
		// instantiate position Vector3f to the x y z params.
		position = new Vector3f(x, y, z);
	}

	// increment the camera's current yaw rotation
	public void yaw(float amount) {
		// increment the yaw by the amount param
		yaw += amount;
	}

	// increment the camera's current pitch rotation
	public void pitch(float amount) {
		// increment the pitch by the amount param
		pitch += amount;
		if (pitch < -90)
			pitch = -90;
		if (pitch > 90)
			pitch = 90;
	}

	public void moveUp(float distance) {
		position.y -= distance * 0.2f;
	}

	public void moveDown(float distance) {
		position.y += distance * 0.2f;
	}

	// moves the camera forward relative to its current rotation (yaw)
	public void walkForward(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw)) * 0.2f;
		position.z += distance * (float) Math.cos(Math.toRadians(yaw)) * 0.2f;
	}

	// moves the camera backward relative to its current rotation (yaw)
	public void walkBackwards(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(yaw)) * 0.2f;
		position.z -= distance * (float) Math.cos(Math.toRadians(yaw)) * 0.2f;
	}

	// strafes the camera left relitive to its current rotation (yaw)
	public void strafeLeft(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90))
				* 0.2f;
		position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90))
				* 0.2f;
	}

	// strafes the camera right relitive to its current rotation (yaw)
	public void strafeRight(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90))
				* 0.2f;
		position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90))
				* 0.2f;
	}

	public void lookThrough() {
		// roatate the pitch around the X axis
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		// roatate the yaw around the Y axis
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		// translate to the position vector's location
		GL11.glTranslatef(position.x, position.y, position.z);
	}

	public void update(float delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			walkForward(0.05f * delta);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			walkBackwards(0.05f * delta);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			strafeLeft(0.05f * delta);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			strafeRight(0.05f * delta);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			moveUp(0.05f * delta);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			moveDown(0.05f * delta);
		}
		yaw(Mouse.getDX() / 10f);
		pitch(-Mouse.getDY() / 10f);
	}
}
