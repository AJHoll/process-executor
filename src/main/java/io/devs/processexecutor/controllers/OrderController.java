package io.devs.processexecutor.controllers;


import io.devs.processexecutor.bpmn.constants.BpmProcessConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.RuntimeService;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.tej.JooQDemo.jooq.sample.model.tables.DictOrder.DICT_ORDER;

@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    private final RuntimeService runtimeService;
    private final DSLContext dsl;

    @PostMapping("order/add/{qty}")
    public void startOrder(@PathVariable("qty") BigDecimal qty) {
        Record1<String> rec = dsl.insertInto(DICT_ORDER, DICT_ORDER.QTY).values(qty).returningResult(DICT_ORDER.CODE).fetchOne();
        if (rec != null && rec.getValue(DICT_ORDER.CODE) != null && !rec.getValue(DICT_ORDER.CODE).isEmpty()) {
            new Thread(() -> {
                executeNewProcess("production_base_inline_v.0.3", rec.getValue(DICT_ORDER.CODE), qty);
            }).start();
        }
    }

    private BigDecimal executeNewProcess(String processDefinitionId, String businessKey, BigDecimal needToProduct) {
        if (needToProduct.compareTo(BigDecimal.ZERO) > 0) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runtimeService.startProcessInstanceByKey(processDefinitionId, businessKey);
            return executeNewProcess(processDefinitionId, businessKey, needToProduct.subtract(BigDecimal.valueOf(BpmProcessConst.CONSIGNMENT_QUANTITY)));
        }
        return BigDecimal.ZERO;
    }
}
