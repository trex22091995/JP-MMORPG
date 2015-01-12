package VoxelGame.Main;

import Chaos.Util.Model.VBO;

public class VoxelBuilder {
	private Voxel[][][] voxels;

	public VoxelBuilder(int x, int y, int z) {
		voxels = new Voxel[x][y][z];
	}

	public void add(Voxel voxel, int x, int y, int z) {
		voxels[x][y][z] = voxel;
	}

	public Voxel remove(int x, int y, int z) {
		Voxel voxel = voxels[x][y][z];
		voxels[x][y][z] = null;
		return voxel;
	}

	public VBO create() {
		// Create
		VBO vbo = new VBO();
		// Count
		int faces = 0;
		for (int x = 0; x < voxels.length; x++) {
			for (int y = 0; y < voxels[0].length; y++) {
				for (int z = 0; z < voxels[0][0].length; z++) {
					if (voxels[x][y][z] != null) {
						boolean top = false, bot = false, left = false, right = false, front = false, back = false;
						if (y == voxels[0].length - 1) {
							top = true;
						} else {
							if (voxels[x][y + 1][z] == null) {
								top = true;
							}
						}
						if (y == 0) {
							bot = true;
						} else {
							if (voxels[x][y - 1][z] == null) {
								bot = true;
							}
						}
						if (x == voxels.length - 1) {
							front = true;
						} else {
							if (voxels[x + 1][y][z] == null) {
								front = true;
							}
						}
						if (x == 0) {
							back = true;
						} else {
							if (voxels[x - 1][y][z] == null) {
								back = true;
							}
						}
						if (z == voxels[0][0].length - 1) {
							left = true;
						} else {
							if (voxels[x][y][z + 1] == null) {
								left = true;
							}
						}
						if (z == 0) {
							right = true;
						} else {
							if (voxels[x][y][z - 1] == null) {
								right = true;
							}
						}
						// Draw
						if (top) {
							faces++;
						}
						if (bot) {
							faces++;
						}
						if (left) {
							faces++;
						}
						if (right) {
							faces++;
						}
						if (front) {
							faces++;
						}
						if (back) {
							faces++;
						}
					}
				}
			}
		}
		// Size
		vbo.index = new int[faces * 6];
		vbo.vertex = new float[faces * 12];
		vbo.texcoor = new float[faces * 8];
		vbo.normal = new float[faces * 12];
		// Fill in
		faces = 0;
		for (int x = 0; x < voxels.length; x++) {
			for (int y = 0; y < voxels[0].length; y++) {
				for (int z = 0; z < voxels[0][0].length; z++) {
					if (voxels[x][y][z] != null) {
						boolean top = false, bot = false, left = false, right = false, front = false, back = false;
						if (y == voxels[0].length - 1) {
							top = true;
						} else {
							if (voxels[x][y + 1][z] == null) {
								top = true;
							}
						}
						if (y == 0) {
							bot = true;
						} else {
							if (voxels[x][y - 1][z] == null) {
								bot = true;
							}
						}
						if (x == voxels.length - 1) {
							front = true;
						} else {
							if (voxels[x + 1][y][z] == null) {
								front = true;
							}
						}
						if (x == 0) {
							back = true;
						} else {
							if (voxels[x - 1][y][z] == null) {
								back = true;
							}
						}
						if (z == voxels[0][0].length - 1) {
							left = true;
						} else {
							if (voxels[x][y][z + 1] == null) {
								left = true;
							}
						}
						if (z == 0) {
							right = true;
						} else {
							if (voxels[x][y][z - 1] == null) {
								right = true;
							}
						}
						// Draw
						if (top) {
							// Index
							vbo.index[faces * 6] = 2 + faces * 4;
							vbo.index[1 + faces * 6] = 1 + faces * 4;
							vbo.index[2 + faces * 6] = faces * 4;
							vbo.index[3 + faces * 6] = faces * 4;
							vbo.index[4 + faces * 6] = 3 + faces * 4;
							vbo.index[5 + faces * 6] = 2 + faces * 4;
							// Vertex
							vbo.vertex[faces * 12] = x;
							vbo.vertex[1 + faces * 12] = y + 1;
							vbo.vertex[2 + faces * 12] = z;
							vbo.vertex[3 + faces * 12] = x + 1;
							vbo.vertex[4 + faces * 12] = y + 1;
							vbo.vertex[5 + faces * 12] = z;
							vbo.vertex[6 + faces * 12] = x + 1;
							vbo.vertex[7 + faces * 12] = y + 1;
							vbo.vertex[8 + faces * 12] = z + 1;
							vbo.vertex[9 + faces * 12] = x;
							vbo.vertex[10 + faces * 12] = y + 1;
							vbo.vertex[11 + faces * 12] = z + 1;
							// Texcoor
							vbo.texcoor[faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[1 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[2 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[3 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[4 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[5 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							vbo.texcoor[6 + faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[7 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							// Normal
							vbo.normal[faces * 12] = 0;
							vbo.normal[1 + faces * 12] = 1;
							vbo.normal[2 + faces * 12] = 0;
							vbo.normal[3 + faces * 12] = 0;
							vbo.normal[4 + faces * 12] = 1;
							vbo.normal[5 + faces * 12] = 0;
							vbo.normal[6 + faces * 12] = 0;
							vbo.normal[7 + faces * 12] = 1;
							vbo.normal[8 + faces * 12] = 0;
							vbo.normal[9 + faces * 12] = 0;
							vbo.normal[10 + faces * 12] = 1;
							vbo.normal[11 + faces * 12] = 0;
							faces++;
						}
						if (bot) {
							// Index
							vbo.index[faces * 6] = faces * 4;
							vbo.index[1 + faces * 6] = 1 + faces * 4;
							vbo.index[2 + faces * 6] = 2 + faces * 4;
							vbo.index[3 + faces * 6] = faces * 4;
							vbo.index[4 + faces * 6] = 2 + faces * 4;
							vbo.index[5 + faces * 6] = 3 + faces * 4;
							// Vertex
							vbo.vertex[faces * 12] = x;
							vbo.vertex[1 + faces * 12] = y;
							vbo.vertex[2 + faces * 12] = z;
							vbo.vertex[3 + faces * 12] = x + 1;
							vbo.vertex[4 + faces * 12] = y;
							vbo.vertex[5 + faces * 12] = z;
							vbo.vertex[6 + faces * 12] = x + 1;
							vbo.vertex[7 + faces * 12] = y;
							vbo.vertex[8 + faces * 12] = z + 1;
							vbo.vertex[9 + faces * 12] = x;
							vbo.vertex[10 + faces * 12] = y;
							vbo.vertex[11 + faces * 12] = z + 1;
							// Texcoor
							vbo.texcoor[faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[1 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[2 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[3 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[4 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[5 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							vbo.texcoor[6 + faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[7 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							// Normal
							vbo.normal[faces * 12] = 0;
							vbo.normal[1 + faces * 12] = -1;
							vbo.normal[2 + faces * 12] = 0;
							vbo.normal[3 + faces * 12] = 0;
							vbo.normal[4 + faces * 12] = -1;
							vbo.normal[5 + faces * 12] = 0;
							vbo.normal[6 + faces * 12] = 0;
							vbo.normal[7 + faces * 12] = -1;
							vbo.normal[8 + faces * 12] = 0;
							vbo.normal[9 + faces * 12] = 0;
							vbo.normal[10 + faces * 12] = -1;
							vbo.normal[11 + faces * 12] = 0;
							faces++;
						}
						if (left) {
							// Index
							vbo.index[faces * 6] = 2 + faces * 4;
							vbo.index[1 + faces * 6] = 1 + faces * 4;
							vbo.index[2 + faces * 6] = faces * 4;
							vbo.index[3 + faces * 6] = 3 + faces * 4;
							vbo.index[4 + faces * 6] = 2 + faces * 4;
							vbo.index[5 + faces * 6] = faces * 4;
							// Vertex
							vbo.vertex[faces * 12] = x;
							vbo.vertex[1 + faces * 12] = y;
							vbo.vertex[2 + faces * 12] = z + 1;
							vbo.vertex[3 + faces * 12] = x;
							vbo.vertex[4 + faces * 12] = y + 1;
							vbo.vertex[5 + faces * 12] = z + 1;
							vbo.vertex[6 + faces * 12] = x + 1;
							vbo.vertex[7 + faces * 12] = y + 1;
							vbo.vertex[8 + faces * 12] = z + 1;
							vbo.vertex[9 + faces * 12] = x + 1;
							vbo.vertex[10 + faces * 12] = y;
							vbo.vertex[11 + faces * 12] = z + 1;
							// Texcoor
							vbo.texcoor[faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[1 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[2 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[3 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[4 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[5 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							vbo.texcoor[6 + faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[7 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							// Normal
							vbo.normal[faces * 12] = 0;
							vbo.normal[1 + faces * 12] = 0;
							vbo.normal[2 + faces * 12] = 1;
							vbo.normal[3 + faces * 12] = 0;
							vbo.normal[4 + faces * 12] = 0;
							vbo.normal[5 + faces * 12] = 1;
							vbo.normal[6 + faces * 12] = 0;
							vbo.normal[7 + faces * 12] = 0;
							vbo.normal[8 + faces * 12] = 1;
							vbo.normal[9 + faces * 12] = 0;
							vbo.normal[10 + faces * 12] = 0;
							vbo.normal[11 + faces * 12] = 1;
							faces++;
						}
						if (right) {
							// Index
							vbo.index[faces * 6] = faces * 4;
							vbo.index[1 + faces * 6] = 1 + faces * 4;
							vbo.index[2 + faces * 6] = 2 + faces * 4;
							vbo.index[3 + faces * 6] = faces * 4;
							vbo.index[4 + faces * 6] = 2 + faces * 4;
							vbo.index[5 + faces * 6] = 3 + faces * 4;
							// Vertex
							vbo.vertex[faces * 12] = x;
							vbo.vertex[1 + faces * 12] = y;
							vbo.vertex[2 + faces * 12] = z;
							vbo.vertex[3 + faces * 12] = x;
							vbo.vertex[4 + faces * 12] = y + 1;
							vbo.vertex[5 + faces * 12] = z;
							vbo.vertex[6 + faces * 12] = x + 1;
							vbo.vertex[7 + faces * 12] = y + 1;
							vbo.vertex[8 + faces * 12] = z;
							vbo.vertex[9 + faces * 12] = x + 1;
							vbo.vertex[10 + faces * 12] = y;
							vbo.vertex[11 + faces * 12] = z;
							// Texcoor
							vbo.texcoor[faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[1 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[2 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[3 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[4 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[5 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							vbo.texcoor[6 + faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[7 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							// Normal
							vbo.normal[faces * 12] = 0;
							vbo.normal[1 + faces * 12] = 0;
							vbo.normal[2 + faces * 12] = -1;
							vbo.normal[3 + faces * 12] = 0;
							vbo.normal[4 + faces * 12] = 0;
							vbo.normal[5 + faces * 12] = -1;
							vbo.normal[6 + faces * 12] = 0;
							vbo.normal[7 + faces * 12] = 0;
							vbo.normal[8 + faces * 12] = -1;
							vbo.normal[9 + faces * 12] = 0;
							vbo.normal[10 + faces * 12] = 0;
							vbo.normal[11 + faces * 12] = -1;
							faces++;
						}
						if (front) {
							// Index
							vbo.index[faces * 6] = faces * 4;
							vbo.index[1 + faces * 6] = 1 + faces * 4;
							vbo.index[2 + faces * 6] = 2 + faces * 4;
							vbo.index[3 + faces * 6] = faces * 4;
							vbo.index[4 + faces * 6] = 2 + faces * 4;
							vbo.index[5 + faces * 6] = 3 + faces * 4;
							// Vertex
							vbo.vertex[faces * 12] = x + 1;
							vbo.vertex[1 + faces * 12] = y;
							vbo.vertex[2 + faces * 12] = z;
							vbo.vertex[3 + faces * 12] = x + 1;
							vbo.vertex[4 + faces * 12] = y + 1;
							vbo.vertex[5 + faces * 12] = z;
							vbo.vertex[6 + faces * 12] = x + 1;
							vbo.vertex[7 + faces * 12] = y + 1;
							vbo.vertex[8 + faces * 12] = z + 1;
							vbo.vertex[9 + faces * 12] = x + 1;
							vbo.vertex[10 + faces * 12] = y;
							vbo.vertex[11 + faces * 12] = z + 1;
							// Texcoor
							vbo.texcoor[faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[1 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[2 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[3 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[4 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[5 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							vbo.texcoor[6 + faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[7 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							// Normal
							vbo.normal[faces * 12] = 1;
							vbo.normal[1 + faces * 12] = 0;
							vbo.normal[2 + faces * 12] = 0;
							vbo.normal[3 + faces * 12] = 1;
							vbo.normal[4 + faces * 12] = 0;
							vbo.normal[5 + faces * 12] = 0;
							vbo.normal[6 + faces * 12] = 1;
							vbo.normal[7 + faces * 12] = 0;
							vbo.normal[8 + faces * 12] = 0;
							vbo.normal[9 + faces * 12] = 1;
							vbo.normal[10 + faces * 12] = 0;
							vbo.normal[11 + faces * 12] = 0;
							faces++;
						}
						if (back) {
							// Index
							vbo.index[faces * 6] = 2 + faces * 4;
							vbo.index[1 + faces * 6] = 1 + faces * 4;
							vbo.index[2 + faces * 6] = faces * 4;
							vbo.index[3 + faces * 6] = 3 + faces * 4;
							vbo.index[4 + faces * 6] = 2 + faces * 4;
							vbo.index[5 + faces * 6] = faces * 4;
							// Vertex
							vbo.vertex[faces * 12] = x;
							vbo.vertex[1 + faces * 12] = y;
							vbo.vertex[2 + faces * 12] = z;
							vbo.vertex[3 + faces * 12] = x;
							vbo.vertex[4 + faces * 12] = y + 1;
							vbo.vertex[5 + faces * 12] = z;
							vbo.vertex[6 + faces * 12] = x;
							vbo.vertex[7 + faces * 12] = y + 1;
							vbo.vertex[8 + faces * 12] = z + 1;
							vbo.vertex[9 + faces * 12] = x;
							vbo.vertex[10 + faces * 12] = y;
							vbo.vertex[11 + faces * 12] = z + 1;
							// Texcoor
							vbo.texcoor[faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[1 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[2 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[3 + faces * 8] = (voxels[x][y][z].y + 0.0001f);
							vbo.texcoor[4 + faces * 8] = (voxels[x][y][z].x + 0.9999f) / 256f;
							vbo.texcoor[5 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							vbo.texcoor[6 + faces * 8] = (voxels[x][y][z].x + 0.0001f) / 256f;
							vbo.texcoor[7 + faces * 8] = (voxels[x][y][z].y + 0.9999f);
							// Normal
							vbo.normal[faces * 12] = -1;
							vbo.normal[1 + faces * 12] = 0;
							vbo.normal[2 + faces * 12] = 0;
							vbo.normal[3 + faces * 12] = -1;
							vbo.normal[4 + faces * 12] = 0;
							vbo.normal[5 + faces * 12] = 0;
							vbo.normal[6 + faces * 12] = -1;
							vbo.normal[7 + faces * 12] = 0;
							vbo.normal[8 + faces * 12] = 0;
							vbo.normal[9 + faces * 12] = -1;
							vbo.normal[10 + faces * 12] = 0;
							vbo.normal[11 + faces * 12] = 0;
							faces++;
						}
					}
				}
			}
		}
		// Return
		return vbo;
	}
}
