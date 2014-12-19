package Chaos.Engine.Core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import Chaos.Engine.Resource.Resource;
import Chaos.Util.Misc;

public class Chaos {
	final ChaosGame game;
	int width = 800, height = 600;
	DisplayMode dm = new DisplayMode(800, 600);
	String title = "Chaos Engine";
	boolean fullscreen = false, ingame = false;
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
				if (!ingame) {

					// TODO Preview Screen and Resource Loader
				} else {
					// actual game
					float delta = getDelta();
					game.update(delta);
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT
							| GL11.GL_DEPTH_BUFFER_BIT
							| GL11.GL_STENCIL_BUFFER_BIT);
					init3D();
					GL11.glPushMatrix();
					game.render3D();
					GL11.glPopMatrix();
					init2D();
					GL11.glPushMatrix();
					game.render2D();
					GL11.glPopMatrix();
				}
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

	// OpenGL Init Code
	private final void init2D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1920, 0, 1080, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	private final void init3D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(60, 1920f / 1080f, 0.1f, 100f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}
}
