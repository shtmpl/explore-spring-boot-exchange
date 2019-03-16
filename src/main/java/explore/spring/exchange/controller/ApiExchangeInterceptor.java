package explore.spring.exchange.controller;

import explore.spring.exchange.domain.ApiExchange;
import explore.spring.exchange.domain.ApiRequest;
import explore.spring.exchange.domain.ApiResponse;
import explore.spring.exchange.service.ApiExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiExchangeInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApiExchangeService apiExchangeService;

    @Autowired
    public ApiExchangeInterceptor(ApiExchangeService apiExchangeService) {
        this.apiExchangeService = apiExchangeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        ApiExchange apiExchange = new ApiExchange();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(request.getRequestURL().toString());
        apiRequest.setMethod(request.getMethod());

        ApiResponse apiResponse = new ApiResponse();

        apiExchange.setRequest(apiRequest);
        apiExchange.setResponse(apiResponse);

        request.setAttribute("exchange", apiExchange);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        ApiExchange apiExchange = (ApiExchange) request.getAttribute("exchange");
        apiExchange.getResponse().setStatusCode(response.getStatus());

        apiExchangeService.save(apiExchange);
    }
}
