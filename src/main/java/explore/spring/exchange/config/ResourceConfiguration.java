package explore.spring.exchange.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableConfigurationProperties(ResourceProperties.class)
public class ResourceConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .additionalInterceptors((HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
                    logger.info("Request: {} {}", request.getMethod(), request.getURI());

                    ClientHttpResponse response = execution.execute(request, body);

                    logger.info("Response: {} {}", response.getStatusCode(), response.getStatusCode());
                    logger.info("Response Body: {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));


                    return response;
                });
    }
}
