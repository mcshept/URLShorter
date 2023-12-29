package dev.shept.urlshorter.backend.api;

import dev.shept.urlshorter.backend.data.entities.URL;
import dev.shept.urlshorter.backend.data.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class URLController {

    private final URLService urlService;

    @Autowired
    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/add")
    public ResponseEntity<URL> shortURL(@RequestBody URL url) {
        return urlService.shortURL(url);
    }

    @GetMapping("/all_urls")
    public List<URL> findAllURLs() {
        return urlService.findAllURLs();
    }

    @GetMapping("/about/{uuid}")
    public ResponseEntity<URL> findByUUID(@PathVariable String uuid) {
        return urlService.findURLByUUID(uuid);
    }

}
