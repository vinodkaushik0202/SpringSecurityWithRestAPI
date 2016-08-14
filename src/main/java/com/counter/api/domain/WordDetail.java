package com.counter.api.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class WordDetail.
 * @author vinod.sharma
 */
@JsonPropertyOrder(value = { "word", "count"})
public class WordDetail {

	/** The word. */
	private String word;
	
	/** The count. */
	private int count;
	
	/**
	 * Gets the word.
	 *
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Sets the word.
	 *
	 * @param word the new word
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
