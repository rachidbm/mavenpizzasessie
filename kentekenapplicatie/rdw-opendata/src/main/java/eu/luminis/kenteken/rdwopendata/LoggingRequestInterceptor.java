package eu.luminis.kenteken.rdwopendata;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
	
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().remove("Accept-Charset");
        final ClientHttpResponse response = execution.execute(request, body);
        log(request, body, response);
        return response;
    }

    private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        LOG.debug("Kenteken search HTTP {} {}", request.getMethod().name(), request.getURI());
        request.getHeaders().entrySet().forEach(header -> LOG.debug("Header {}: {}", header.getKey(), header.getValue()));
        LOG.debug("Kenteken search response: {} ({})", response.getStatusCode(), response.getStatusText());
    }
}
