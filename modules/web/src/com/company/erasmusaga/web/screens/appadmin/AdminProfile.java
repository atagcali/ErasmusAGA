package com.company.erasmusaga.web.screens.appadmin;

import com.company.erasmusaga.entity.AppAdmin;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_AdminProfile")
@UiDescriptor("admin-profile.xml")
public class AdminProfile extends Screen {
    @Inject
    private InstanceContainer<AppAdmin> adminDc;

    @Inject
    private UserSessionSource userSessionSource;

    @Subscribe
    public void onInit(InitEvent event) {
        adminDc.setItem((AppAdmin) userSessionSource.getUserSession().getUser());
    }
}