package VoxelMOBA;

import Chaos.Util.Collision.Box;
import Chaos.Util.Collision.ColMap;

public abstract class Entity {
	public float x, y, z, dx, dy, dz, rot;
	public Box box;
	public boolean ground;
	public ColMap cm;

	public Entity(float x, float y, float z, float width, float height,
			float depth, ColMap cm) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.cm = cm;
		ground = false;
		box = new Box(x, y, z, width, height, depth);
	}

	public final void update(float delta) {
		logic(delta);
		Box col = null;
		// Apply Gravity
		dy -= delta * 15;
		// X
		x += dx * delta;
		box.setX(x);
		col = cm.check(box);
		if (col != null) {
			if (dx > 0) {
				// x +
				x = col.getX() - col.getDx() - box.getDx() - 0.0001f;
			} else {
				// x -
				x = col.getX() + col.getDx() + box.getDx() + 0.0001f;
			}
			dx = 0;
			box.setX(x);
		}
		// Y
		ground = false;
		y += dy * delta;
		box.setY(y);
		col = cm.check(box);
		if (col != null) {
			if (dy > 0) {
				// y +
				y = col.getY() - col.getDy() - box.getDy() - 0.0001f;
			} else {
				// y -
				y = col.getY() + col.getDy() + box.getDy() + 0.0001f;
			}
			dy = 0;
			ground = true;
			box.setY(y);
		}
		// Z
		z += dz * delta;
		box.setZ(z);
		col = cm.check(box);
		if (col != null) {
			if (dz > 0) {
				// z +
				z = col.getZ() - col.getDz() - box.getDz() - 0.0001f;
			} else {
				// z -
				z = col.getZ() + col.getDz() + box.getDz() + 0.0001f;
			}
			dz = 0;
			box.setZ(z);
		}
	}

	public abstract void logic(float delta);

	public abstract void render();
}
