package explore.spring.exchange.service;

import explore.spring.exchange.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceService {

    List<Resource> findAll();
}
