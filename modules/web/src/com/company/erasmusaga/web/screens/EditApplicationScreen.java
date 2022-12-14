package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Application;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("erasmusaga_EditApplicationScreen")
@UiDescriptor("edit-Application-screen.xml")
public class EditApplicationScreen extends Screen {
    @Inject
    private InstanceContainer<Application> applicationDc;

    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        applicationDc.setItem((Application) options.getParams().get("application"));
    }

}