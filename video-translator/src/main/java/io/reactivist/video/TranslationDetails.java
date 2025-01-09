package io.reactivist.video;

public interface TranslationDetails {
    String getSourceLanguage();
    String getTargetLanguage();

    String getSourceLanguageTranscription();
    String getTargetLanguageTranscription();

    String getTranslationSummary();
}
