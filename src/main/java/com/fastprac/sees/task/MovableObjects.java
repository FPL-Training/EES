package com.fastprac.sees.task;

import java.util.HashMap;
import java.util.Map;

public abstract class MovableObjects<K, V> {
	protected final Map<K, V> objects;

	public MovableObjects() {
		objects = new HashMap<K, V>();
	}

	public Map<K, V> getObjects() {
		return objects;
	}
}
