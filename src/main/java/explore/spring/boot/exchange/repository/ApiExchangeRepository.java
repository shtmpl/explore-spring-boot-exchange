package explore.spring.boot.exchange.repository;

import explore.spring.boot.exchange.domain.ApiExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiExchangeRepository extends JpaRepository<ApiExchange, Long> {
}
