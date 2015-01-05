package Chaos.Engine.Core;

public abstract class ChaosGame {
	private Scene current, next;

	public abstract void loadResources(Resource resources);

	public abstract void update(float delta);

	public abstract void render3D();

	public abstract void render2D();

	public final void engineUpdate(float delta) {
		if (current != null)
			current.update(delta);
		// Game
		update(delta);
		// Change Scene
		if (next != null) {
			if (current != null)
				current.destroy();
			current = next;
			current.create();
			next = null;
		}
	}

	public final void engineRender3D() {
		if (current != null)
			current.render3D();
		// Game
		render3D();
	}

	public final void engineRender2D() {
		if (current != null)
			current.render2D();
		// Game
		render2D();
	}

	public final void setScene(Scene next) {
		this.next = next;
	}
}
