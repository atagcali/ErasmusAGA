package com.company.erasmusaga.web.screens;


import com.company.erasmusaga.entity.Course;
import com.company.erasmusaga.entity.University;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("erasmusaga_EditUniversityScreen")
@UiDescriptor("edit-university-screen.xml")
public class EditUniversityScreen extends Screen {
    @Inject
    private InstanceContainer<University> universityDc;
    @Inject
    private CollectionLoader<Course> universityCoursesL;
    @Inject
    private DataGrid<Course> universityCoursesDG;
    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    public void onInit(InitEvent event) {
        universityCoursesL.setMaxResults(10);
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        universityDc.setItem((University) options.getParams().get("university"));
        universityCoursesL.setParameter("university", universityDc.getItem());
        universityCoursesL.load();
        if (universityCoursesL.getContainer().getMutableItems().isEmpty()){
            universityCoursesDG.setVisible(false);
        }
    }

    @Subscribe("universityCoursesDG")
    public void onUniversityCoursesDGItemClick(DataGrid.ItemClickEvent<Course> event) {
        if(event.isDoubleClick()){
            Course course = event.getItem();
            screenBuilders.screen(this).withScreenClass(CourseScreen.class)
                    .withOptions(new MapScreenOptions(ImmutableMap.of("course", course)))
                    .withLaunchMode(OpenMode.NEW_TAB).show();
        }
    }
}