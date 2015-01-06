package VoxelGame.Main;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Scene;
import Chaos.Util.Misc;
import Chaos.Util.Model.Model;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Model.OBJLoader;
import Chaos.Util.Model.VBO;
import Chaos.Util.Noise.Noise;
import Chaos.Util.Texture.Texture;
import Chaos.Util.Texture.TextureStore;

public class TestScene extends Scene {
	Camera cam = new Camera(0, 0, 0);

	public void create() {
		try {
			ModelStore.add("Model1",
					new Model(OBJLoader.load("./Resources/chr_fox.obj")));
			ModelStore.add("Model2",
					new Model(OBJLoader.load("./Resources/chr_gumi.obj")));
			ModelStore.add("Model3",
					new Model(OBJLoader.load("./Resources/chr_jp.obj")));
			ModelStore.add("Model4",
					new Model(OBJLoader.load("./Resources/chr_knight.obj")));
			ModelStore.add("Model5",
					new Model(OBJLoader.load("./Resources/chr_rain.obj")));
			ModelStore.add("Model6",
					new Model(OBJLoader.load("./Resources/chr_sword.obj")));
			TextureStore.put("Texture", new Texture(
					"./Resources/chr_knight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TEST durchschnittliche Zeit zum generieren von 16x16x16 chunks
		// (Random chunks)
		VoxelBuilder vb = new VoxelBuilder(16, 16, 16);
		VBO vbo;
		Model model;
		noise = new Noise(1, 5, 2);
		randomly(vb);
		vbo = vb.create();
		model = new Model(vbo);
		ModelStore.add("Random1", model);
		// END TEST

		Misc.log("+0.6f = " + (int) Math.floor(0.6f));
		Misc.log("+0.5f = " + (int) Math.floor(0.5f));
		Misc.log("+0.4f = " + (int) Math.floor(0.4f));
		Misc.log("-0.4f = " + (int) Math.floor(-0.4f));
		Misc.log("-0.5f = " + (int) Math.floor(-0.5f));
		Misc.log("-0.6f = " + (int) Math.floor(-0.6f));
		Mouse.setGrabbed(true);
	}

	Noise noise;

	private void randomly(VoxelBuilder vb) {
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				int h = (int) Math.floor((noise.get(x, y) + 1f) * 8f);
				for (int z = 0; z < 16; z++) {
					if (z == h) {
						// Gras
						Voxel voxel = new Voxel(83);
						vb.add(voxel, x, z, y);
					} else if (z < h) {
						// Dirt
						Voxel voxel = new Voxel(95);
						vb.add(voxel, x, z, y);
					} else {
						// Air
						vb.remove(x, z, y);
					}
				}
			}
		}
	}

	public void destroy() {
		ModelStore.rem("Model1");
		ModelStore.rem("Model2");
		ModelStore.rem("Model3");
		ModelStore.rem("Model4");
		ModelStore.rem("Model5");
		ModelStore.rem("Model6");
		TextureStore.rem("Texture");
	}

	public void update(float delta) {
		cam.update(delta * 1000f);
	}

	public void render3D() {
		cam.lookThrough();
		// TEST RENDER
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -4, -15);
		// Box
		GL11.glColor3f(1, 0, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glBegin(GL11.GL_LINES);
		// aussen
		GL11.glVertex3f(0, 0, 0);
		GL11.glVertex3f(0, 16, 0);
		GL11.glVertex3f(16, 0, 0);
		GL11.glVertex3f(16, 16, 0);
		GL11.glVertex3f(16, 0, 16);
		GL11.glVertex3f(16, 16, 16);
		GL11.glVertex3f(0, 0, 16);
		GL11.glVertex3f(0, 16, 16);
		// oben
		GL11.glVertex3f(0, 16, 0);
		GL11.glVertex3f(16, 16, 0);
		GL11.glVertex3f(16, 16, 0);
		GL11.glVertex3f(16, 16, 16);
		GL11.glVertex3f(16, 16, 16);
		GL11.glVertex3f(0, 16, 16);
		GL11.glVertex3f(0, 16, 16);
		GL11.glVertex3f(0, 16, 0);
		// unten
		GL11.glVertex3f(0, 0, 0);
		GL11.glVertex3f(16, 0, 0);
		GL11.glVertex3f(16, 0, 0);
		GL11.glVertex3f(16, 0, 16);
		GL11.glVertex3f(16, 0, 16);
		GL11.glVertex3f(0, 0, 16);
		GL11.glVertex3f(0, 0, 16);
		GL11.glVertex3f(0, 0, 0);
		GL11.glEnd();
		// Box
		GL11.glColor3f(1, 1, 1);
		TextureStore.get("Texture").bind();
		GL11.glScalef(1, 1, 1);
		ModelStore.get("Random1").draw();
		GL11.glPopMatrix();
		// END TEST
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -4, -15);
		GL11.glColor3f(0.2f, 0.6f, 0.3f);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(-3, 0, 3);
		GL11.glVertex3f(3, 0, 3);
		GL11.glVertex3f(3, 0, -3);
		GL11.glVertex3f(-3, 0, -3);
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);
		TextureStore.get("Texture").bind();
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1.5f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Model2").draw();
		GL11.glPopMatrix();
		GL11.glRotatef(60, 0, 1, 0);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1.5f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Model3").draw();
		GL11.glPopMatrix();
		GL11.glRotatef(60, 0, 1, 0);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1.5f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Model4").draw();
		GL11.glPopMatrix();
		GL11.glRotatef(60, 0, 1, 0);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1.5f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Model5").draw();
		GL11.glPopMatrix();
		GL11.glRotatef(60, 0, 1, 0);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1.5f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Model6").draw();
		GL11.glPopMatrix();
		GL11.glRotatef(60, 0, 1, 0);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1.5f);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		ModelStore.get("Model1").draw();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public void render2D() {

	}

}
