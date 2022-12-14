package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Application;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("erasmusaga_ApplicationListScreen")
@UiDescriptor("application-list-screen.xml")
public class ApplicationListScreen extends Screen {
    @Inject
    private CollectionLoader<Application> applicationDl;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataGrid<Application> applicationDG;

    @Subscribe
    public void onInit(InitEvent event) {
        applicationDl.setMaxResults(20);
        applicationDl.load();
   }

    @Subscribe("applicationDG")
    public void onApplicationDGItemClick(DataGrid.ItemClickEvent<Application> event) {
        if(event.isDoubleClick()){
            Application application = event.getItem();
            screenBuilders.screen(this).withScreenClass(EditApplicationScreen.class)
                    .withOptions(new MapScreenOptions(ImmutableMap.of("application", application)))
                    .withLaunchMode(OpenMode.NEW_TAB).withAfterCloseListener(e->applicationDl.load()).show();
        }
    }

}