package explore.spring.exchange.repository;

import explore.spring.exchange.domain.Resource;

import java.util.List;

public interface ResourceRepository {

    List<Resource> findAll();
}
