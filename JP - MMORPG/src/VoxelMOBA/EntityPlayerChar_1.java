package VoxelMOBA;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import Chaos.Util.Collision.ColMap;
import Chaos.Util.Model.ModelStore;

public class EntityPlayerChar_1 extends EntityPlayer {

	public EntityPlayerChar_1(float x, float y, float z, float width,
			float height, float depth, ColMap cm) {
		super(x, y, z, width, height, depth, cm);
	}

	public void render() {
		if (Keyboard.isKeyDown(Keyboard.KEY_F12))
			box.renderHitBox();
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(rot, 0, 1, 0);
		GL11.glTranslatef(-.4f, -.7f, -.4f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Char").draw();
		GL11.glPopMatrix();
	}

	public void input() {
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && ground) {
			dy = 7;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			rot = mouserot;
		}
	}
}
