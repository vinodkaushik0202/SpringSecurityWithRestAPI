package com.counter.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.counter.api.domain.WordDetail;

/**
 * The Interface ITokenReaderService.
 * @author vinod.sharma
 */
public interface ITokenReaderService {
	
	/**
	 * Load file contents.
	 */
	public abstract void loadFileContents();
	
	/**
	 * Gets the word count.
	 *
	 * @param wordList the word list
	 * @return the word count
	 */
	public Map<String,Integer> getWordCount(final ArrayList<String> wordList);
	
	/**
	 * Gets the top word count.
	 *
	 * @param top the top
	 * @return the top word count
	 */
	public List<WordDetail> getTopWordCount(final int top);

}
