package io.devs.processexecutor.bpmn.services;

import io.devs.processexecutor.bpmn.interfaces.BpmExecutionEnd;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class RootEndEventService implements BpmExecutionEnd {
    @Override
    public void end(DelegateExecution execution) {
        log.info("process " + execution.getBusinessKey() + " completed!");
    }
}
