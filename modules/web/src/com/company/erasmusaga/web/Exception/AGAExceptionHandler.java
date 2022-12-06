package com.company.erasmusaga.web.Exception;

import com.company.erasmusaga.exception.AGARuntimeException;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.AppUI;
import com.haulmont.cuba.web.exception.DefaultExceptionHandler;
import com.vaadin.ui.Window;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class AGAExceptionHandler extends DefaultExceptionHandler  {
    @Override
    protected void showDialog(App app, AppUI ui, Throwable exception) {
        Throwable rootCause = ExceptionUtils.getRootCause(exception);
        if (rootCause == null) {
            rootCause = exception;
        }

        AGAExceptionDialog dialog = rootCause instanceof AGARuntimeException ?
                new AGAExceptionDialog(rootCause, ((AGARuntimeException)rootCause).getCaption(), null) :
                new AGAExceptionDialog(rootCause);

        for (Window window : ui.getWindows()) {
            if (window.isModal()) {
                dialog.setModal(true);
                break;
            }
        }
        ui.addWindow(dialog);
        dialog.focus();
    }

}
