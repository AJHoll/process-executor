package io.devs.processexecutor.bpmn.interfaces;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public interface BpmExecutionEnd {
    void end(DelegateExecution execution);
}
