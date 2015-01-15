package VoxelMOBA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

public class Shader {
	public int shaderProgram = 0, vertShader, fragShader;

	public Shader() {

	}

	public Shader(String path) {
		try {
			vertShader = createShader(path + ".vert",
					ARBVertexShader.GL_VERTEX_SHADER_ARB);
			fragShader = createShader(path + ".frag",
					ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			if (vertShader == 0 || fragShader == 0) {
				System.out.println("Vert or Frag Shader not loaded!");
				return;
			}
		}
		shaderProgram = ARBShaderObjects.glCreateProgramObjectARB();
		if (shaderProgram == 0) {
			System.out.println("Program not loaded!");
			return;
		}
		ARBShaderObjects.glAttachObjectARB(shaderProgram, vertShader);
		ARBShaderObjects.glAttachObjectARB(shaderProgram, fragShader);
		ARBShaderObjects.glLinkProgramARB(shaderProgram);

		if (ARBShaderObjects.glGetObjectParameteriARB(shaderProgram,
				ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
			System.out.println(getLogInfo(shaderProgram));
			return;
		}

		ARBShaderObjects.glValidateProgramARB(shaderProgram);
		if (ARBShaderObjects.glGetObjectParameteriARB(shaderProgram,
				ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
			System.out.println(getLogInfo(shaderProgram));
			return;
		}
	}

	public void bind() {
		ARBShaderObjects.glUseProgramObjectARB(shaderProgram);
	}

	public void unbind() {
		ARBShaderObjects.glUseProgramObjectARB(0);
	}

	public int createShader(String path, int type) throws Exception {
		int shader = 0;
		try {
			shader = ARBShaderObjects.glCreateShaderObjectARB(type);
			if (shader == 0) {
				return 0;
			}
			ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(path));
			ARBShaderObjects.glCompileShaderARB(shader);
			if (ARBShaderObjects.glGetObjectParameteriARB(shader,
					ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
				throw new RuntimeException("Error creating shader: "
						+ getLogInfo(shader));
			}
			return shader;
		} catch (Exception e) {
			ARBShaderObjects.glDeleteObjectARB(shader);
			throw e;
		}
	}

	public String getLogInfo(int obj) {
		return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects
				.glGetObjectParameteriARB(obj,
						ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
	}

	public String readFileAsString(String path) throws Exception {
		StringBuilder source = new StringBuilder();
		FileInputStream in = new FileInputStream(path);
		Exception e = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

			Exception inEx = null;
			try {
				String line;
				while ((line = reader.readLine()) != null)
					source.append(line).append('\n');
			} catch (Exception ex) {
				e = ex;
			} finally {
				try {
					reader.close();
				} catch (Exception ex) {
					if (inEx == null)
						inEx = ex;
					else
						ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			e = ex;
		} finally {
			try {
				in.close();
			} catch (Exception ex) {
				if (e == null)
					e = ex;
				else
					ex.printStackTrace();
			}
			if (e != null)
				throw e;
		}
		return source.toString();

	}
}
