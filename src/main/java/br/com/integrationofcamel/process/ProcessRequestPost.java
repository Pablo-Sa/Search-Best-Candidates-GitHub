package br.com.integrationofcamel.process;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.integrationofcamel.dto.resquestpost.DtoRequestPost;
import br.com.integrationofcamel.util.ProcessExchange;

public class ProcessRequestPost implements Processor {

	private ArrayList<String> candidates = new ArrayList<>();
	private ArrayList<String> teste = new ArrayList<>();

	@Override
	public void process(Exchange exchange) throws Exception {
		DtoRequestPost dto = exchange.getIn().getBody(DtoRequestPost.class);
		candidates = ProcessExchange.languages(dto);
		
		for (String string : candidates) {
			System.out.println(string);
		}

		teste = ProcessExchange.frameworks(dto, candidates);

		
		
		
		
		
		
	
	
	}
}
