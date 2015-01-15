package VoxelMOBA;

import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Chaos;
import Chaos.Engine.Core.ChaosGame;
import Chaos.Engine.Core.Resource;
import Chaos.Util.Texture.Text;

public class VoxelMOBA extends ChaosGame {
	// Start
	public static void main(String[] args) {
		new VoxelMOBA();
	}

	public VoxelMOBA() {
		// Init Code for the Engine
		chaos = new Chaos(this);
		chaos.setScreenSize(800, 600);
		chaos.setScreenFull(false);
		chaos.setScreenTitle("Voxel-MOBA");
		chaos.start();
	}

	// Engine
	Chaos chaos;

	// Loop
	public void loadResources(Resource resources) {

	}

	// Update Loop
	boolean hasScene = false;

	public void update(float delta) {
		if (!hasScene) {
			hasScene = true;
			setScene(new GameScene());
		}
		// rot += delta * 90;
		// if (rot >= 360)
		// rot -= 360;
	}

	float rot = 0;

	// 3D Rendering
	public void render3D() {

	}

	// 2D Rendering
	public void render2D() {
		GL11.glColor4f(0.8f, 0.6f, 0.3f, 1.0f);
		GL11.glTranslatef(800, 8, 0);
		Chaos.text.draw("Copyright (c) 2014", .5f, Text.ALIGN.RIGHT);
		GL11.glTranslatef(0, -8, 0);
		Chaos.text.draw("Peter Esser & Jonas Prinz", .5f, Text.ALIGN.RIGHT);
	}
}
