package com.company.erasmusaga.web.screens;

import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

@UiController("erasmusaga_ToDoListScreeenCc")
@UiDescriptor("to-do-list-screeen-cc.xml")
public class ToDoListScreeenCc extends ApplicationListScreen {
    @Override
    public void onInit(InitEvent event) {
        applicationDl.setParameter("seenByCC", false);
        super.onInit(event);
    }
}