package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Application;
import com.company.erasmusaga.entity.Course;
import com.company.erasmusaga.web.fragments.FileFragment;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("erasmusaga_CourseScreen")
@UiDescriptor("course-screen.xml")
public class CourseScreen extends Screen {
    @Inject
    private InstanceContainer<Course> courseDc;
    @Inject
    private Fragments fragments;
    @Inject
    private HBoxLayout syllabusHbox;

    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        courseDc.setItem((Course) options.getParams().get("course"));
        FileFragment fragment = fragments.create(this, FileFragment.class);
        fragment.setAdd(false);
        fragment.setFileDescriptor(courseDc.getItem().getSyllabus());
        syllabusHbox.add(fragment.getFragment());
    }
    
}