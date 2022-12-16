package com.company.erasmusaga.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@Table(name = "ERASMUSAGA_APPLICATION")
@Entity(name = "erasmusaga_Application")
public class Application extends StandardEntity {
    private static final long serialVersionUID = -6261459020895699808L;

    @OneToMany(mappedBy = "application")
    private List<Comment> comments;

    @Column(name = "SEEN_BY_CC")
    private Boolean seenByCC;

    @Column(name = "SEEN_BY_ADMIN")
    private Boolean seenByAdmin;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DURATION_ID")
    private Duration duration;

    @ManyToMany
    @JoinTable(name = "ERASMUSAGA_APPLICATION_UNIVERSITY_LINK",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "UNIVERSITY_ID"))
    private List<University> universities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LAST_STATUS_ID")
    private Status lastStatus;

    @OneToMany(mappedBy = "application")
    private List<Status> statuses;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "application")
    private Student student;

    public void setSeenByAdmin(Boolean seenByAdmin) {
        this.seenByAdmin = seenByAdmin;
    }

    public Boolean getSeenByAdmin() {
        return seenByAdmin;
    }

    public Boolean getSeenByCC() {
        return seenByCC;
    }

    public void setSeenByCC(Boolean seenByCC) {
        this.seenByCC = seenByCC;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public Status getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Status lastStatus) {
        this.lastStatus = lastStatus;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addUniversity(University university) { this.universities.add(university); }
}