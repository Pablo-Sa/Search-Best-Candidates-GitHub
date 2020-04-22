package br.com.integrationofcamel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import br.com.integrationofcamel.dto.resquestpost.DtoRequestPost;
import br.com.integrationofcamel.process.ProcessEndPointGitHub;
import br.com.integrationofcamel.process.ProcessRequestPost;

public class MyRouteBuilder extends RouteBuilder {
	
	private JacksonDataFormat jDataFormatRequestPost;
	
	public MyRouteBuilder() {
		jDataFormatRequestPost = new JacksonDataFormat(DtoRequestPost.class);
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
		.to("kafka:BestCandidates?brokers=localhost:9092")
		.end();
		
		from("kafka:BestCandidates?brokers=localhost:9092").process(new ProcessEndPointGitHub())
		.to("cql://localhost/camel_ks?cql=")
		.end();
	}
}

