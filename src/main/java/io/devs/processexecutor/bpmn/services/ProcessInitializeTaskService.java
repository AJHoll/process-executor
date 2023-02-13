package io.devs.processexecutor.bpmn.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.tej.JooQDemo.jooq.sample.model.Tables.*;
import static io.devs.processexecutor.bpmn.constants.BpmProcessConst.*;
import static org.jooq.impl.DSL.*;


@Log4j2
@Service
@RequiredArgsConstructor
public class ProcessInitializeTaskService {

    private final DSLContext dsl;

    public void init(DelegateExecution execution) {

        BigDecimal needToRunProcess = SHTRIPS_AMOUNT_IN_ONE_PRODDUCT.multiply(BigDecimal.valueOf(CONSIGNMENT_QUANTITY));

        List<BigDecimal> needAmountList = dsl.select(coalesce(DICT_STOREHOUSE.AMOUNT, BigDecimal.ZERO).minus(needToRunProcess))
                .from(DICT_MATERIALS)
                .leftJoin(DICT_STOREHOUSE).on(DICT_STOREHOUSE.MATERIAL_ID.eq(DICT_MATERIALS.ID))
                .where(DICT_MATERIALS.NAME.eq("МОЦ"))
                .fetchInto(BigDecimal.class);
        if (!needAmountList.isEmpty() && needAmountList.get(0) != null && needAmountList.get(0).compareTo(BigDecimal.ZERO) >= 0) {
            execution.setVariable("allMaterialExists", true);
        } else {
            execution.setVariable("allMaterialExists", false);
            execution.setVariable("materialAmountNeedsToPurchase", needAmountList.get(0).abs());
        }
    }
}
