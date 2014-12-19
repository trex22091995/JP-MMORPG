package Chaos.Engine.Core;

import Chaos.Engine.Resource.Resource;

public abstract class ChaosGame {
	public abstract void loadResources(Resource resources);

	public abstract void update(float delta);

	public abstract void render3D();

	public abstract void render2D();
}
