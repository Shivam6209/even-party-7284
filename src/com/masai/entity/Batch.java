package com.masai.entity;

import java.time.LocalDate;
import java.util.List;

public class Batch {

	private Long batchId;
	private String batchName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int maxCapacity;

	public Batch(Long batchId, String batchName, LocalDate startDate, LocalDate endDate, int maxCapacity) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxCapacity = maxCapacity;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	
}
