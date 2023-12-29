package dev.shept.urlshorter.backend.data.service;

import dev.shept.urlshorter.backend.dao.URLDao;
import dev.shept.urlshorter.backend.data.entities.URL;
import dev.shept.urlshorter.backend.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Service
public class URLService {

    private final URLDao urlDao;

    @Autowired
    public URLService(@Qualifier("jpa") URLDao urlDao) {
        this.urlDao = urlDao;
    }

    public ResponseEntity<URL> shortURL(URL url) {
        if (url == null) return ResponseEntity.notFound().build();
        Optional<URL> existing = urlDao.findByUrl(url.getUrl());
        if (existing.isPresent()) return ResponseEntity.ok(existing.get());
        else {
            url.setUuid(UUIDGenerator.generateUUID());
            urlDao.save(url);
            return ResponseEntity.ok(url);
        }
    }

    public List<URL> findAllURLs() {
        return urlDao.findAll();
    }

    public ResponseEntity<URL> findURLByUUID(String uuid) {
        Optional<URL> url = urlDao.findByUuid(uuid);
        if (url.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(url.get());
    }

    public RedirectView redirect(String uuid) {
        if ("about".equals(uuid)) {
            return new RedirectView("/about");
        } else if ("add".equals(uuid)) {
            return new RedirectView("/add");
        } else {
            Optional<URL> url = urlDao.findByUuid(uuid);
            if (url.isPresent()) {
                RedirectView view = new RedirectView();
                view.setUrl(url.get().getUrl());
                return view;
            } else {
                return new RedirectView("/not-found");
            }
        }
    }

}
