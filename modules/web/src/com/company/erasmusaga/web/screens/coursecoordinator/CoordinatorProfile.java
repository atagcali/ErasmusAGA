package com.company.erasmusaga.web.screens.coursecoordinator;

import com.company.erasmusaga.entity.CourseCoordinator;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_CoordinatorProfile")
@UiDescriptor("coordinator-profile.xml")
public class CoordinatorProfile extends Screen {
    @Inject
    private InstanceContainer<CourseCoordinator> ccDc;

    @Inject
    private UserSessionSource userSessionSource;

    @Subscribe
    public void onInit(InitEvent event) {
        ccDc.setItem((CourseCoordinator) userSessionSource.getUserSession().getUser());

    }
}