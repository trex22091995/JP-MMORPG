package VoxelMOBA;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Chaos.Util.Collision.ColMap;

public abstract class EntityPlayer extends Entity {
	public float mouserot;

	public EntityPlayer(float x, float y, float z, float width, float height,
			float depth, ColMap cm) {
		super(x, y, z, width, height, depth, cm);
	}

	public final void logic(float delta) {

	}

	public final void poll(float delta) {
		mouserot -= Mouse.getDX() * 0.7f;
		if (mouserot < 0) {
			mouserot += 360;
		}
		if (mouserot >= 360) {
			mouserot -= 360;
		}
		input();
	}

	public final void cam() {
		GL11.glTranslatef(0, -3, -10);
		GL11.glRotatef(20, 1, 0, 0);
		GL11.glRotatef(180 + mouserot, 0, -1, 0);
		GL11.glTranslatef(-x, -y, -z);
	}

	public abstract void input();
}
