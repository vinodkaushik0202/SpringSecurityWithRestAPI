package com.counter.api.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * The Class TokenReaderUtils.
 * @author vinod.sharma
 */
public class TokenReaderUtils {

	/**
	 * Read file contents as string.
	 *
	 * @return the string
	 */
	public static String readFileContentsAsString() {
		Resource resource = new ClassPathResource("/inteliment.txt");
		String arffPathRaw = null;
		BufferedReader br = null;
		try {
			arffPathRaw = resource.getURI().toString();
			String arffPath = arffPathRaw.replace("file:/", "");
			FileInputStream fs = null;
			try {
				fs = new FileInputStream(arffPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			DataInputStream is = new DataInputStream(fs);
			br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = "";

			while (br.ready() && (line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Gets the sorted word list in dec order.
	 *
	 * @param sringValue the sring value
	 * @param topLimit the top limit
	 * @return the sorted word list in dec order
	 */
	public static List<Entry<String, Integer>> getSortedWordListInDecOrder(final String sringValue, final int topLimit) {
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		String[] words = sringValue.replaceAll("[^a-zA-Z'\\s]","").toLowerCase().split("\\s+");
		for (String word : words) {
			if (wordMap.containsKey(word)) {
				wordMap.put(word, (wordMap.get(word) + 1));
			} else {
				wordMap.put(word, 1);
			}
		}
		Set<Entry<String, Integer>> entries = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(entries);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		if(topLimit < list.size()){
			return list.subList(0, topLimit);
		}
		return list;
	}
}
