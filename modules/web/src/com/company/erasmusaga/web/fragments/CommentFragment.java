package com.company.erasmusaga.web.fragments;

import com.company.erasmusaga.entity.Comment;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.Fragments;
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

    public void setComment(Comment comment){ this.comment = comment; }

    @Subscribe
    public void onInit(InitEvent event) {
        commentTxt.setValue(comment.getContent());
        for (FileDescriptor fileDescriptor : comment.getDocuments()) {
            FileFragment fileFragment = fragments.create(this, FileFragment.class);
            fileFragment.setFileDescriptor(fileDescriptor);
            Vbox.add(fileFragment.getFragment());
        }
    }
}