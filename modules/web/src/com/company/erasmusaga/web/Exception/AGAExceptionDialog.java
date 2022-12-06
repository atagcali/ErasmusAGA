package com.company.erasmusaga.web.Exception;

import com.company.erasmusaga.exception.AGARuntimeException;
import com.haulmont.cuba.web.exception.ExceptionDialog;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;

import javax.annotation.Nullable;
import java.util.Iterator;

public class AGAExceptionDialog extends ExceptionDialog {

    public AGAExceptionDialog(Throwable throwable) {
        this(throwable, null, null);
    }

    public AGAExceptionDialog(Throwable throwable, @Nullable String caption, @Nullable String message) {
        super(throwable, caption, message);
        Iterator<Component> iterator = mainLayout.iterator();
        HorizontalLayout buttonsPanel = null;
        while (iterator.hasNext()){
            Component component = iterator.next();
            if (component instanceof HorizontalLayout){
                buttonsPanel = (HorizontalLayout) component;
            }
        }
        if (buttonsPanel != null){
            Button logoutButton = (Button) buttonsPanel.getComponent(buttonsPanel.getComponentCount() - 1);
            logoutButton.setVisible(false);
        }

        String rootMessage = message != null ? message : getText(throwable);
        if (throwable instanceof AGARuntimeException){
            showStackTraceButton.setVisible(false);
            iterator = mainLayout.iterator();
            while (iterator.hasNext()){
                Component component = iterator.next();
                if (component instanceof TextArea){
                    TextArea textArea = (TextArea) component;
                    textArea.setValue(rootMessage.replace(AGARuntimeException.START_TEXT, ""));
                    textArea.setStyleName("exception-text-area");
                }
            }
        }
    }
}
