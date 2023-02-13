package io.devs.processexecutor.bpmn.interfaces;

import org.camunda.bpm.engine.delegate.DelegateTask;

public interface BpmTaskTimeout {
    void timeout(DelegateTask task);
}
