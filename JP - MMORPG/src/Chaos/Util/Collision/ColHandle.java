package Chaos.Util.Collision;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ColHandle {
	public static void save(ColMap cm, String path) throws IOException {
		if (cm == null) {
			return;
		}
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
		// Complicated File writing D:
		dos.writeFloat(cm.x);
		dos.writeFloat(cm.y);
		dos.writeFloat(cm.z);
		dos.writeFloat(cm.box.getX());
		dos.writeFloat(cm.box.getY());
		dos.writeFloat(cm.box.getZ());
		dos.writeFloat(cm.box.getDx());
		dos.writeFloat(cm.box.getDy());
		dos.writeFloat(cm.box.getDz());
		// chunk number
		dos.writeInt(cm.chunks.length);
		for (int x = 0; x < cm.chunks.length; x++) {
			// each Chunk
			dos.writeFloat(cm.chunks[x].box.getX());
			dos.writeFloat(cm.chunks[x].box.getY());
			dos.writeFloat(cm.chunks[x].box.getZ());
			// block number
			dos.writeInt(cm.chunks[x].blocks.length);
			for (int y = 0; y < cm.chunks[x].blocks.length; y++) {
				dos.writeFloat(cm.chunks[x].blocks[y].box.getX());
				dos.writeFloat(cm.chunks[x].blocks[y].box.getY());
				dos.writeFloat(cm.chunks[x].blocks[y].box.getZ());
			}
		}
		// END!! :D
		dos.close();
	}

	public static ColMap load(String path) throws IOException {
		ColMap cm = new ColMap();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		DataInputStream dos = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)));
		cm.x = dos.readFloat();
		cm.y = dos.readFloat();
		cm.z = dos.readFloat();
		float x, y, z, dx, dy, dz;
		x = dos.readFloat();
		y = dos.readFloat();
		z = dos.readFloat();
		dx = dos.readFloat();
		dy = dos.readFloat();
		dz = dos.readFloat();
		cm.box = new Box(x, y, z, dx, dy, dz);
		int chunks = dos.readInt();
		cm.chunks = new ColChunk[chunks];
		for (int i = 0; i < chunks; i++) {
			cm.chunks[i] = new ColChunk();
			x = dos.readFloat();
			y = dos.readFloat();
			z = dos.readFloat();
			cm.chunks[i].box = new Box(x, y, z, 8, 8, 8);
			int blocks = dos.readInt();
			cm.chunks[i].blocks = new ColBlock[blocks];
			for (int i2 = 0; i2 < blocks; i2++) {
				cm.chunks[i].blocks[i2] = new ColBlock();
				x = dos.readFloat();
				y = dos.readFloat();
				z = dos.readFloat();
				cm.chunks[i].blocks[i2].box = new Box(x, y, z, 0.5f, 0.5f, 0.5f);
			}
		}
		dos.close();
		return cm;
	}
}
