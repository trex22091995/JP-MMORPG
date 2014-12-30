/**
 * 
 */
package Chaos.Engine.Core;

public abstract class Scene extends ChaosGame {

	private Scene nextScene;	
	
	public Scene getNextScene(){
		return nextScene;
	};
	
	public void setNextScene(Scene nextScene) {
		this.nextScene = nextScene;
	}
	
	public abstract void SceneCreate();
	
//	public abstract void SceneUpdate(float deltaTime);
//	
//	public abstract void SceneRender2D();
//	
//	public abstract void SceneRender3D();
	
	public abstract void SceneDestroy();
	
	
}
