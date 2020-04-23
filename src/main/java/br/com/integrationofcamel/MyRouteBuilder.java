package br.com.integrationofcamel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import br.com.integrationofcamel.dto.finalcandidates.FinalCandidatesEvaluated;
import br.com.integrationofcamel.dto.resquestpost.DtoRequestPost;
import br.com.integrationofcamel.process.ProcessRequestPost;

public class MyRouteBuilder extends RouteBuilder {
	
	private JacksonDataFormat jDataFormatRequestPost;
	private JacksonDataFormat jDataFormatFinalCandidates;
	
	public MyRouteBuilder() {
		jDataFormatRequestPost = new JacksonDataFormat(DtoRequestPost.class);
		jDataFormatFinalCandidates = new JacksonDataFormat(FinalCandidatesEvaluated.class);
	}
	
    public void configure() {
    	
		restConfiguration().host("localhost:8098/").producerComponent("http");

		rest().post("/searchcandidate").
		consumes("application/json").produces("application/json").
		route().to("direct:searchcandidaterouter").end();
		
		from("direct:searchcandidaterouter")
		.setHeader(Exchange.HTTP_METHOD, simple("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.unmarshal(jDataFormatRequestPost)
		.process(new ProcessRequestPost())
		.to("direct:deserializablefinalcandidates")
		.end();
		
		from("direct:deserializablefinalcandidates")
		.marshal(jDataFormatFinalCandidates)
		.to("kafka:finallCandidatesList?brokers=localhost:9092")
		.end();
		
	}
}

