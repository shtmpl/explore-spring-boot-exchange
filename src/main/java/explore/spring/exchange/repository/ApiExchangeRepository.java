package explore.spring.exchange.repository;

import explore.spring.exchange.domain.ApiExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiExchangeRepository extends JpaRepository<ApiExchange, Long> {
}
