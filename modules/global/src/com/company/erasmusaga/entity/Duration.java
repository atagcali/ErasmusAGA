package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "ERASMUSAGA_DURATION")
@Entity(name = "erasmusaga_Duration")
@NamePattern("%s|name")
public class Duration extends StandardEntity {
    private static final long serialVersionUID = -6170997702858679127L;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}