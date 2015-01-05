package Chaos.Engine.Core;

public abstract class Scene {
	public abstract void create();

	public abstract void destroy();

	public abstract void update(float delta);

	public abstract void render3D();

	public abstract void render2D();
}
