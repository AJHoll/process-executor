package io.devs.processexecutor.bpmn.services;

import com.tej.JooQDemo.jooq.sample.model.routines.Addmaterialamount;
import io.devs.processexecutor.bpmn.interfaces.BpmTaskComplete;
import io.devs.processexecutor.bpmn.interfaces.BpmTaskTimeout;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.tej.JooQDemo.jooq.sample.model.Tables.DICT_MATERIALS;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeliveryMaterialTaskService implements BpmTaskComplete, BpmTaskTimeout {

    private final DSLContext dsl;


    @Override
    public void complete(DelegateTask task) {

        task.setVariable("allMaterialExists", true);
        Long metallId = dsl.select(DICT_MATERIALS.ID)
                .from(DICT_MATERIALS)
                .where(DICT_MATERIALS.NAME.eq("МОЦ"))
                .fetchInto(Long.class).get(0);
        Addmaterialamount func = new Addmaterialamount();
        func.setMaterialId(BigDecimal.valueOf(metallId));
        func.setAmount(BigDecimal.valueOf(5000));
        func.execute(dsl.configuration());
    }

    @Override
    public void timeout(DelegateTask task) {
        task.complete();
    }
}
