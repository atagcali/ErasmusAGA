package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "ERASMUSAGA_COURSE")
@Entity(name = "erasmusaga_Course")
@NamePattern("%s|name")
public class Course extends StandardEntity {
    private static final long serialVersionUID = 8694429948719710009L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private Integer code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SYLLABUS_ID")
    private FileDescriptor syllabus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @JoinTable(name = "ERASMUSAGA_UNIVERSITY_COURSE_LINK",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "UNIVERSITY_ID"))
    @ManyToMany
    private List<University> universities;

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public FileDescriptor getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(FileDescriptor syllabus) {
        this.syllabus = syllabus;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}