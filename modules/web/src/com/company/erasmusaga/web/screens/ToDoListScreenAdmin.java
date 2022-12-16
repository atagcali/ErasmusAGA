package com.company.erasmusaga.web.screens;

import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

@UiController("erasmusaga_ToDoListScreenAdmin")
@UiDescriptor("to-do-list-screen-admin.xml")
public class ToDoListScreenAdmin extends ApplicationListScreen {
    @Override
    public void onInit(InitEvent event) {
        applicationDl.setMaxResults(20);
        applicationDl.setParameter("seenByAdmin", false);
        applicationDl.load();
    }
}