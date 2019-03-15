package explore.spring.exchange.controller;

import explore.spring.exchange.domain.Resource;
import explore.spring.exchange.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping({"", "index"})
    public ResponseEntity<List<Resource>> index() {
        return ResponseEntity.ok(resourceService.findAll());
    }
}
