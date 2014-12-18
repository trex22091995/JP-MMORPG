package Chaos.Engine.Core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Chaos.Engine.Resource.Resource;
import Chaos.Util.Misc;

public class Chaos {
	final ChaosGame game;
	int width = 800, height = 600;
	DisplayMode dm = new DisplayMode(800, 600);
	String title = "Chaos Engine";
	boolean fullscreen = false;
	long time;
	Resource res;

	// Übergabe des Haupt-Spiel Objects
	public Chaos(ChaosGame game) {
		this.game = game;
	}

	// Setzen der Breite und Höhe des Fensters
	public void setScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
		try {
			dm = null;
			DisplayMode[] d = Display.getAvailableDisplayModes();
			for (DisplayMode mode : d) {
				if (mode.getWidth() == width && mode.getHeight() == height) {
					dm = mode;
				}
			}
			if (dm == null) {
				dm = new DisplayMode(width, height);
			}
		} catch (LWJGLException e) {
			Misc.err(e);
		}
	}

	// Setzen des Titels
	public void setScreenTitle(String title) {
		this.title = title;
	}

	// Fullscreen Option
	public void setScreenFull(boolean full) {
		this.fullscreen = full;
	}

	// Starten der Engine mit dem game Object
	public void start() {
		try {
			// Create Display
			Display.setTitle(title);
			Display.setVSyncEnabled(true);
			Display.setDisplayMode(dm);
			Display.setFullscreen(fullscreen);
			Display.create();
			Mouse.create();
			Keyboard.create();
			// Loop
			res = new Resource();
			game.loadResources(res);
			getDelta();
			while (!Display.isCloseRequested()) {

				Display.update();
				Display.sync(60);
			}
			// Destroy Display
			stop();
		} catch (LWJGLException e) {
			Misc.err(e);
		}
	}

	// Stoppen der Engine
	public void stop() {
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
		System.exit(0);
	}

	// Delta berechnung
	private float getDelta() {
		long dif = System.nanoTime() - time;
		time = System.nanoTime();
		return (float) dif / 1000000000f;
	}
}
