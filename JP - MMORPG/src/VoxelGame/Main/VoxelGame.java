package VoxelGame.Main;

import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Chaos;
import Chaos.Engine.Core.ChaosGame;
import Chaos.Engine.Core.Resource;
import Chaos.Util.Texture.Text;

public class VoxelGame extends ChaosGame {
	// Start
	public static void main(String[] args) {
		new VoxelGame();
	}

	public VoxelGame() {
		// Init Code for the Engine
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
		// Resource Loading (Only Models for now)
		// resources.addModel("Model1", "./Resources/chr_knight.obj");
		// resources.addModel("Model2", "./Resources/chr_sword.obj");
		// resources.addModel("Model3", "./Resources/chr_fox.obj");
		// resources.addModel("Model4", "./Resources/chr_gumi.obj");
		// resources.addModel("Model5", "./Resources/chr_jp.obj");
		// resources.addModel("Model6", "./Resources/chr_rain.obj");
		// resources.addTexture("Texture", "./Resources/chr_knight.png");
		// Nur zur demonstration vom Ladebildschirm 6 mal das gleiche xD
	}

	// Update Loop
	boolean hasScene = false;

	public void update(float delta) {
		if (!hasScene) {
			hasScene = true;
			setScene(new TestScene());
		}
		// rot += delta * 90;
		// if (rot >= 360)
		// rot -= 360;
	}

	float rot = 0;

	// 3D Rendering
	public void render3D() {
		// GL11.glColor3f(1, 1, 1);
		// TextureStore.get("Texture").bind();
		// GL11.glPushMatrix();
		// GL11.glTranslatef(-5, 4, -15);
		// GL11.glRotatef(rot, 0, 1, 0);
		// GL11.glScalef(.1f, .1f, .1f);
		// ModelStore.get("Model1").draw();
		// GL11.glPopMatrix();
		// GL11.glPushMatrix();
		// GL11.glTranslatef(-5, -4, -15);
		// GL11.glRotatef(rot, 0, 1, 0);
		// GL11.glScalef(.1f, .1f, .1f);
		// ModelStore.get("Model2").draw();
		// GL11.glPopMatrix();
		// GL11.glPushMatrix();
		// GL11.glTranslatef(0, 4, -15);
		// GL11.glRotatef(rot, 0, 1, 0);
		// GL11.glScalef(.1f, .1f, .1f);
		// ModelStore.get("Model3").draw();
		// GL11.glPopMatrix();
		// GL11.glPushMatrix();
		// GL11.glTranslatef(0, -4, -15);
		// GL11.glRotatef(rot, 0, 1, 0);
		// GL11.glScalef(.1f, .1f, .1f);
		// ModelStore.get("Model4").draw();
		// GL11.glPopMatrix();
		// GL11.glPushMatrix();
		// GL11.glTranslatef(5, 4, -15);
		// GL11.glRotatef(rot, 0, 1, 0);
		// GL11.glScalef(.1f, .1f, .1f);
		// ModelStore.get("Model5").draw();
		// GL11.glPopMatrix();
		// GL11.glPushMatrix();
		// GL11.glTranslatef(5, -4, -15);
		// GL11.glRotatef(rot, 0, 1, 0);
		// GL11.glScalef(.1f, .1f, .1f);
		// ModelStore.get("Model6").draw();
		// GL11.glPopMatrix();
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
