package explore.spring.exchange.repository;

import org.springframework.http.HttpMethod;

public interface ResourceRepository {

    String request(String resource, HttpMethod method);
}
