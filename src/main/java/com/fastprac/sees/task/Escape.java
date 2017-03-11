/**
 * 
 */
package com.fastprac.sees.task;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.fastprac.sees.model.Person;
import com.fastprac.sees.model.status.MomentStatus;

/**
 * @author Admin
 *
 */
public class Escape implements Callable<List<MomentStatus>> {

	private Person person;
	private int speed;	// (1-1000);
	private int duration;
	
	/**
	 * 
	 */
	public Escape() {
		this.person = new Person();
		speed = 10;
		duration = 100;
	}

	
	/**
	 * 
	 * @param person
	 * @param speed
	 * @param duration
	 */
	public Escape(Person person, int speed, int duration) {
		super();
		this.person = person;
		this.speed = speed;
		this.duration = duration;
	}


	@Override
	public List<MomentStatus> call() throws Exception {
		int pauseTime = 1000/speed;
		
		List<MomentStatus> mStatusList = new ArrayList<MomentStatus>();
		Long seq = 0L;
		while(person.isMovable() && (duration > 0)) {
			seq++;
			MomentStatus mStatus = person.move();
			mStatusList.add(mStatus);
			Thread.sleep(pauseTime);
			//System.out.println("Person: "+person.getId() + ", status: "+person.getStatus().toString()+", Duration left = "+duration);
			duration--;
		}

		return mStatusList;
	}

}
