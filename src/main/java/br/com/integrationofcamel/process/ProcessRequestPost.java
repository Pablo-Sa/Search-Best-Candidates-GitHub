package br.com.integrationofcamel.process;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.integrationofcamel.dto.finalcandidates.FinalCandidatesEvaluated;
import br.com.integrationofcamel.dto.resquestpost.DtoRequestPost;
import br.com.integrationofcamel.util.ProcessExchange;

public class ProcessRequestPost implements Processor {

	private ArrayList<String> candidates = new ArrayList<>();
	private FinalCandidatesEvaluated finalAllCandidates = new FinalCandidatesEvaluated();

	@Override
	public void process(Exchange exchange) throws Exception {
		DtoRequestPost dto = exchange.getIn().getBody(DtoRequestPost.class);
		candidates = ProcessExchange.languages(dto);
		finalAllCandidates = ProcessExchange.frameworks(dto, candidates);
		exchange.getOut().setBody(finalAllCandidates, FinalCandidatesEvaluated.class);
	}
}
