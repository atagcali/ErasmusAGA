package com.company.erasmusaga.web.fragments;

import com.company.erasmusaga.entity.Comment;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_CommentFragment")
@UiDescriptor("comment-fragment.xml")

public class CommentFragment extends ScreenFragment {
    Comment comment;
    @Inject
    private TextArea<String> commentTxt;
    @Inject
    private Fragments fragments;
    @Inject
    private VBoxLayout Vbox;
    @Inject
    private Label<String> userLbl;

    public void setComment(Comment comment){ this.comment = comment; }

    @Subscribe
    public void onInit(InitEvent event) {
        userLbl.setValue(comment.getUser().getName() + " Tarafından " + comment.getCreateTs().toString() + " tarihinde " + " atıldı.");
        commentTxt.setValue(comment.getContent());
        for (FileDescriptor fileDescriptor : comment.getDocuments()) {
            FileFragment fileFragment = fragments.create(this, FileFragment.class);
            fileFragment.setFileDescriptor(fileDescriptor);
            fileFragment.setAdd(false);
            Vbox.add(fileFragment.getFragment());
        }
    }
}