package VoxelMOBA;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Scene;
import Chaos.Util.Model.Model;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Model.VBOHandle;
import Chaos.Util.Texture.Texture;
import Chaos.Util.Texture.TextureStore;

public class GameScene extends Scene {
	Camera cam = new Camera(0, 0, 0);
	Shader shader;

	public void create() {
		try {
			ModelStore.add("Map",
					new Model(VBOHandle.load("./Resources/Char_1.vbo")));
			TextureStore.put("Texture", new Texture("./Resources/palette.png"));
			shader = new Shader("./Resources/ambient");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mouse.setGrabbed(true);
	}

	public void destroy() {
		TextureStore.rem("Texture");
	}

	public void update(float delta) {
		cam.update(delta * 1000f);
	}

	public void render3D() {
		cam.lookThrough();
		// Map
		GL11.glPushMatrix();
		shader.bind();
		GL11.glColor3f(1, 1, 1);
		TextureStore.get("Texture").bind();
		ModelStore.get("Map").draw();
		shader.unbind();
		GL11.glPopMatrix();

	}

	public void render2D() {

	}

}
