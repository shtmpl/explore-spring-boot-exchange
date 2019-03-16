package explore.spring.exchange.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import explore.spring.exchange.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ResourceService resourceService;

    @Autowired
    public ApiController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("")
    public ResponseEntity<JsonNode> index(@RequestParam String resource) {

        return ResponseEntity.ok(extractResponseFromResource(new ObjectMapper(), resourceService.request(resource)));
    }

    private static JsonNode extractResponseFromResource(ObjectMapper mapper, String resource) {
        try {
            return mapper.readValue(resource, JsonNode.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
