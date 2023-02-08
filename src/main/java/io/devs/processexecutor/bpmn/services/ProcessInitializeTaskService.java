package io.devs.processexecutor.bpmn.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProcessInitializeTaskService {

    public void init(DelegateExecution execution) {
        log.info("ProcessInitializeTaskService.init(execution) running");
        execution.setVariable("allMaterialExists", false);
    }
}
