package com.company.erasmusaga.listeners;

import com.company.erasmusaga.config.AgaConfig;
import com.company.erasmusaga.entity.Application;

import java.util.UUID;

import com.company.erasmusaga.entity.Status;
import com.company.erasmusaga.entity.StatusType;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component("erasmusaga_ApplicationChangedListener")
public class ApplicationChangedListener implements BeforeInsertEntityListener<Application> {

    @EventListener
    public void beforeCommit(EntityChangedEvent<Application, UUID> event) {

    }

    @Override
    public void onBeforeInsert(Application entity, EntityManager entityManager) {
        DataManager dataManager = AppBeans.get(DataManager.class);
        AgaConfig agaConfig = AppBeans.get(AgaConfig.class);
        Status status = dataManager.create(Status.class);
        status.setType(dataManager.load(StatusType.class).id(UUID.fromString(agaConfig.getOnEvaluation())).one());
        entity.setLastStatus(status);
        status.setApplication(entity);
        entityManager.persist(status);
    }
}