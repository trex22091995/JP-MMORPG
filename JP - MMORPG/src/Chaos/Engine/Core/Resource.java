package Chaos.Engine.Core;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import Chaos.Util.Misc;
import Chaos.Util.Model.Model;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Model.OBJLoader;
import Chaos.Util.Model.VBO;
import Chaos.Util.Model.VBOHandle;
import Chaos.Util.Texture.Text;
import Chaos.Util.Texture.Texture;
import Chaos.Util.Texture.TextureStore;

public class Resource {
	/*
	 * Die Dateien werden hier NICHT geladen! Es wird nur registriert, was
	 * geladen werden soll und wie viele es sind (Abschätzungen für den
	 * Ladebalken) Geladen wird das ganze in einem Thread der parallel zum
	 * Update-Render Thread läuft, welcher anzeigt wie weit das Laden schon ist.
	 */
	int amount, current;
	Loader loader;
	Chaos chaos;
	// For actual Loading
	HashMap<String, VBO> vboload = new HashMap<String, VBO>();
	HashMap<String, String> texload = new HashMap<String, String>();
	int texcount = 0;

	// For Resource Loader
	HashMap<String, String> vbores = new HashMap<String, String>();

	public Resource(Chaos chaos) {
		loader = new Loader(this);
		this.chaos = chaos;
	}

	// Texture Loading
	public void addTexture(String name, String path) {
		amount++;
		texload.put(name, path);
	}

	// Model Loading
	public void addModel(String name, String path) {
		amount++;
		this.vbores.put(name, path);
	}

	// Shader Loading
	public void addShader(String name, String path) {
		amount++;
	}

	// Visual and Loading

	void render() {
		// Loading Bar
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPushMatrix();
		GL11.glTranslatef(chaos.width / 2f, chaos.height / 2f, 0);
		Chaos.text.draw("Loading ... ", 1, Text.ALIGN.CENTER);
		GL11.glTranslatef(0, -16, 0);
		Chaos.text.draw(current + " / " + amount, 1, Text.ALIGN.CENTER);
		GL11.glPopMatrix();
		if (current == amount) {
			// final step: put vbo as models into graphics card
			for (String name : vboload.keySet()) {
				Model model = new Model(vboload.get(name));
				ModelStore.add(name, model);
			}
			chaos.ingame = true;
		}
		// Loading Textures
		if (texcount < texload.keySet().toArray().length) {
			try {
				Texture tex = new Texture(texload.get(texload.keySet()
						.toArray()[texcount]));
				TextureStore.put((String) texload.keySet().toArray()[texcount],
						tex);
			} catch (IOException e) {
				Misc.err(e);
			}
			texcount++;
			current++;
		}
	}

	void start() {
		// Start Loading Thread
		loader.start();
	}

	private class Loader extends Thread {
		public Resource res;

		public Loader(Resource res) {
			this.res = res;
		}

		public void run() {
			// TODO (For now only load VBO/OBJ files into vbo objects)
			for (String name : vbores.keySet()) {
				String path = vbores.get(name);
				if (path.endsWith(".vbo")) {
					// VBO File
					try {
						VBO vbo = VBOHandle.load(path);
						res.vboload.put(name, vbo);
					} catch (IOException e) {
						Misc.err(e);
					}
				} else if (path.endsWith(".obj")) {
					// OBJ File
					try {
						VBO vbo = OBJLoader.load(path);
						res.vboload.put(name, vbo);
					} catch (IOException e) {
						Misc.err(e);
					}
				}
				current++;
			}
		}
	}
}
