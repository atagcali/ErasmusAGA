package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.Student;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PasswordField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("erasmusaga_StudentProfileScreen")
@UiDescriptor("student-profile-screen.xml")
public class StudentProfileScreen extends Screen {
    @Inject
    private CollectionLoader<Student> studentsDl;
    @Inject
    private InstanceContainer<Student> studentDc;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private CheckBox showPasswordCb;
//    @Inject
//    private TextField<String> input_password;
    @Inject
    private Notifications notifications;
    @Inject
    private PasswordField passwordField;

    @Subscribe
    public void onInit(InitEvent event) {
        //"StudentsDc" container'ı örnek objeyi alabilmek için oluşturuldu. Gerekli DÜzenlemeler yapıldıktan sonra
        //silinmesi gerekiyor. Container'ı xml'den tamamen sildikten sonra, önce inject edilen loader silinecek sonra
        //aşşağıdaki "studentsDl.load ve studentdc.set Item satırları silinecek. Hepsi bittikten sonra onInıt'te comment
        //olan studentDc.setItem Fonksiyonu commentten çıkarılsın.
        //password change için bir kaydet butonu koyun ve textField'dan aldığınız  yeni şifre kaydedilsin.
        showPasswordCb.setValue(false);
        studentsDl.load();
        studentDc.setItem(studentsDl.getContainer().getMutableItems().get(0));
        //setPassword(showPasswordCb.getValue());
        //studentDc.setItem((Student) userSessionSource.getUserSession().getUser());
    }
//    public void setPassword(boolean show){
//        if(show)
//            input_password.setValue(studentDc.getItem().getPassword());
//        else
//            input_password.setValue("*************");
//
//    }

    @Subscribe("showPasswordCb")
    public void onShowPasswordCbValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        //setPassword(event.getValue());
        notifications.create()
                .withCaption(passwordField.getValue())
                .show();
    }

}