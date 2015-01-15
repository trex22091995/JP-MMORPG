package Chaos.Util.Collision;
public class ColMap {
	public ColChunk[] chunks = new ColChunk[0]; // Chunks
	public Box box; // Collision Box
	public float x, y, z;

	public void drawDebug() {
		// General draw
		box.renderHitBox();
		// Chunk draw
		for (int i = 0; i < chunks.length; i++) {
			chunks[i].drawDebug();
		}
	}

	public Box check(Box box) {
		if (box.check(this.box)) {
			for (int i = 0; i < chunks.length; i++) {
				Box out = chunks[i].check(box);
				if (out != null) {
					return out;
				}
			}
		}
		return null;
	}

	public void setPosition(float x, float y, float z) {
		float dx = x - this.x;
		float dy = y - this.y;
		float dz = z - this.z;
		this.x = x;
		this.y = y;
		this.z = z;
		// Move all boxes for dx/dy/dz
		box.setX(box.getX() + dx);
		box.setY(box.getY() + dy);
		box.setZ(box.getZ() + dz);
	}
}
