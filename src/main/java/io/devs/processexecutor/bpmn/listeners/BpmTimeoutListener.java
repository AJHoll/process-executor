package io.devs.processexecutor.bpmn.listeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service
public class BpmTimeoutListener implements TaskListener {

    @Override
    public void notify(DelegateTask task) {

    }
}
