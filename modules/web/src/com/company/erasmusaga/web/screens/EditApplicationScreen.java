package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Application;
import com.company.erasmusaga.entity.Comment;
import com.company.erasmusaga.web.dialog.FileUpload;
import com.company.erasmusaga.web.fragments.CommentFragment;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@UiController("erasmusaga_EditApplicationScreen")
@UiDescriptor("edit-Application-screen.xml")
public class EditApplicationScreen extends Screen {
    @Inject
    private InstanceContainer<Application> applicationDc;
    @Inject
    private CollectionLoader<Comment> commentDl;
    @Inject
    private Fragments fragments;
    @Inject
    private TextArea<String> commentTxt;
    @Inject
    private Notifications notifications;
    @Inject
    private DataManager dataManager;
    @Inject
    private UserSession userSession;
    @Inject
    private VBoxLayout commentsVbox;
    @Inject
    private ScreenBuilders screenBuilders;
    List<FileDescriptor> files = new ArrayList<>();
    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        applicationDc.setItem((Application) options.getParams().get("application"));
        commentDl.setParameter("application",applicationDc.getItemOrNull());
        loadComments();
    }

    @Subscribe("saveCommentBtn")
    public void onSaveCommentBtnClick(Button.ClickEvent event) {
        if(commentTxt.getValue()==null || commentTxt.getValue().equals(""))
            notifications.create().withCaption("Please enter a comment").show();
        else{
            CommitContext ccc = new CommitContext();
            Comment comment = dataManager.create(Comment.class);
            comment.setApplication(applicationDc.getItemOrNull());
            comment.setContent(commentTxt.getValue());
            comment.setUser(userSession.getUser());
            applicationDc.setItem(dataManager.reload(applicationDc.getItem(),"application-view"));
            for (FileDescriptor file : files) {
                ccc.addInstanceToCommit(file);
            }
            applicationDc.getItem().setSeenByCC(false);
            applicationDc.getItem().setSeenByAdmin(false);
            comment.setDocuments(files);
            ccc.addInstanceToCommit(comment);
            dataManager.commit(ccc);
            loadComments();
            commentTxt.setValue(null);
            files.clear();
        }
    }

    @Subscribe("addFileBtn")
    public void onAddFileBtnClick(Button.ClickEvent event) {
        FileUpload screen = screenBuilders.screen(this).withScreenClass(FileUpload.class)
                .withOpenMode(OpenMode.DIALOG)
                .withOptions(new MapScreenOptions(ImmutableMap.of("files", files)))
                .build();
        screen.addAfterCloseListener(afterCloseEvent -> {
           files = screen.getFiles();
        });
        screen.show();
    }
    public void loadComments(){
        commentsVbox.removeAll();
        commentDl.load();
        for (Comment mutableItem : commentDl.getContainer().getMutableItems()) {
            CommentFragment commentFragment = fragments.create(this,CommentFragment.class);
            commentFragment.setComment(mutableItem);
            commentsVbox.add(commentFragment.getFragment());
        }
    }
    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        dataManager.commit(applicationDc.getItem());
    }
}