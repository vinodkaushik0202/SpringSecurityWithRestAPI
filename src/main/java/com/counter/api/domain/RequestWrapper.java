package com.counter.api.domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement; 

/**
 * The Class RequestWrapper.
 * @author vinod.sharma
 */
@XmlRootElement 
public class RequestWrapper {

	 /** The search text. */
 	ArrayList<String> searchText;

	/**
	 * Gets the search text.
	 *
	 * @return the search text
	 */
	public ArrayList<String> getSearchText() {
		return searchText;
	}

	/**
	 * Sets the search text.
	 *
	 * @param searchText the new search text
	 */
	public void setSearchText(ArrayList<String> searchText) {
		this.searchText = searchText;
	}
}