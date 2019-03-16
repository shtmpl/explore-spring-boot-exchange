package explore.spring.exchange.integration;

import explore.spring.exchange.domain.ApiExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ResourceClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ApiExchange exchange = (ApiExchange) RequestContextHolder.currentRequestAttributes()
                .getAttribute("exchange", RequestAttributes.SCOPE_REQUEST);

        logger.info("=> ResourceClientHttpRequestInterceptor: {}", exchange);
        logger.info("=> ResourceClientHttpRequestInterceptor: {} {}", request.getMethod(), request.getURI());

        ClientHttpResponse response = execution.execute(request, body);

        logger.info("=> ResourceClientHttpRequestInterceptor: {}", response.getStatusCode());

        return response;
    }
}
