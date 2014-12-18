package Chaos.Util.Model;

import java.util.HashMap;

public class ModelStore {
	private HashMap<String, Model> models = new HashMap<String, Model>();

	public void add(String name, Model model) {
		if (models.containsKey(name)) {
			models.get(name).destroy();
			models.remove(name);
			models.put(name, model);
		} else {
			models.put(name, model);
		}
	}

	public Model get(String name) {
		return models.get(name);
	}
}
