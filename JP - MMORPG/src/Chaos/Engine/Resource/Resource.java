package Chaos.Engine.Resource;

public class Resource {
	/*
	 * Die Dateien werden hier NICHT geladen! Es wird nur registriert, was
	 * geladen werden soll und wie viele es sind (Abschätzungen für den
	 * Ladebalken) Geladen wird das ganze in einem Thread der parallel zum
	 * Update-Render Thread läuft, welcher anzeigt wie weit das Laden schon ist.
	 */

	// Texture Loading
	public void addTexture(String name, String path) {

	}

	// Model Loading
	public void addModel(String name, String path) {

	}

	// Shader Loading
	public void addShader(String name, String path) {

	}
}
