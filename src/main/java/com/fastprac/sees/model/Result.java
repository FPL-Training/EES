package com.fastprac.sees.model;

import java.util.Date;

import com.fastprac.sees.model.status.PersonStatus;

public class Result {
	private int durCount;
	private int simulId;
	private int personId;
	private Cell loc;
	private PersonStatus status;
	
	private Date date;
	
	public Result() {
		super();
		this.durCount = 0;
		this.simulId = 0;
		this.personId = 0;
		this.loc = null;
		this.status = null;
		this.date = new Date();	}

	/**
	 * @param durCount
	 * @param simulId
	 * @param personId
	 * @param loc
	 * @param status
	 * @param date
	 */
	public Result(int simulId, int durCount, int personId, Cell loc, PersonStatus status) {
		super();
		this.durCount = durCount;
		this.simulId = simulId;
		this.personId = personId;
		this.loc = loc;
		this.status = status;
		this.date = new Date();
	}

	/**
	 * @return the durCount
	 */
	public int getDurCount() {
		return durCount;
	}

	/**
	 * @return the simulId
	 */
	public int getSimulId() {
		return simulId;
	}

	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @return the loc
	 */
	public Cell getCell() {
		return loc;
	}

	/**
	 * @return the status
	 */
	public PersonStatus getStatus() {
		return status;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

}
