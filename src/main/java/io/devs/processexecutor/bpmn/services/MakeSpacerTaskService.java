package io.devs.processexecutor.bpmn.services;

import io.devs.processexecutor.bpmn.interfaces.BpmTaskComplete;
import io.devs.processexecutor.bpmn.interfaces.BpmTaskCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MakeSpacerTaskService implements BpmTaskCreate, BpmTaskComplete {
    @Override
    public void complete(DelegateTask task) {
        log.info("MakeSpacerTaskService.complete executed");
    }

    @Override
    public void create(DelegateTask task) {
        log.info("MakeSpacerTaskService.create executed");
    }
}
