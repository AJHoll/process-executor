package io.devs.processexecutor.bpmn.services;

import io.devs.processexecutor.bpmn.interfaces.BpmExecutionStart;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class RootStartEventService implements BpmExecutionStart {
    @Override
    public void start(DelegateExecution execution) {
        log.info("Process " + execution.getBusinessKey() + " started!");
    }
}
