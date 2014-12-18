package Chaos.Util.Model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class Model {
	int length, vboiID, vbotID, vbonID, vboID, vbocID;

	public Model(VBO vbo) {
		// ID

		vboID = Handle.createVBO();
		vbotID = Handle.createVBO();
		vbonID = Handle.createVBO();
		vboiID = Handle.createVBO();
		vbocID = Handle.createVBO();

		// GENERATE
		length = vbo.index.length;
		// Buffers
		FloatBuffer b = BufferUtils.createFloatBuffer(vbo.vertex.length);
		b.put(vbo.vertex);
		b.flip();
		FloatBuffer bt = BufferUtils.createFloatBuffer(vbo.texcoor.length);
		bt.put(vbo.texcoor);
		bt.flip();
		FloatBuffer bn = BufferUtils.createFloatBuffer(vbo.normal.length);
		bn.put(vbo.normal);
		bn.flip();
		FloatBuffer bc = BufferUtils.createFloatBuffer(vbo.color.length);
		bc.put(vbo.color);
		bc.flip();
		IntBuffer bi = BufferUtils.createIntBuffer(vbo.index.length);
		bi.put(vbo.index);
		bi.flip();
		// Create
		Handle.bufferData(vboID, b);
		Handle.bufferData(vbotID, bt);
		Handle.bufferData(vbonID, bn);
		Handle.bufferData(vbocID, bc);
		Handle.bufferElementData(vboiID, bi);
	}

	public void draw() {
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboID);
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vbocID);
		GL11.glColorPointer(4, GL11.GL_FLOAT, 0, 0);
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vbonID);
		GL11.glNormalPointer(GL11.GL_FLOAT, 0, 0);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vbotID);
		GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0);
		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, vboiID);
		GL12.glDrawRangeElements(GL11.GL_TRIANGLES, 0, length, length,
				GL11.GL_UNSIGNED_INT, 0);
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	}

	public void destroy() {
		Handle.destroyVBO(vboID);
		Handle.destroyVBO(vboiID);
		Handle.destroyVBO(vbotID);
		Handle.destroyVBO(vbocID);
		Handle.destroyVBO(vbonID);
	}
}
