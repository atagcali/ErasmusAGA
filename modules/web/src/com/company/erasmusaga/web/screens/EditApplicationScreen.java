package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.*;
import com.company.erasmusaga.web.dialog.FileUpload;
import com.company.erasmusaga.web.fragments.CommentFragment;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.*;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogActions;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogOutcome;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.model.CollectionContainer;
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
    @Inject
    private CollectionContainer<University> unisDc;
    @Inject
    private Dialogs dialogs;
    @Inject
    private UiComponents uiComponents;

    CommitContext ccc = new CommitContext();
    @Inject
    private Button cancelBtn;

    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        applicationDc.setItem((Application) options.getParams().get("application"));
        commentDl.setParameter("application",applicationDc.getItemOrNull());
        loadComments();
        unisDc.setItems(applicationDc.getItem().getUniversities());
        if(applicationDc.getItemOrNull().getLastStatus().getType().getName().equals("Canceled")){
            cancelBtn.setVisible(false);
        }
    }

    @Subscribe("saveCommentBtn")
    public void onSaveCommentBtnClick(Button.ClickEvent event) {
        if(commentTxt.getValue()==null || commentTxt.getValue().equals(""))
            notifications.create().withCaption("Please enter a comment").show();
        else{
            CommitContext commitContext = new CommitContext();
            Comment comment = dataManager.create(Comment.class);
            comment.setApplication(applicationDc.getItemOrNull());
            comment.setContent(commentTxt.getValue());
            comment.setUser(userSession.getUser());
            for (FileDescriptor file : files) {
                commitContext.addInstanceToCommit(file);
            }
            applicationDc.getItem().setSeenByCC(false);
            applicationDc.getItem().setSeenByAdmin(false);
            comment.setDocuments(files);
            commitContext.addInstanceToCommit(comment);
            dataManager.commit(commitContext);
            applicationDc.setItem(dataManager.reload(applicationDc.getItem(),"application-view"));
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
        commentDl.load();
        commentsVbox.removeAll();
        for (Comment mutableItem : commentDl.getContainer().getMutableItems()) {
            CommentFragment commentFragment = fragments.create(this,CommentFragment.class);
            commentFragment.setComment(mutableItem);
            commentsVbox.add(commentFragment.getFragment());
        }
    }
    @Subscribe("cancelBtn")
    public void onCancelBtnClick(Button.ClickEvent event) {
        InputDialog dialog = dialogs.createInputDialog(this)
                .withActions(DialogActions.OK_CANCEL).
                withCloseListener(inputDialogCloseEvent -> {
                    if (inputDialogCloseEvent.closedWith(DialogOutcome.OK)) {
                        Status status = dataManager.create(Status.class);
                        status.setApplication(applicationDc.getItemOrNull());
                        status.setType(dataManager.load(StatusType.class).query("e.name=:name")
                                .parameter("name", "Canceled").one());
                        applicationDc.getItemOrNull().setLastStatus(status);
                        applicationDc.getItemOrNull().getStatuses().add(status);
                        ccc.addInstanceToCommit(applicationDc.getItem(), "application-view");
                        ccc.addInstanceToCommit(status,"status-view");
                    }
                }).show();
        Label<String> label = uiComponents.create(Label.NAME);
        label.setValue("Do you want to cancel your Application?");
        dialog.getWindow().add(label, 0);
    }
    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        dataManager.commit(ccc);
    }
}