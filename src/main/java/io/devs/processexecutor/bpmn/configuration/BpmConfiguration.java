package io.devs.processexecutor.bpmn.configuration;

import io.devs.processexecutor.bpmn.listeners.BpmTimeoutListener;
import io.devs.processexecutor.bpmn.plugins.BpmProcessEnginePlugin;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BpmConfiguration {

    private final BpmTimeoutListener bpmTimeoutListener;

    @Bean
    public BpmProcessEnginePlugin bpmEventPlugin() {
        return new BpmProcessEnginePlugin(bpmTimeoutListener);
    }
}
