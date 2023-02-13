package io.devs.processexecutor.bpmn.services;


import io.devs.processexecutor.bpmn.constants.BpmProcessConst;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.tej.JooQDemo.jooq.sample.model.Tables.DICT_MATERIALS;
import static com.tej.JooQDemo.jooq.sample.model.Tables.DICT_STOREHOUSE;
import static org.jooq.impl.DSL.*;

@Service
@RequiredArgsConstructor
public class NeedMakeOneShtripsTaskService {

    private final DSLContext dsl;

    public void check(DelegateExecution execution) {
        Long shtripsId = dsl.select(DICT_MATERIALS.ID)
                .from(DICT_MATERIALS)
                .where(DICT_MATERIALS.NAME.eq("ШТР74"))
                .fetchInto(Long.class).get(0);

        BigDecimal needShtrips = BpmProcessConst.getShtripsInProduct();
        Boolean shtripsNotNeeded = dsl.select(coalesce(max(inline(1)), inline(0)).cast(Boolean.class))
                .from(DICT_STOREHOUSE)
                .where(DICT_STOREHOUSE.MATERIAL_ID.eq(shtripsId)
                        .and(DICT_STOREHOUSE.AMOUNT.greaterOrEqual(needShtrips)))
                .fetchInto(Boolean.class).get(0);
        execution.setVariable("needOneMoreShtrips", !shtripsNotNeeded);
    }
}
