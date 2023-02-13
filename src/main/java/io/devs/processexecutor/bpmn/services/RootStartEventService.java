package io.devs.processexecutor.bpmn.services;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.ActRuExecution;
import io.devs.processexecutor.bpmn.interfaces.BpmExecutionStart;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class RootStartEventService implements BpmExecutionStart {

    private final DSLContext dsl;
    @Override
    public void start(DelegateExecution execution) {
        log.info("Process " + execution.getBusinessKey() + " started!");
    }
}
