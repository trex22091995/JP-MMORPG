package Chaos.Test;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Scene;
import Chaos.Util.Model.Model;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Model.OBJLoader;
import Chaos.Util.Texture.Texture;
import Chaos.Util.Texture.TextureStore;

public class SceneModel extends Scene {

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

	public float rot;

	public void update(float delta) {
		rot += delta * 30f;
		if (rot >= 360)
			rot -= 360;
	}

	public void render3D() {
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -4, -15);
		GL11.glRotatef(rot, 0, 1, 0);
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
