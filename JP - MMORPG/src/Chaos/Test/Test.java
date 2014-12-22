package Chaos.Test;

import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Chaos;
import Chaos.Engine.Core.ChaosGame;
import Chaos.Engine.Core.Resource;
import Chaos.Util.Texture.Text;

public class Test extends ChaosGame {
	// Start
	public static void main(String[] args) {
		new Test();
	}

	public Test() {
		chaos = new Chaos(this);
		chaos.setScreenSize(800, 600);
		chaos.setScreenFull(false);
		chaos.setScreenTitle("Test-Chaos");
		chaos.start();
	}

	// Engine
	Chaos chaos;

	// Loop
	public void loadResources(Resource resources) {
		// TODO Resource.java not fully implemented
		// resources.addTexture("font", "./Resources/font.png");
	}

	public void update(float delta) {
	}

	public void render3D() {

	}

	public void render2D() {
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glTranslatef(400, 300, 0);
		Chaos.text.draw("Test Screen", 2, Text.ALIGN.CENTER);
		GL11.glColor4f(0.8f, 0.6f, 0.3f, 1.0f);
		GL11.glTranslatef(400, -300 + 8, 0);
		Chaos.text.draw("Copyright (c) 2014", .5f, Text.ALIGN.RIGHT);
		GL11.glTranslatef(0, -8, 0);
		Chaos.text.draw("Peter Esser & Jonas Prinz", .5f, Text.ALIGN.RIGHT);
	}
}
