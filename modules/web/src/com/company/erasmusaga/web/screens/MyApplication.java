package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Application;
import com.company.erasmusaga.entity.Student;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_MyApplication")
@UiDescriptor("my-application.xml")
public class MyApplication extends EditApplicationScreen {
    @Inject
    private UserSessionSource userSessionSource;

    @Override
    public void onInit(InitEvent event) {
        applicationDc.setItem(dataManager.load(Application.class).view("application-view")
                .query("e.student=:student")
                .parameter("student", (Student)userSessionSource.getUserSession().getUser()).one());
        commentDl.setParameter("application",applicationDc.getItemOrNull());
        loadComments();
        unisDc.setItems(applicationDc.getItem().getUniversities());
        if(applicationDc.getItemOrNull().getLastStatus().getType().getName().equals("Canceled")){
            cancelBtn.setVisible(false);
        }
    }

}