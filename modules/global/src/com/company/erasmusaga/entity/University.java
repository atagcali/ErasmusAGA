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

    @Column(name = "QUOTA")
    private Integer quota;

    @OneToMany(mappedBy = "acceptedUniversity")
    private List<Student> acceptedStudents;

    @Column(name = "AGREEMENT_TYPE")
    private String agreementType;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "LOWEST_GRADE")
    private String lowestGrade;

    @Column(name = "HIGHEST_GRADE")
    private String highestGrade;

    @Column(name = "PASSING_GRADE")
    private String passingGrade;

    @Column(name = "DEPARTMENT")
    private String department;

    @OneToMany(mappedBy = "university")
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

    public List<Student> getAcceptedStudents() {
        return acceptedStudents;
    }

    public void setAcceptedStudents(List<Student> acceptedStudents) {
        this.acceptedStudents = acceptedStudents;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public String getHighestGrade() {
        return highestGrade;
    }

    public void setHighestGrade(String highestGrade) {
        this.highestGrade = highestGrade;
    }

    public String getLowestGrade() {
        return lowestGrade;
    }

    public void setLowestGrade(String lowestGrade) {
        this.lowestGrade = lowestGrade;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public boolean isAvailable () {
        if(this.acceptedStudents.size() <= quota)
            return true;
        else
            return false;
    }

    @PrePersist
    public void prePersist() {
        if(name == null){
            throw new RuntimeException("name boş olamaz");
        }
    }
}