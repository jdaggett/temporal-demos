package io.reactivist.video;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VideoTranslationApp {

    public static void main(String[] args) throws Exception {

        // A WorkflowServiceStubs communicates with the Temporal front-end service.
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newLocalServiceStubs();

        // A WorkflowClient wraps the stub.
        WorkflowClient client = WorkflowClient.newInstance(serviceStub);

        // Workflow options configure
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.VIDEO_TRANSLATION_TASK_QUEUE)
                .setWorkflowId("video-translation-workflow")
                .build();

        // WorkflowStubs enable calls to methods as if the Workflow object is local
        VideoTranslationWorkflow workflow = client.newWorkflowStub(VideoTranslationWorkflow.class, options);

        // Configure the details for this request
        String referenceId = UUID.randomUUID().toString().substring(0, 18);

        String fromLanguage = "en";
        String toLanguage = "jp";
        String audioTranscription = "Here are some wonderful things that you can do with Temporal";

        TranslationDetails translation = new VideoTranslationDetails(fromLanguage, toLanguage, audioTranscription);

        // Perform asynchronous execution.
        WorkflowExecution we = WorkflowClient.start(workflow::translate, translation);

        System.out.printf("[WorkflowID: %s]\n[RunID: %s]\n[Translation Reference: %s]\n\n", we.getWorkflowId(), we.getRunId(), referenceId);
        System.exit(0);
    }
}
