package explore.spring.boot.exchange.integration;

import explore.spring.boot.exchange.domain.ApiExchange;
import explore.spring.boot.exchange.domain.ApiRequest;
import explore.spring.boot.exchange.domain.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

public class ResourceClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ApiExchange auxExchange = new ApiExchange();
        auxExchange.setDateCreated(Date.from(Instant.now()));
        auxExchange.setRequest(extractApiRequest(request, body));

        ClientHttpResponse response = execution.execute(request, body);

        ApiExchange mainExchange = (ApiExchange) RequestContextHolder.currentRequestAttributes().getAttribute("exchange", RequestAttributes.SCOPE_REQUEST);
        if (mainExchange == null) {
            return response;
        }

        auxExchange.setResponse(extractApiResponse(response));

        auxExchange.setMain(mainExchange);
        mainExchange.getAuxiliaries().add(auxExchange);

        return response;
    }

    private static ApiRequest extractApiRequest(HttpRequest request, byte[] body) {
        ApiRequest result = new ApiRequest();
        result.setUrl(request.getURI().toString());
        result.setMethod(request.getMethodValue());

        String json = new String(body, DEFAULT_CHARSET);
        if (!json.isEmpty()) {
            result.setBody(json);
        }

        return result;
    }

    private static ApiResponse extractApiResponse(ClientHttpResponse response) throws IOException {
        ApiResponse result = new ApiResponse();
        result.setStatusCode(response.getRawStatusCode());

        String json = StreamUtils.copyToString(response.getBody(), DEFAULT_CHARSET);
        if (!json.isEmpty()) {
            result.setBody(json);
        }

        return result;
    }
}
