package explore.spring.boot.exchange.repository;

import explore.spring.boot.exchange.integration.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

    private final ResourceClient resourceClient;

    @Autowired
    public ResourceRepositoryImpl(ResourceClient resourceClient) {
        this.resourceClient = resourceClient;
    }

    @Override
    public String request(String resource, HttpMethod method) {
        return resourceClient.request(resource, method);
    }
}
