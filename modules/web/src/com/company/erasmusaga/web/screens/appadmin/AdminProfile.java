package com.company.erasmusaga.web.screens.appadmin;

import com.company.erasmusaga.entity.AppAdmin;
import com.company.erasmusaga.entity.CourseCoordinator;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PasswordField;
import com.haulmont.cuba.gui.model.CollectionLoader;
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
    private CollectionLoader<AppAdmin> adminDltemp;
    @Inject
    private InstanceContainer<AppAdmin> adminDc;
    @Inject
    private PasswordField passwordField;
    @Inject
    private Notifications notifications;
    @Inject
    private CheckBox showPasswordCb;

    @Subscribe
    public void onInit(InitEvent event) {
        showPasswordCb.setValue(false);
        adminDltemp.load();
        adminDc.setItem(adminDltemp.getContainer().getMutableItems().get(0));
        //setPassword(showPasswordCb.getValue());
        ////ccDc.setItem((CourseCoordinator) userSessionSource.getUserSession().getUser());
    }

    @Subscribe("showPasswordCb")
    public void onShowPasswordCbValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        //setPassword(event.getValue());
        notifications.create()
                .withCaption(passwordField.getValue())
                .show();
    }
}