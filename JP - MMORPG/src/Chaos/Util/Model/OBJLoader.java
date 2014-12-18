package Chaos.Util.Model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class OBJLoader {
	public static VBO load(String file) throws IOException {
		return (load(new File(file)));
	}

	@SuppressWarnings("deprecation")
	public static VBO load(File file) throws IOException {
		VBO vbo = new VBO();
		vbo.normal = new float[0];
		vbo.texcoor = new float[0];
		vbo.vertex = new float[0];
		vbo.index = new int[0];
		Vector3f[] vertex = new Vector3f[0];
		Vector3f[] normal = new Vector3f[0];
		int index = 0;
		int faces = 0;
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		String line = dis.readLine();
		while (line != null) {
			String[] split = line.split(" ");
			if (split[0].equals("v")) {
				Vector3f lVertex = new Vector3f(Float.parseFloat(split[1]),
						Float.parseFloat(split[2]), Float.parseFloat(split[3]));
				Vector3f[] vertex2 = new Vector3f[vertex.length + 1];
				System.arraycopy(vertex, 0, vertex2, 0, vertex.length);
				vertex2[vertex.length] = lVertex;
				vertex = vertex2;
			} else if (split[0].equals("vn")) {
				Vector3f lNormal = new Vector3f(Float.parseFloat(split[1]),
						Float.parseFloat(split[2]), Float.parseFloat(split[3]));
				Vector3f[] normal2 = new Vector3f[normal.length + 1];
				System.arraycopy(normal, 0, normal2, 0, normal.length);
				normal2[normal.length] = lNormal;
				normal = normal2;
			} else if (split[0].equals("f")) {
				faces++;
				for (int i = 1; i < 4; i++) {
					String[] lIndex = split[i].split("/");
					// Add Vertices
					float[] vbovertex = vbo.vertex;
					vbo.vertex = new float[vbovertex.length + 3];
					System.arraycopy(vbovertex, 0, vbo.vertex, 0,
							vbovertex.length);
					vbo.vertex[vbo.vertex.length - 3] = vertex[Integer
							.parseInt(lIndex[0]) - 1].x;
					vbo.vertex[vbo.vertex.length - 2] = vertex[Integer
							.parseInt(lIndex[0]) - 1].y;
					vbo.vertex[vbo.vertex.length - 1] = vertex[Integer
							.parseInt(lIndex[0]) - 1].z;
					// Add Normals
					float[] vbonormal = vbo.normal;
					vbo.normal = new float[vbonormal.length + 3];
					System.arraycopy(vbonormal, 0, vbo.normal, 0,
							vbonormal.length);
					vbo.normal[vbo.normal.length - 3] = normal[Integer
							.parseInt(lIndex[2]) - 1].x;
					vbo.normal[vbo.normal.length - 2] = normal[Integer
							.parseInt(lIndex[2]) - 1].y;
					vbo.normal[vbo.normal.length - 1] = normal[Integer
							.parseInt(lIndex[2]) - 1].z;
					// Add Textures
					float[] vbotexcoor = vbo.texcoor;
					vbo.texcoor = new float[vbotexcoor.length + 2];
					System.arraycopy(vbotexcoor, 0, vbo.texcoor, 0,
							vbotexcoor.length);
					vbo.texcoor[vbo.texcoor.length - 2] = 0;
					vbo.texcoor[vbo.texcoor.length - 1] = 0;
					// Add Index
					int[] vboindex = vbo.index;
					vbo.index = new int[vboindex.length + 1];
					System.arraycopy(vboindex, 0, vbo.index, 0, vboindex.length);
					vbo.index[vbo.index.length - 1] = index;
					index++;
				}
			}

			line = dis.readLine();
		}
		dis.close();
		System.out.println("Faces: " + faces);
		return vbo;
	}
}
