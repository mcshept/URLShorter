package dev.shept.urlshorter.backend.dao;

import dev.shept.urlshorter.backend.data.entities.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("jpa")
public interface URLDao extends JpaRepository<URL, Long> {

    Optional<URL> findByUrl(String url);
    Optional<URL> findByUuid(String uuid);

}
