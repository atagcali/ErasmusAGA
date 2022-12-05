package com.company.erasmusaga.web.screens;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_ImportScreen")
@UiDescriptor("import-screen.xml")
public class ImportScreen extends Screen {
    @Inject
    private Notifications notifications;
    @Inject
    private TextField<String> input_txt;

    @Subscribe
    public void onInit(InitEvent event) {

    }


    @Subscribe("importBtn")
    public void onImportBtnClick(Button.ClickEvent event) {
        if(input_txt==null || input_txt.getValue().equals("")){
            notifications.create().withCaption("Please enter the localPath").show();
        }
        else {
            String localPath = input_txt.getValue();
            notifications.create().withCaption(localPath).show();
        }
    }
}