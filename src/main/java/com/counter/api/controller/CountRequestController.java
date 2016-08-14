package com.counter.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.api.domain.RequestWrapper;
import com.counter.api.domain.ResponseWrapper;
import com.counter.api.domain.WordDetail;
import com.counter.api.service.ITokenReaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * The Class CountRequestController.
 */
@RestController
public class CountRequestController {
	
	/** The readerservice. */
	@Autowired
	private ITokenReaderService readerservice;
	
	/**
	 * Gets the word occurence.
	 *
	 * @param requestWrapper the request wrapper
	 * @return the word occurence
	 */
	@BadgerFish
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/search/wordcount", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<ResponseWrapper> getWordOccurence(@RequestBody RequestWrapper requestWrapper)
	{
		Map<String, Integer> map = readerservice.getWordCount(requestWrapper.getSearchText());
		ResponseWrapper rwrapper = new ResponseWrapper();
		ArrayList<WordDetail> counts = new ArrayList<WordDetail>();
		for(String key : map.keySet()){
			WordDetail wd = new WordDetail();
			wd.setWord(key);
			wd.setCount(map.get(key));
			counts.add(wd);
		}
		rwrapper.setCounts(counts);
		return new ResponseEntity<ResponseWrapper>(rwrapper,HttpStatus.OK);
	}

	/**
	 * Gets the top words in paragraps.
	 *
	 * @param topLimit the top limit
	 * @return the top words in paragraps
	 * @throws JsonProcessingException 
	 */
	@Produces("text/csv")
	@RequestMapping(value = "/search/top/{topLimit}", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<String> getTopWordsInParagraps(@PathVariable("topLimit") int topLimit) throws JsonProcessingException
	{
		List<WordDetail> list = readerservice.getTopWordCount(topLimit);
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(WordDetail.class).withHeader();
		schema = schema.withQuoteChar('\"');
		return new ResponseEntity<String>(mapper.writer(schema).writeValueAsString(list), HttpStatus.OK);
	}
}
