package com.company.erasmusaga.web.dialog;

import com.company.erasmusaga.web.fragments.FileFragment;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.FileMultiUploadField;
import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UiController("erasmusaga_FileUpload")
@UiDescriptor("file-upload.xml")
public class FileUpload extends Screen {
    List<FileDescriptor> files;
    @Inject
    private FileMultiUploadField fileUpload;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private Notifications notifications;
    @Inject
    private HBoxLayout customHBox;
    @Inject
    private Fragments fragments;

    public List<FileDescriptor> getFiles() {
        return files;
    }

    @Subscribe
    public void onInit(InitEvent event) {
        Map<String, Object> params;
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        params = options.getParams();
        files = (List<FileDescriptor>) params.get("files");
        customHBox.removeAll();
        updateBox(files);
    }


    @Subscribe("fileUpload")
    public void onFileUploadQueueUploadComplete(FileMultiUploadField.QueueUploadCompleteEvent event) {
        for (Map.Entry<UUID, String> entry : fileUpload.getUploadsMap().entrySet()) {
            UUID fileId = entry.getKey();
            String fileName = entry.getValue();
            FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
            try {
                fileUploadingAPI.putFileIntoStorage(fileId, fd);
            } catch (FileStorageException e) {
                throw new RuntimeException("Error saving file to FileStorage", e);
            }
            files.add(fd);
            customHBox.removeAll();
            updateBox(files);
        }
        notifications.create()
                .withCaption("Uploaded files: " + fileUpload.getUploadsMap().values())
                .show();
        fileUpload.clearUploads();
    }

    public void updateBox(List<FileDescriptor> files){
        for (FileDescriptor file : files) {
            FileFragment fileFragment = fragments.create(this,FileFragment.class);
            fileFragment.setFileDescriptor(file);
            customHBox.add(fileFragment.getFragment());
        }
    }

}