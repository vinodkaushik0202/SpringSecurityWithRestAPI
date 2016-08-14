package com.counter.api.domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ResponseWrapper.
 * @author vinod.sharma
 */
@XmlRootElement 
public class ResponseWrapper {
	
	/** The counts. */
	ArrayList<WordDetail> counts;

	/**
	 * Gets the counts.
	 *
	 * @return the counts
	 */
	public ArrayList<WordDetail> getCounts() {
		return counts;
	}
	
	/**
	 * Sets the counts.
	 *
	 * @param counts the new counts
	 */
	public void setCounts(ArrayList<WordDetail> counts) {
		this.counts = counts;
	}
}
