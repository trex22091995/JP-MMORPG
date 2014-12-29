package Chaos.Test;

import org.lwjgl.opengl.GL11;

import Chaos.Engine.Core.Chaos;
import Chaos.Engine.Core.ChaosGame;
import Chaos.Engine.Core.Resource;
import Chaos.Util.Model.ModelStore;
import Chaos.Util.Texture.Text;

public class Test {
	// Start
	public static void main(String[] args) {
		new Test();
	}
	
	
	// Engine
	Chaos chaos;
	public Test() {
		// Init Code for the Engine
		chaos = new Chaos(new TestScene()); //Pass the first scene to the Engine
		chaos.setScreenSize(800, 600);
		chaos.setScreenFull(false);
		chaos.setScreenTitle("Test-Chaos");
		chaos.start();
	}

	
}
