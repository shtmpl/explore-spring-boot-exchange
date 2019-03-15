package explore.spring.exchange.repository;

import explore.spring.exchange.domain.Resource;
import explore.spring.exchange.integration.ResourceClient;
import explore.spring.exchange.integration.response.ResponsePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

    private final ResourceClient resourceClient;

    @Autowired
    public ResourceRepositoryImpl(ResourceClient resourceClient) {
        this.resourceClient = resourceClient;
    }

    @Override
    public List<Resource> findAll() {
        return resourceClient.getPosts()
                .stream()
                .map(ResourceRepositoryImpl::extractResourceFromResponse)
                .collect(Collectors.toList());
    }

    private static Resource extractResourceFromResponse(ResponsePost response) {
        Resource result = new Resource();
        result.setId(response.getId());

        return result;
    }
}
