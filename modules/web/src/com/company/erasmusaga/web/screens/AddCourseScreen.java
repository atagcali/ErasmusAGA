package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Comment;
import com.company.erasmusaga.entity.Course;
import com.company.erasmusaga.entity.Department;
import com.company.erasmusaga.entity.University;
import com.company.erasmusaga.web.dialog.FileUpload;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@UiController("erasmusaga_AddCourseScreen")
@UiDescriptor("add-course-screen.xml")
public class AddCourseScreen extends Screen {
    University university;
    @Inject
    private DataManager dataManager;
    @Inject
    private InstanceContainer<Course> courseDc;
    @Inject
    private ScreenBuilders screenBuilders;

    private List<FileDescriptor> files = new ArrayList<>();
    private Course course;
    @Inject
    private Notifications notifications;
    @Inject
    private CollectionLoader<Department> departmentDl;

    @Subscribe
    public void onInit(InitEvent event) {
        departmentDl.load();
        MapScreenOptions options = (MapScreenOptions)event.getOptions();
        university = (University) options.getParams().get("university");
        course = dataManager.create(Course.class);
        course.setUniversity(university);
        courseDc.setItem(course);
    }

    public void saveButtonPressed() {
        CommitContext ccc = new CommitContext();
        ccc.addInstanceToCommit(university);
        if(!files.isEmpty()){
            ccc.addInstanceToCommit(files.get(0));
            courseDc.getItem().setSyllabus(files.get(0));
        }
        ccc.addInstanceToCommit(course);
        dataManager.commit(ccc);
        files.clear();
        this.close(StandardOutcome.CLOSE);
    }

    public void importSyllabus() {
        FileUpload screen = screenBuilders.screen(this).withScreenClass(FileUpload.class)
                .withOpenMode(OpenMode.DIALOG)
                .withOptions(new MapScreenOptions(ImmutableMap.of("files", files)))
                .build();
        screen.addAfterCloseListener(afterCloseEvent -> {
            if(files.size()>1)
                notifications.create().withCaption("Syllabus cannot contain more than one file").show();
            else
                files = screen.getFiles();
        });
        screen.show();
    }
}