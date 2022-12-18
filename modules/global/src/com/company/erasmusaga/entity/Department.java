package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "ERASMUSAGA_DEPARTMENT")
@Entity(name = "erasmusaga_Department")
@NamePattern("%s|name")
public class Department extends StandardEntity {
    private static final long serialVersionUID = -1362898608128270131L;

    @Column(name = "NAME")
    protected String name;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}