package com.fastprac.sees.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fastprac.sees.model.Campus;
import com.fastprac.sees.model.Person;

public class People {
	private final Map<Person, Escape> people;
	
	public People() {
		people = new HashMap<Person, Escape>();
	}

	public Map<Person, Escape> getPeople() {
		return people;
	}

	public void add(int num, int startId, int size, int speed, int duration, Controller ctrl) {
		for (int n = 0; n < num; n++) {
			int i = 0;
			int j = 0;
			do {
				i = (int) (Campus.getClassroomStartI() + Math.random() * Campus.getClassroomNumX());
				j = (int) (Campus.getClassroomStartJ() + Math.random() * Campus.getClassroomNumY());
			} while (!Campus.cells[i][j].isOpen());

			int attackerId = startId + n;
			if (Campus.cells[i][j].isOpen()) {
				Person person = new Person(attackerId, "Attacker", size, Campus.cells[i][j]);
				Escape escape = new Escape(ctrl, person, speed, duration);
				people.put(person, escape);
			}
		}
	}

	public void draw() {
		for (Entry<Person, Escape> obj : people.entrySet()) {
			(obj.getKey()).draw();
		}
	}
}
