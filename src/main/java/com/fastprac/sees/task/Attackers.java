package com.fastprac.sees.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fastprac.sees.model.Attacker;
import com.fastprac.sees.model.Campus;
import com.fastprac.sees.task.Attacking;
import com.fastprac.sees.task.Controller;

public class Attackers {
	protected final Map<Attacker, Attacking> attackers;

	public Attackers() {
		attackers = new HashMap<Attacker, Attacking>();
	}

	public Map<Attacker, Attacking> getAttackers() {
		return attackers;
	}

	public void add(int numOfAttackers, int startId, int size, int speed, int duration, Controller ctrl) {
		for (int n = 0; n < numOfAttackers; n++) {
			int i = 0;
			int j = 0;
			do {
				i = (int) (Campus.getClassroomStartI() + Math.random() * Campus.getClassroomNumX());
				j = (int) (Campus.getClassroomStartJ() + Math.random() * Campus.getClassroomNumY());
			} while (!Campus.cells[i][j].isOpen());

			int attackerId = startId + n;
			if (Campus.cells[i][j].isOpen()) {
				Attacker attacker = new Attacker(attackerId, "Attacker", size, Campus.cells[i][j]);
				Attacking attacking = new Attacking(ctrl, attacker, speed, duration);
				attackers.put(attacker, attacking);
			}
		}		
	}
	
	public void draw() {
		for (Entry<Attacker, Attacking> obj : attackers.entrySet()) {
			(obj.getKey()).draw();
		}
	}
}
