package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@Table(name = "ERASMUSAGA_UNIVERSITY")
@Entity(name = "erasmusaga_University")
@NamePattern("%s|name")
public class University extends StandardEntity {
    private static final long serialVersionUID = 8328773308797842280L;

    @Column(name = "NAME")
    private String name;

    @JoinTable(name = "ERASMUSAGA_UNIVERSITY_COURSE_LINK",
            joinColumns = @JoinColumn(name = "UNIVERSITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    @ManyToMany
    private List<Course> courses;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POLICIES_ID")
    private FileDescriptor policies;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUIREMENTS_ID")
    private FileDescriptor requirements;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public FileDescriptor getRequirements() {
        return requirements;
    }

    public void setRequirements(FileDescriptor requirements) {
        this.requirements = requirements;
    }

    public FileDescriptor getPolicies() {
        return policies;
    }

    public void setPolicies(FileDescriptor policies) {
        this.policies = policies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PrePersist
    public void prePersist() {
        if(name == null){
            throw new RuntimeException("name boş olamaz");
        }
    }
}