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
		float ns;
		Model model;
		int i;
		float all = 0;
		for (i = 0; i < 1; i++) {
			randomly(vb);
			vbo = vb.create();
			ns = System.nanoTime();
			model = new Model(vbo);
			ns = System.nanoTime() - ns;
			Misc.log("Model: " + i + " Millieseconds: " + (ns / 1000000f));
			ModelStore.add("Random" + i, model);
			all += ns / 1000000f;
		}
		Misc.log("Amount: " + i + " Millieseconds for each Model: "
				+ (all / (float) i));
		// END TEST
		Mouse.setGrabbed(true);
	}

	private void randomly(VoxelBuilder vb) {
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				for (int z = 0; z < 16; z++) {
					if (Math.random() >= 0.5f) {
						// put a block
						int num = (int) (Math.random() * 256f);
						Voxel voxel = new Voxel(num);
						vb.add(voxel, x, y, z);
					} else {
						// clear block
						vb.remove(x, y, z);
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
		GL11.glColor3f(1, 1, 1);
		TextureStore.get("Texture").bind();
		GL11.glScalef(1, 1, 1);
		for (int i = 0; i < 1; i++) {
			ModelStore.get("Random" + i).draw();
		}
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
