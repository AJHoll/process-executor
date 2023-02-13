package io.devs.processexecutor.bpmn.services;

import io.devs.processexecutor.bpmn.interfaces.BpmTaskTimeout;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseMaterialTaskService implements BpmTaskTimeout {
    @Override
    public void timeout(DelegateTask task) {
        task.complete();
    }
}
