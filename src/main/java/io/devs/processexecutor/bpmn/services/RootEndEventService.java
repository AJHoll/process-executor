package io.devs.processexecutor.bpmn.services;

import io.devs.processexecutor.bpmn.constants.BpmProcessConst;
import io.devs.processexecutor.bpmn.interfaces.BpmExecutionEnd;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.tej.JooQDemo.jooq.sample.model.tables.DictOrder.DICT_ORDER;

@Log4j2
@Service
@RequiredArgsConstructor
public class RootEndEventService implements BpmExecutionEnd {
    private final DSLContext dsl;

    @Override
    public void end(DelegateExecution execution) {
        dsl.update(DICT_ORDER).set(DICT_ORDER.QTY_EXECUTE, (DICT_ORDER.QTY_EXECUTE.plus(BigDecimal.valueOf(BpmProcessConst.CONSIGNMENT_QUANTITY))))
                .where(DICT_ORDER.CODE.eq(execution.getBusinessKey())).execute();
        log.info("process " + execution.getBusinessKey() + " completed!");
    }
}
