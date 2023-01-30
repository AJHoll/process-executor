package io.devs.processexecutor.bpmn.plugins;

import io.devs.processexecutor.bpmn.listeners.BpmTimeoutListener;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

import java.util.List;

@RequiredArgsConstructor
public class BpmTimeoutParseListener extends AbstractBpmnParseListener {

    private final BpmTimeoutListener timeoutListener;

    @Override
    public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if (activityBehavior instanceof UserTaskActivityBehavior) {
            UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
            if (userTaskActivityBehavior.getTaskDefinition() != null) {
                List<TaskListener> taskListeners = userTaskActivityBehavior.getTaskDefinition()
                        .getTaskListeners(TaskListener.EVENTNAME_TIMEOUT);
                if (taskListeners == null || !taskListeners.contains(timeoutListener)) {
                    /*userTaskActivityBehavior
                            .getTaskDefinition()
                            .addTimeoutTaskListener("PT15S", timeoutListener);

                    Object t = userTaskActivityBehavior.getTaskDefinition().getTaskListeners();*/
                }
            }

        }
    }
}
