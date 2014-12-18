package Chaos.Util.Model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GLContext;

public class Handle {
	public static IntBuffer createVBOs(int amount) {
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {
			IntBuffer buffer = BufferUtils.createIntBuffer(amount);
			ARBVertexBufferObject.glGenBuffersARB(buffer);
			return buffer;
		}

		return null;
	}

	public static void destroyVBO(int i) {
		IntBuffer buffer = BufferUtils.createIntBuffer(1);
		buffer.put(i);
		ARBVertexBufferObject.glDeleteBuffersARB(buffer);
	}

	public static int createVBO() {
		return createVBOs(1).get(0);
	}

	public static void bufferData(int id, FloatBuffer buffer) {
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {
			ARBVertexBufferObject.glBindBufferARB(
					ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, id);
			ARBVertexBufferObject.glBufferDataARB(
					ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, buffer,
					ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		}
	}

	public static void bufferElementData(int id, IntBuffer buffer) {
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {

			ARBVertexBufferObject.glBindBufferARB(
					ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, id);
			ARBVertexBufferObject.glBufferDataARB(
					ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, buffer,
					ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		}
	}
}
