package com.company.erasmusaga.web.fragments;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_FileFragment")
@UiDescriptor("file-fragment.xml")
public class FileFragment extends ScreenFragment {
    FileDescriptor fileDescriptor;
    @Inject
    private Button fileName;

    public void setFileDescriptor(FileDescriptor fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    @Subscribe
    public void onInit(InitEvent event) {
        fileName.setCaption(fileDescriptor.getName().toString());
    }
}