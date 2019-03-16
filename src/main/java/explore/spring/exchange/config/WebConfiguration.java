package explore.spring.exchange.config;

import explore.spring.exchange.controller.ApiExchangeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final ApiExchangeInterceptor apiExchangeInterceptor;

    @Autowired
    public WebConfiguration(ApiExchangeInterceptor apiExchangeInterceptor) {
        this.apiExchangeInterceptor = apiExchangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiExchangeInterceptor);
    }
}
