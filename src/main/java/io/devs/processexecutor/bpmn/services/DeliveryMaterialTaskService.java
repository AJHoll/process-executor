package io.devs.processexecutor.bpmn.services;

import io.devs.processexecutor.bpmn.interfaces.BpmTaskComplete;
import io.devs.processexecutor.bpmn.interfaces.BpmTaskTimeout;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeliveryMaterialTaskService implements BpmTaskComplete, BpmTaskTimeout {
    @Override
    public void complete(DelegateTask task) {

        task.setVariable("allMaterialExists", true);
    }

    @Override
    public void timeout(DelegateTask task) {
        task.complete();
    }
}
