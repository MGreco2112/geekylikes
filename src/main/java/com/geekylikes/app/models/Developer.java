package com.geekylikes.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Developer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private Integer cohort;
    private List<String> languages;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
