package com.counter.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.counter.api.domain.WordDetail;
import com.counter.api.utils.TokenReaderUtils;

/**
 * The Class TokenReaderService.
 * @author vinod.sharma
 */
@Service("ITokenReaderService")
public class TokenReaderService implements ITokenReaderService {

	/** The prg string. */
	private String prgString = null;

	/**
	 * Gets the prg string.
	 *
	 * @return the prg string
	 */
	public String getPrgString() {
		return prgString;
	}

	/**
	 * Sets the prg string.
	 *
	 * @param prgString the new prg string
	 */
	public void setPrgString(String prgString) {
		this.prgString = prgString;
	}

	/* (non-Javadoc)
	 * @see com.counter.api.service.ITokenReaderService#loadFileContents()
	 */
	@Override
	@PostConstruct
	public void loadFileContents() {
		String strRead = TokenReaderUtils.readFileContentsAsString();
		setPrgString(strRead);
	}

	/* (non-Javadoc)
	 * @see com.counter.api.service.ITokenReaderService#getWordCount(java.util.ArrayList)
	 */
	@Override
	public Map<String, Integer> getWordCount(ArrayList<String> wordList) {
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		for (String word : wordList) {
			try {
				wordCountMap.put(word, countAWordlnString(getPrgString().replaceAll("[^a-zA-Z'\\s]","").toLowerCase().split("\\s+"), word.toLowerCase()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return wordCountMap;
	}

	/**
	 * Count a wordln string.
	 *
	 * @param str the str
	 * @param word the word
	 * @return the int
	 */
	private int countAWordlnString(String[] str, String word) {
		if (word.isEmpty()) {
			return 0;
		}
		Map<String, Integer> hmap = new HashMap<String, Integer>();
		for (String tempStr : str) {
			if(tempStr.equalsIgnoreCase(word)){
				if (hmap.containsKey(word)) {
					int i = hmap.get(word);
					i += 1;
					hmap.put(word, i);
				} else
					hmap.put(word, 1);
			}else{
				continue;
			}
			
		}
		return hmap.get(word) != null ? hmap.get(word) : 0;
	}

	/* (non-Javadoc)
	 * @see com.counter.api.service.ITokenReaderService#getTopWordCount(int)
	 */
	@Override
	public List<WordDetail> getTopWordCount(final int topLimit) {
		List<Entry<String, Integer>> listWords = TokenReaderUtils.getSortedWordListInDecOrder(getPrgString(), topLimit);
		List<WordDetail> wordDetailsList = new ArrayList<WordDetail>();
		for(Entry<String, Integer> e : listWords){
			WordDetail w = new WordDetail();
			w.setWord(e.getKey());
			w.setCount(e.getValue());
			wordDetailsList.add(w);
		}
		return wordDetailsList; 
	}
}
