package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Student;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_StudentProfileScreen")
@UiDescriptor("student-profile-screen.xml")
public class StudentProfileScreen extends Screen {
    @Inject
    private CollectionLoader<Student> studentsDl;
    @Inject
    private InstanceContainer<Student> studentDc;
    @Inject
    private UserSessionSource userSessionSource;
    @Subscribe
    public void onInit(InitEvent event) {
        studentsDl.load();
        studentDc.setItem((Student) userSessionSource.getUserSession().getUser());
    }
}