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
public class DrillBlankForKP01LTaskService implements BpmTaskCreate, BpmTaskComplete {
    @Override
    public void complete(DelegateTask task) {
        log.info("DrillBlankForKP01LTaskService.complete executed");
    }

    @Override
    public void create(DelegateTask task) {
        log.info("DrillBlankForKP01LTaskService.create executed");
    }
}
