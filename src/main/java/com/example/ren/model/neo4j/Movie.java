package com.example.ren.model.neo4j;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * 电影
 * Created by qiang.ren on 2018/4/17.
 */
@NodeEntity
public class Movie {

    @GraphId
    private Long id;

    private String title;

    private String year;

    private String tagline;

    @Relationship(type = "ACTS_IN", direction = Relationship.INCOMING)
    private List<Role> roles = new ArrayList<>();

    public void addRole(Actor actor, String name){
        Role role = new Role(name, actor, this);
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Movie() {
    }

    public Movie(String title, String year, String tagline, List<Role> roles) {
        this.title = title;
        this.year = year;
        this.tagline = tagline;
        this.roles = roles;
    }
}
