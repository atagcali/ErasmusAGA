package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "ERASMUSAGA_COURSE")
@Entity(name = "erasmusaga_Course")
@NamePattern("%s|name")
public class Course extends StandardEntity {
    private static final long serialVersionUID = 8694429948719710009L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXEMPTED_BILKENT_COURSE_CODE")
    private String exemptedBilkentCourseCode;

    @Column(name = "EXEMPTED_BILKENT_COURSE_NAME")
    private String exemptedBilkentCourseName;

    @Column(name = "EXEMPTED_BILKENT_COURSE_CREDIT")
    private Double exemptedBilkentCourseCredit;

    @Column(name = "CREDIT")
    private Double credit;

    @Column(name = "CODE")
    private String code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SYLLABUS_ID")
    private FileDescriptor syllabus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;

    public void setExemptedBilkentCourseCredit(Double exemptedBilkentCourseCredit) {
        this.exemptedBilkentCourseCredit = exemptedBilkentCourseCredit;
    }

    public Double getExemptedBilkentCourseCredit() {
        return exemptedBilkentCourseCredit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getCredit() {
        return credit;
    }

    public String getExemptedBilkentCourseName() {
        return exemptedBilkentCourseName;
    }

    public void setExemptedBilkentCourseName(String exemptedBilkentCourseName) {
        this.exemptedBilkentCourseName = exemptedBilkentCourseName;
    }

    public String getExemptedBilkentCourseCode() {
        return exemptedBilkentCourseCode;
    }

    public void setExemptedBilkentCourseCode(String exemptedBilkentCourseCode) {
        this.exemptedBilkentCourseCode = exemptedBilkentCourseCode;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}