package it.habble.api.log;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


public class LogRequest implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        ClientHttpResponse response = execution.execute(request, body);
        log(request, body, response);
        return response;
    }

    private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
//    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    	IOUtils.copy(response.getBody(), baos);
//    	byte[] bytes = baos.toByteArray();

    	
    	System.out.println("REQUEST: " + new String(body) + 
    					   ", RESPONSE: " + response.getStatusText());
    }
}
