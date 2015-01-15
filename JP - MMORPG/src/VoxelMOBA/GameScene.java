package VoxelMOBA;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

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
	// Camera cam = new Camera(0, 0, 0);
	Shader shader;
	ColMap cm;
	ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
	EntityPlayer player;

	public void create() {
		try {
			ModelStore.add("Map",
					new Model(VBOHandle.load("./src/VoxelMOBA/Map.vbo")));
			ModelStore.add("Char",
					new Model(VBOHandle.load("./src/VoxelMOBA/Char_1.vbo")));
			TextureStore.put("Texture", new Texture(
					"./src/VoxelMOBA/palette.png"));
			shader = new Shader("./src/VoxelMOBA/ambient");
			cm = ColHandle.load("./src/VoxelMOBA/Map.col");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mouse.setGrabbed(true);
		// Add Entities
		entities.put(1, player = new EntityPlayerChar_1(64, 16, 64, 0.4f, 0.7f,
				0.4f, cm));
	}

	public void destroy() {
		TextureStore.rem("Texture");
	}

	public void update(float delta) {
		// cam.update(delta * 1000f);
		player.poll(delta);
		for (Entity entity : entities.values()) {
			entity.update(delta);
		}
	}

	public void render3D() {
		// cam.lookThrough();
		player.cam();
		// Map
		shader.bind();
		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		TextureStore.get("Texture").bind();
		ModelStore.get("Map").draw();
		GL11.glPopMatrix();
		// Character
		for (Entity entity : entities.values()) {
			entity.render();
		}
		shader.unbind();
	}

	public void render2D() {

	}

}
