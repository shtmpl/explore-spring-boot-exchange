package explore.spring.boot.exchange.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResourceClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    @Autowired
    public ResourceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String request(String resource, HttpMethod method) {
        try {
            return restTemplate.exchange(resource, method, null, String.class).getBody();
        } catch (Exception exception) {
            logger.error("Error interacting with remote resource", exception);

            throw new IntegrationException(exception);
        }
    }
}
