package com.company.erasmusaga.web.screens.coursecoordinator;

import com.company.erasmusaga.entity.CourseCoordinator;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionLoader;
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
    private CollectionLoader<CourseCoordinator> ccDltemp;
    @Inject
    private InstanceContainer<CourseCoordinator> ccDc;
    //@Inject
    //private TextField<String> input_password;

    @Inject
    private PasswordField passwordField;
    @Inject
    private Notifications notifications;
    @Inject
    private CheckBox showPasswordCb;

    @Subscribe
    public void onInit(InitEvent event) {
        showPasswordCb.setValue(false);
        ccDltemp.load();
        ccDc.setItem(ccDltemp.getContainer().getMutableItems().get(0));
        //setPassword(showPasswordCb.getValue());
        ////ccDc.setItem((CourseCoordinator) userSessionSource.getUserSession().getUser());
    }
//    public void setPassword(boolean show){
//        if(show)
//            passwordField.setValue(passwordField.getValue());
////        else
////            passwordField.setValue("*************");
//
//    }

    @Subscribe("showPasswordCb")
    public void onShowPasswordCbValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        //setPassword(event.getValue());
        notifications.create()
                .withCaption(passwordField.getValue())
                .show();
    }

//    @Subscribe("showPasswordBtn")
//    protected void onShowPasswordBtnClick(Button.ClickEvent event) {
//        notifications.create()
//                .withCaption(passwordField.getValue())
//                .show();
//    }
}