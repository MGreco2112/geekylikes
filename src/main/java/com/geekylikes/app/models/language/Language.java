package com.geekylikes.app.models.language;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekylikes.app.models.developer.Developer;

import javax.persistence.*;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
public class Language{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String name;
    private String tag;

//    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name="developer_language",
            joinColumns = @JoinColumn(name="language_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    Set<Developer> developers;

    public Language() {

    }

    public Language(String name, String tag, Set<Developer> developers) {
        this.name = name;
        this.tag = tag;
        this.developers = developers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
