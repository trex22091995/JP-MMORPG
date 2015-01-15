package Chaos.Util.Collision;
import org.lwjgl.opengl.GL11;

public class Box {
	private float x, y, z, dx, dy, dz;

	public Box() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.dx = 0;
		this.dy = 0;
		this.dz = 0;
	}

	public void renderHitBox() {
		// TEST BOX
		GL11.glPushMatrix();
		GL11.glColor4f(1, 0, 0, 1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glTranslatef(getX(), getY(), getZ());
		GL11.glBegin(GL11.GL_LINES);
		// Unten
		GL11.glVertex3f(-dx, -dy, -dz);
		GL11.glVertex3f(dx, -dy, -dz);
		GL11.glVertex3f(dx, -dy, -dz);
		GL11.glVertex3f(dx, -dy, dz);
		GL11.glVertex3f(dx, -dy, dz);
		GL11.glVertex3f(-dx, -dy, dz);
		GL11.glVertex3f(-dx, -dy, dz);
		GL11.glVertex3f(-dx, -dy, -dz);
		// Oben
		GL11.glVertex3f(-dx, dy, -dz);
		GL11.glVertex3f(dx, dy, -dz);
		GL11.glVertex3f(dx, dy, -dz);
		GL11.glVertex3f(dx, dy, dz);
		GL11.glVertex3f(dx, dy, dz);
		GL11.glVertex3f(-dx, dy, dz);
		GL11.glVertex3f(-dx, dy, dz);
		GL11.glVertex3f(-dx, dy, -dz);
		// Seiten
		GL11.glVertex3f(-dx, -dy, -dz);
		GL11.glVertex3f(-dx, dy, -dz);
		GL11.glVertex3f(dx, -dy, -dz);
		GL11.glVertex3f(dx, dy, -dz);
		GL11.glVertex3f(dx, -dy, dz);
		GL11.glVertex3f(dx, dy, dz);
		GL11.glVertex3f(-dx, -dy, dz);
		GL11.glVertex3f(-dx, dy, dz);
		GL11.glEnd();
		GL11.glPopMatrix();
		// END BOX
	}

	public Box(float x, float y, float z, float dx, float dy, float dz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	public boolean check(Box box) {
		if (box != null) {
			if (getX() + getDx() > box.getX() - box.getDx()) {
				if (getX() - getDx() < box.getX() + box.getDx()) {
					if (getY() + getDy() > box.getY() - box.getDy()) {
						if (getY() - getDy() < box.getY() + box.getDy()) {
							if (getZ() + getDz() > box.getZ() - box.getDz()) {
								if (getZ() - getDz() < box.getZ() + box.getDz()) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	// GET&SET
	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}

	public float getDz() {
		return dz;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public void setDz(float dz) {
		this.dz = dz;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
