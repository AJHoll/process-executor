package io.devs.processexecutor.bpmn.interfaces;

import org.camunda.bpm.engine.delegate.DelegateTask;

public interface BpmTaskComplete {
    void complete(DelegateTask task);
}
