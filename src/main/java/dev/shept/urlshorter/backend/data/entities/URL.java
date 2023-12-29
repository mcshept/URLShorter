package dev.shept.urlshorter.backend.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "urls")
public class URL {

    @Id
    @GeneratedValue
    private Long id;
    private String url;
    private String uuid;

}
