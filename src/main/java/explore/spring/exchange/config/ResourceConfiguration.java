package explore.spring.exchange.config;

import explore.spring.exchange.integration.ResourceClientHttpRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class ResourceConfiguration {

    @Bean
    public ClientHttpRequestInterceptor resourceClientHttpRequestInterceptor() {
        return new ResourceClientHttpRequestInterceptor();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .additionalInterceptors(resourceClientHttpRequestInterceptor());
    }
}
