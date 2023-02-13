package io.devs.processexecutor.bpmn.services;

import com.tej.JooQDemo.jooq.sample.model.routines.Removematerialamount;
import io.devs.processexecutor.bpmn.constants.BpmProcessConst;
import io.devs.processexecutor.bpmn.interfaces.BpmExecutionStart;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.tej.JooQDemo.jooq.sample.model.Tables.DICT_MATERIALS;

@Service
@RequiredArgsConstructor
public class ParallelProductionGatewayService implements BpmExecutionStart {

    private final DSLContext dsl;

    @Override
    public void start(DelegateExecution execution) {
        Long shtripsId = dsl.select(DICT_MATERIALS.ID)
                .from(DICT_MATERIALS)
                .where(DICT_MATERIALS.NAME.eq("ШТР74"))
                .fetchInto(Long.class).get(0);
        Removematerialamount addFunc = new Removematerialamount();
        addFunc.setMaterialId(BigDecimal.valueOf(shtripsId));
        addFunc.setAmount(BpmProcessConst.getMetallInProduct());
        addFunc.execute(dsl.configuration());
    }
}
