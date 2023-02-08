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
public class MakeShtripsTaskService implements BpmTaskCreate, BpmTaskComplete {
    @Override
    public void create(DelegateTask task) {

    }

    @Override
    public void complete(DelegateTask task) {
        Boolean needOneMoreShtrips = (Boolean) task.getVariable("needOneMoreShtrips");
        if (needOneMoreShtrips != null && needOneMoreShtrips.equals(false)) {
            task.setVariable("needOneMoreShtrips", true);
        }
        log.info("MakeShtripsTaskService.complete executed!");
    }
}
