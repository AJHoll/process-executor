package io.devs.processexecutor.bpmn.interfaces;

import org.camunda.bpm.engine.delegate.DelegateTask;

public interface BpmTaskCreate {
    void create(DelegateTask task);
}
