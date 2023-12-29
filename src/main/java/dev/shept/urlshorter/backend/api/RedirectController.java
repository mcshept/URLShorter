package dev.shept.urlshorter.backend.api;

import dev.shept.urlshorter.backend.data.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

    private final URLService urlService;

    @Autowired
    public RedirectController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{uuid}")
    public RedirectView redirect(@PathVariable String uuid) {
        return urlService.redirect(uuid);
    }

}
