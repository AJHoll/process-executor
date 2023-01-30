package io.devs.processexecutor.bpmn.plugins;

import io.devs.processexecutor.bpmn.listeners.BpmTimeoutListener;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BpmProcessEnginePlugin extends AbstractProcessEnginePlugin {

    private final BpmTimeoutListener bpmTimeoutListener;

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        List<BpmnParseListener> preParseListeners;
        preParseListeners = processEngineConfiguration.getCustomPreBPMNParseListeners();
        if (preParseListeners == null) {
            preParseListeners = new ArrayList<>();
            processEngineConfiguration.setCustomPreBPMNParseListeners(preParseListeners);
        }
        preParseListeners.add(new BpmTimeoutParseListener(bpmTimeoutListener));
    }
}
