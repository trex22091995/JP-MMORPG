package Chaos.Util.Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VBOHandle {
	public static void save(VBO vbo, String path) throws IOException {
		if (vbo == null) {
			return;
		}
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
		// Index
		int index = vbo.index.length;
		dos.writeInt(index);
		for (int i = 0; i < index; i++) {
			dos.writeInt(vbo.index[i]);
		}
		// Vertex
		index = vbo.vertex.length;
		dos.writeInt(index);
		for (int i = 0; i < index; i++) {
			dos.writeFloat(vbo.vertex[i]);
		}
		// Normal
		index = vbo.normal.length;
		dos.writeInt(index);
		for (int i = 0; i < index; i++) {
			dos.writeFloat(vbo.normal[i]);
		}
		// Texture
		index = vbo.texcoor.length;
		dos.writeInt(index);
		for (int i = 0; i < index; i++) {
			dos.writeFloat(vbo.texcoor[i]);
		}
		dos.close();

	}

	public static VBO load(String path) throws IOException {
		VBO vbo = new VBO();
		File file = new File(path);
		if (!file.exists()) {
			return vbo;
		}
		DataInputStream dos = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)));
		// Index
		vbo.index = new int[dos.readInt()];
		for (int i = 0; i < vbo.index.length; i++) {
			vbo.index[i] = dos.readInt();
		}
		// Vertex
		vbo.vertex = new float[dos.readInt()];
		for (int i = 0; i < vbo.vertex.length; i++) {
			vbo.vertex[i] = dos.readFloat();
		}
		// Normal
		vbo.normal = new float[dos.readInt()];
		for (int i = 0; i < vbo.normal.length; i++) {
			vbo.normal[i] = dos.readFloat();
		}
		// Texture
		vbo.texcoor = new float[dos.readInt()];
		for (int i = 0; i < vbo.texcoor.length; i++) {
			vbo.texcoor[i] = dos.readFloat();
		}
		dos.close();
		return vbo;
	}
}
