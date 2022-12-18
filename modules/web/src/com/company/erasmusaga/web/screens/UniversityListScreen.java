package com.company.erasmusaga.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.company.erasmusaga.entity.University;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.ArrayList;

@UiController("erasmusaga_UniversityListScreen")
@UiDescriptor("university-list-screen.xml")
public class UniversityListScreen extends Screen {
    @Inject
    private CollectionLoader<University> universityDl;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataGrid<University> universityDG;
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onInit(InitEvent event) {
        universityDl.setMaxResults(20);
        universityDl.load();
    }

    @Subscribe("universityDG")
    public void onUniversityDGItemClick(DataGrid.ItemClickEvent<University> event) {
        if(event.isDoubleClick()){
            University university = event.getItem();
            screenBuilders.screen(this).withScreenClass(EditUniversityScreen.class)
                    .withOptions(new MapScreenOptions(ImmutableMap.of("university",university)))
                    .withLaunchMode(OpenMode.NEW_TAB).show();
        }
    }
}