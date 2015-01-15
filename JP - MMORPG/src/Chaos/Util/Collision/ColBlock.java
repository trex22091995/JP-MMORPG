package Chaos.Util.Collision;
public class ColBlock {
	public Box box;

	public void drawDebug() {
		// Draw General
		box.renderHitBox();
	}

	public Box check(Box box) {
		if (box.check(this.box)) {
			return this.box;
		}
		return null;
	}
}
