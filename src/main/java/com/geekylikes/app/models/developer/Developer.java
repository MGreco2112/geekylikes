package com.geekylikes.app.models.developer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekylikes.app.models.avatar.Avatar;
import com.geekylikes.app.models.geekout.Geekout;
import com.geekylikes.app.models.language.Language;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
public class Developer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private Integer cohort;
//    private String[] languages;
    @OneToMany
    @JoinColumn(name="developer_id", referencedColumnName = "id")
    private List<Geekout> geekouts;

//    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name="developer_language",
            joinColumns = @JoinColumn(name="developer_id"),
            inverseJoinColumns = @JoinColumn(name="language_id")
    )
    public Set<Language> languages;

    @OneToOne
    private Avatar avatar;


    public Developer(String name, String email, Integer cohort, List<Geekout> geekouts, Set<Language> languages, Avatar avatar) {
        this.name = name;
        this.email = email;
        this.cohort = cohort;
        this.geekouts = geekouts;
        this.languages = languages;
        this.avatar = avatar;
    }

    public Developer() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCohort() {
        return cohort;
    }

    public void setCohort(Integer cohort) {
        this.cohort = cohort;
    }

    public void setGeekouts(List<Geekout> geekouts) {
        this.geekouts = geekouts;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
