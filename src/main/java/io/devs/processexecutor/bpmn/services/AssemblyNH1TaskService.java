package io.devs.processexecutor.bpmn.services;

import com.tej.JooQDemo.jooq.sample.model.routines.Addmaterialamount;
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
public class AssemblyNH1TaskService implements BpmTaskCreate, BpmTaskComplete, BpmTaskTimeout {

    private final DSLContext dsl;
    @Override
    public void complete(DelegateTask task) {
        Long productId = dsl.select(DICT_MATERIALS.ID)
                .from(DICT_MATERIALS)
                .where(DICT_MATERIALS.NAME.eq("НХ-01"))
                .fetchInto(Long.class).get(0);
        Addmaterialamount addFunc = new Addmaterialamount();
        addFunc.setMaterialId(BigDecimal.valueOf(productId));
        addFunc.setAmount(BigDecimal.valueOf(BpmProcessConst.CONSIGNMENT_QUANTITY));
        addFunc.execute(dsl.configuration());
    }

    @Override
    public void create(DelegateTask task) {
        log.info("AssemblyNH1TaskService.create executed");
    }

    @Override
    public void timeout(DelegateTask task) {
        task.complete();
    }
}
