package io.devs.processexecutor.bpmn.interfaces;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public interface BpmExecutionStart {
    void start(DelegateExecution execution);
}
