package com.company.erasmusaga.web.fragments;

import com.company.erasmusaga.web.dialog.FileUpload;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("erasmusaga_FileFragment")
@UiDescriptor("file-fragment.xml")
public class FileFragment extends ScreenFragment {
    FileDescriptor fileDescriptor;
    boolean isAdd;
    FileUpload parentScreen;
    @Inject
    private Button fileName;
    @Inject
    private Button deleteBtn;
    @Inject
    private HBoxLayout filesHbox;
    @Inject
    private ExportDisplay exportDisplay;

    public void setFileDescriptor(FileDescriptor fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }
    public void setAdd(Boolean isAdd) { this.isAdd = isAdd; }
    public void setParentScreen (FileUpload parentScreen){ this.parentScreen = parentScreen; }

    @Subscribe
    public void onInit(InitEvent event) {
        fileName.setCaption(fileDescriptor.getName().toString());
        deleteBtn.setVisible(isAdd);
    }

    @Subscribe("fileName")
    public void onFileNameClick(Button.ClickEvent event) {
        exportDisplay.show(fileDescriptor);
    }

    @Subscribe("deleteBtn")
    public void onDeleteBtnClick(Button.ClickEvent event) {
        filesHbox.setVisible(false);
        parentScreen.removeFromFiles(fileDescriptor);
    }
}