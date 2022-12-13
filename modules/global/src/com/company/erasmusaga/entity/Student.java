package com.company.erasmusaga.entity;

import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "erasmusaga_Student")
public class Student extends User {
    private static final long serialVersionUID = -527565257349891853L;

    @Column(name = "BILKENT_ID")
    private Integer bilkentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "TOTAL_GRADE")
    private Double totalGrade;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "student")
    private List<Comment> comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;

    public void setBilkentID(Integer bilkentID) {
        this.bilkentID = bilkentID;
    }

    public Integer getBilkentID() {
        return bilkentID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Double totalGrade) {
        this.totalGrade = totalGrade;
    }

}