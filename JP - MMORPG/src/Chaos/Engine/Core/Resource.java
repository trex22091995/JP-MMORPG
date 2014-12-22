package Chaos.Engine.Core;

import org.lwjgl.opengl.GL11;

import Chaos.Util.Texture.Text;

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

	public Resource(Chaos chaos) {
		loader = new Loader(this);
		this.chaos = chaos;
	}

	// Texture Loading
	public void addTexture(String name, String path) {
		amount++;
	}

	// Model Loading
	public void addModel(String name, String path) {
		amount++;
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

			chaos.ingame = true;
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

		}
	}
}
