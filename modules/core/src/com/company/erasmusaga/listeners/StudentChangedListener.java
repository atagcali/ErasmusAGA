package com.company.erasmusaga.listeners;

import com.company.erasmusaga.entity.Student;

import java.util.UUID;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("erasmusaga_StudentChangedListener")
public class StudentChangedListener  {

    @EventListener
    public void beforeCommit(EntityChangedEvent<Student, UUID> event) {

    }
}