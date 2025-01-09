package io.reactivist.video;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface VideoTranslationWorkflow {
    @WorkflowMethod
    void translate(VideoDetails video);
}
