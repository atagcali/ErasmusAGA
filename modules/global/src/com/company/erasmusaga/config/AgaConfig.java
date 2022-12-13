package com.company.erasmusaga.config;

import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.defaults.DefaultString;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

public interface AgaConfig extends Config {
    @Property("erasmusaga.StatusType.Evaluation")
    @DefaultString("2198dd21-bcf5-fbae-15d9-26bf66a4f752")
    String getOnEvaluation();
    void setOnEvaluation(String stateID);

    @Property("erasmusaga.StatusType.Approved")
    @DefaultString("21aeb8ca-5e34-5fd1-8337-8ae0c53bd547")
    String getApproved();
    void setApproved(String stateID);

    @Property("erasmusaga.StatusType.Declined")
    @DefaultString("dc7c2d3c-b954-b691-71a6-f2f184f1b0ba")
    String getDeclined();
    void setDeclined(String stateID);
}
