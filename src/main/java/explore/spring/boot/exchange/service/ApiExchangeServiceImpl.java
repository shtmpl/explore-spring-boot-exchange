package explore.spring.boot.exchange.service;

import explore.spring.boot.exchange.domain.ApiExchange;
import explore.spring.boot.exchange.repository.ApiExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiExchangeServiceImpl implements ApiExchangeService {

    private final ApiExchangeRepository apiExchangeRepository;

    @Autowired
    public ApiExchangeServiceImpl(ApiExchangeRepository apiExchangeRepository) {
        this.apiExchangeRepository = apiExchangeRepository;
    }

    @Override
    public ApiExchange save(ApiExchange exchange) {
        return apiExchangeRepository.save(exchange);
    }
}
