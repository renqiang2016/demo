package com.example.ren.model.neo4j;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电影
 * Created by qiang.ren on 2018/4/17.
 */
@NodeEntity
public class Movie {

    @GraphId
    private Long id;

    private String name;

    private String photo;

    @DateLong
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private Date createDate;

    @Relationship(type = "扮演", direction = Relationship.INCOMING)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Movie() {
    }
}
