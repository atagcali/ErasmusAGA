package com.company.erasmusaga.entity;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@Table(name = "ERASMUSAGA_COMMENT")
@Entity(name = "erasmusaga_Comment")
public class Comment extends StandardEntity {
    private static final long serialVersionUID = 1000837581373122078L;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @JoinTable(name = "ERASMUSAGA_COMMENT_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "COMMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToMany
    private List<FileDescriptor> documents;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "comment")
    private List<CommentType> type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<CommentType> getType() {
        return type;
    }

    public void setType(List<CommentType> type) {
        this.type = type;
    }

    public List<FileDescriptor> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FileDescriptor> documents) {
        this.documents = documents;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}