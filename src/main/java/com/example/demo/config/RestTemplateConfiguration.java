package com.example.demo.config;

import java.util.Collections;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.MediaType;

@Configuration
public class RestTemplateConfiguration {

	final String uri ="https://jsonplaceholder.typicode.com";
	@Bean(name = "restTemplate")
	RestTemplate thirdPartyApiRestTemplate(RestTemplateBuilder builder) throws Exception{
		HttpClient httpClient = HttpClients.createDefault();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return builder
				.requestFactory(() -> factory)
				.rootUri(uri)
				.additionalCustomizers((restTemplate -> {
					restTemplate.getInterceptors().add(
							(request , body, execution) -> {
						request.getHeaders().setAccept(Collections.singletonList(MediaType.ALL));
						request.getHeaders().setCacheControl("no-cache");
						request.getHeaders().setConnection("keep-alive");
						return execution.execute(request, body);
					}
							);
				})).build();
	}
}
