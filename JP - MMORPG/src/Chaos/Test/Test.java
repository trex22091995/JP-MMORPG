package Chaos.Test;

import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Chaos;
import Chaos.Engine.Core.ChaosGame;
import Chaos.Engine.Core.Resource;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Texture.Text;

public class Test extends ChaosGame {
	// Start
	public static void main(String[] args) {
		new Test();
	}

	public Test() {
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
		resources.addModel("Monkey1", "./Resources/untitled.obj");
		resources.addModel("Monkey2", "./Resources/untitled.obj");
		resources.addModel("Monkey3", "./Resources/untitled.obj");
		resources.addModel("Monkey4", "./Resources/untitled.obj");
		resources.addModel("Monkey5", "./Resources/untitled.obj");
		resources.addModel("Monkey6", "./Resources/untitled.obj");
		// Nur zur demonstration vom Ladebildschirm 6 mal das gleiche xD
	}

	// Update Loop
	public void update(float delta) {
		rot += delta * 90;
		if (rot >= 360)
			rot -= 360;
	}

	float rot = 0;

	// 3D Rendering
	public void render3D() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glColor3f(0.2f, 0.8f, 0.8f);
		GL11.glPushMatrix();
		GL11.glTranslatef(-5, 4, -15);
		GL11.glRotatef(rot, 1, 0, 0);
		ModelStore.get("Monkey1").draw();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(-5, -4, -15);
		GL11.glRotatef(rot, -1, 0, 0);
		ModelStore.get("Monkey2").draw();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(0, 4, -15);
		GL11.glRotatef(rot, 0, 1, 0);
		ModelStore.get("Monkey3").draw();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -4, -15);
		GL11.glRotatef(rot, 0, -1, 0);
		ModelStore.get("Monkey4").draw();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(5, 4, -15);
		GL11.glRotatef(rot, 0, 0, 1);
		ModelStore.get("Monkey5").draw();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(5, -4, -15);
		GL11.glRotatef(rot, 0, 0, -1);
		ModelStore.get("Monkey6").draw();
		GL11.glPopMatrix();
	}

	// 2D Rendering
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
