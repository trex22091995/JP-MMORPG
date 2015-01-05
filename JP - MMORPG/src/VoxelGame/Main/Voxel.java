package VoxelGame.Main;

public class Voxel {
	public float x; // x coordinate
	public float y = 0.5f;

	public Voxel(float x) { // 0-255
		this.x = (x + 0.5f) / 256f;
	}
}
