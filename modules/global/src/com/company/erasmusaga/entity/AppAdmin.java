package com.company.erasmusaga.entity;

import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;

@Entity(name = "erasmusaga_AppAdmin")
public class AppAdmin extends User {
    private static final long serialVersionUID = -8859627715732693615L;
}