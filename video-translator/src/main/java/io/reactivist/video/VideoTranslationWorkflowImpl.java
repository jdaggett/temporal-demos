package io.reactivist.video;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import io.temporal.common.RetryOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class VideoTranslationWorkflowImpl implements VideoTranlationWorkflow {
    private static final String TRANSLATE = "Translate";

    // RetryOptions specify how to automatically handle retries when Activities fail
    private final RetryOptions retryOptions = RetryOptions.newBuilder()
        .setInitialInterval(Duration.ofSeconds(1))
        .setMaximumInterval(Duration.ofSeconds(30))
        .setBackoffCoefficient(2)
        .setMaximumAttempts(5000)
        .build();

    // ActivityOptions specify the limits on how long an Activity can execute
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
        .setRetryOptions(retryOptions)
        .setStartToCloseTimeout(Duration.ofSeconds(5))
        .setScheduleToCloseTimeout(Duration.ofSeconds(5000))
        .build();

    private final Map<String, ActivityOptions> perActivityMethodOptions = new HashMap<String, ActivityOptions>() {{
        // A heartbeat time-out is a proof-of life indicator that an activity is still working.
        // The 5 second duration used here waits for up to 5 seconds to hear a heartbeat.
        // If one is not heard, the Activity fails.
        // The `withdraw` method is hard-coded to succeed, so this never happens.
        // Use heartbeats for long-lived event-driven applications.
        put(TRANSLATE, ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());
    }};

    // ActivityStubs enable calls to methods as if the Activity object is local but actually perform an RPC invocation
    private final TranslationActivity translationActivityStub = Workflow.newActivityStub(TranslationActivity.class, defaultActivityOptions, perActivityMethodOptions);


    @Override
    public String translateVideo(VideoDetails videoDetails) {
        String sourceLanguage = videoDetails.getSourceLanguage();
        String destinationLanguage = videoDetails.getDestinationLanguage();

        // 1. get audio transcription
        try {
            String textToSummarize = videoDetails.getAudioTranscription();

            translationActivityStub.translate(sourceLanguage, targetLanguage, textToSummarize);
        } catch (Exception e) {
            // If the translation fails, for any exception, it's caught here
            System.out.printf("[%s] Translation of $%d from account %s failed", transactionReferenceId, amountToTransfer, sourceAccountId);
            System.out.flush();

            // Transaction ends here
            return;
        }
        // 2. Return summary of translated
    }

}
