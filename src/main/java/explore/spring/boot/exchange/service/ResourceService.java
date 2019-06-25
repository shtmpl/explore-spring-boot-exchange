package explore.spring.boot.exchange.service;

import org.springframework.http.HttpMethod;

public interface ResourceService {

    String request(String resource, HttpMethod method);
}
