package Chaos.Util.Collision;
public class ColChunk {
	public ColBlock[] blocks = new ColBlock[0]; // Blocks
	public Box box; // Collision box

	public void drawDebug() {
		// General Draw
		box.renderHitBox();
		// Block Draw
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].drawDebug();
		}
	}

	public Box check(Box box) {
		if (box.check(this.box)) {
			for (int i = 0; i < blocks.length; i++) {
				Box out = blocks[i].check(box);
				if (out != null) {
					return out;
				}
			}
		}
		return null;
	}
}
