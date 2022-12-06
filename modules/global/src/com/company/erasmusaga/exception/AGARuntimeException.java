package com.company.erasmusaga.exception;

import com.haulmont.cuba.core.global.SupportedByClient;

@SupportedByClient
public class AGARuntimeException extends RuntimeException{
    public static final String START_TEXT = "AGARuntimeException: ";
    private String caption = null;

    public AGARuntimeException(String message){super(message);}

    public AGARuntimeException(String message, String caption){
        super(message);
        this.caption = caption;
    }
    public String getCaption(){
        return caption;
    }
}
