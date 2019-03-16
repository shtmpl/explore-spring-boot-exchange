package explore.spring.exchange.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import explore.spring.exchange.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final ResourceService resourceService;

    @Autowired
    public ApiController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("")
    public ResponseEntity<JsonNode> get(@RequestParam String resource) {
        return ResponseEntity.ok(extractResponseFromResource(MAPPER, resourceService.request(resource, HttpMethod.GET)));
    }

    @PostMapping("")
    public ResponseEntity<JsonNode> post(@RequestParam String resource) {
        return ResponseEntity.ok(extractResponseFromResource(MAPPER, resourceService.request(resource, HttpMethod.POST)));
    }

    @PutMapping("")
    public ResponseEntity<JsonNode> put(@RequestParam String resource) {
        return ResponseEntity.ok(extractResponseFromResource(MAPPER, resourceService.request(resource, HttpMethod.PUT)));
    }

    @DeleteMapping("")
    public ResponseEntity<JsonNode> delete(@RequestParam String resource) {
        return ResponseEntity.ok(extractResponseFromResource(MAPPER, resourceService.request(resource, HttpMethod.DELETE)));
    }

    private static JsonNode extractResponseFromResource(ObjectMapper mapper, String resource) {
        try {
            return mapper.readValue(resource, JsonNode.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
