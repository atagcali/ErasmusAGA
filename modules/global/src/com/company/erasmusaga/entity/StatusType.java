package com.company.erasmusaga.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "ERASMUSAGA_STATUS_TYPE")
@Entity(name = "erasmusaga_StatusType")
@NamePattern("%s|name")
public class StatusType extends StandardEntity {
    private static final long serialVersionUID = 1017557263126475831L;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}