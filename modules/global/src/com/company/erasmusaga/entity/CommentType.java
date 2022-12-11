package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "ERASMUSAGA_COMMENT_TYPE")
@Entity(name = "erasmusaga_CommentType")
@NamePattern("%s|name")
public class CommentType extends StandardEntity {
    private static final long serialVersionUID = 8905955832462507861L;

    @Column(name = "NAME")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}