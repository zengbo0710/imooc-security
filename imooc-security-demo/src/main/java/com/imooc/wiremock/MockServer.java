package com.imooc.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

public class MockServer {

	public static void main(String[] args) throws IOException {
		configureFor(8081);
		removeAllMappings();
		
		mock("/order/1", "01.txt");
		mock("/order/2", "02.txt");
		System.out.println("end");
	}

	private static void mock(String url, String name) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + name);
		String contentString = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(contentString).withStatus(200)));		
	}
	
	

}
