package VoxelMOBA;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Scene;
import Chaos.Util.Collision.ColHandle;
import Chaos.Util.Collision.ColMap;
import Chaos.Util.Model.Model;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Model.VBOHandle;
import Chaos.Util.Texture.Texture;
import Chaos.Util.Texture.TextureStore;

public class GameScene extends Scene {
	Camera cam = new Camera(0, 0, 0);
	Shader shader;
	ColMap cm;

	public void create() {
		try {
			ModelStore.add("Map",
					new Model(VBOHandle.load("./src/VoxelMOBA/Map.vbo")));
			TextureStore.put("Texture", new Texture(
					"./src/VoxelMOBA/palette.png"));
			shader = new Shader("./src/VoxelMOBA/ambient");
			cm = ColHandle.load("./src/VoxelMOBA/Map.col");
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
		cm.drawDebug();
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
