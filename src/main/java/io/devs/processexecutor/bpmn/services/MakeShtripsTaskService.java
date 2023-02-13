package io.devs.processexecutor.bpmn.services;

import com.tej.JooQDemo.jooq.sample.model.routines.Addmaterialamount;
import com.tej.JooQDemo.jooq.sample.model.routines.Removematerialamount;
import io.devs.processexecutor.bpmn.constants.BpmProcessConst;
import io.devs.processexecutor.bpmn.interfaces.BpmTaskComplete;
import io.devs.processexecutor.bpmn.interfaces.BpmTaskCreate;
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
public class MakeShtripsTaskService implements BpmTaskCreate, BpmTaskComplete, BpmTaskTimeout {

    private final DSLContext dsl;

    @Override
    public void create(DelegateTask task) {

    }

    @Override
    public void complete(DelegateTask task) {
        Long shtripsId = dsl.select(DICT_MATERIALS.ID)
                .from(DICT_MATERIALS)
                .where(DICT_MATERIALS.NAME.eq("ШТР74"))
                .fetchInto(Long.class).get(0);
        Addmaterialamount addFunc = new Addmaterialamount();
        addFunc.setMaterialId(BigDecimal.valueOf(shtripsId));
        addFunc.setAmount(BigDecimal.valueOf(1000));
        addFunc.execute(dsl.configuration());

        Long metallId = dsl.select(DICT_MATERIALS.ID)
                .from(DICT_MATERIALS)
                .where(DICT_MATERIALS.NAME.eq("МОЦ"))
                .fetchInto(Long.class).get(0);
        Removematerialamount removeFunc = new Removematerialamount();
        removeFunc.setMaterialId(BigDecimal.valueOf(metallId));
        removeFunc.setAmount(BigDecimal.valueOf(1000));
        removeFunc.execute(dsl.configuration());
    }

    @Override
    public void timeout(DelegateTask task) {
        task.complete();
    }
}
