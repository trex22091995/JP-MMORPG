package Chaos.Util.Texture;

import java.util.HashMap;

public class TextureStore {
	public static HashMap<String, Texture> tex = new HashMap<String, Texture>();

	public static Texture get(String name) {
		if (tex.containsKey(name))
			return tex.get(name);
		else
			return null;
	}

	public static void put(String name, Texture tex) {
		if (!TextureStore.tex.containsKey(name))
			TextureStore.tex.put(name, tex);
	}

	public static Texture rem(String name) {
		Texture t = get(name);
		tex.remove(name);
		return t;
	}
}
