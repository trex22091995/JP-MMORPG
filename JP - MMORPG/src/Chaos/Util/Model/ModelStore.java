package Chaos.Util.Model;

import java.util.HashMap;

public class ModelStore {
	private static HashMap<String, Model> models = new HashMap<String, Model>();

	public static void add(String name, Model model) {
		if (!models.containsKey(name))
			models.put(name, model);
	}

	public static Model get(String name) {
		if (models.containsKey(name))
			return models.get(name);
		else
			return null;
	}

	public static Model rem(String name) {
		Model model = get(name);
		models.remove(name);
		return model;
	}
}
