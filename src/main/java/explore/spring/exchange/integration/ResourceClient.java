package explore.spring.exchange.integration;

import explore.spring.exchange.config.ResourceProperties;
import explore.spring.exchange.domain.Resource;
import explore.spring.exchange.integration.response.ResponsePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class ResourceClient {

    private static final String PATH_UNKNOWN = "/posts";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ResourceProperties resourceProperties;

    private final RestTemplate restTemplate;

    @Autowired
    public ResourceClient(ResourceProperties resourceProperties,
                          RestTemplateBuilder restTemplateBuilder) {
        this.resourceProperties = resourceProperties;

        this.restTemplate = restTemplateBuilder.build();
    }

    public List<ResponsePost> getPosts() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(resourceProperties.getUrl())
                .path(PATH_UNKNOWN);

        return requestResources(builder.toUriString());
    }

    private List<ResponsePost> requestResources(String url) {
        try {
            return restTemplate
                    .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResponsePost>>() {
                    }).getBody();
        } catch (Exception exception) {
            logger.error("Error interacting with remote resource", exception);

            throw new IntegrationException(exception);
        }
    }
}
