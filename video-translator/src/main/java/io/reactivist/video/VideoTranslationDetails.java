package io.reactivist.video;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import sun.tools.tree.ThisExpression;

import java.io.File;

@JsonDeserialize(as = VideoTranslationDetails.class)
public class VideoTranslationDetails {

    String fromLanguage;
    String toLanguage;

    // Substitute with a real File object
    String videoFile;

    String translationSummary;

    public VideoTranslationDetails(String fromLanguage, String toLanguage, String videoFile) {
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.videoFile = videoFile;
    }

    String getSourceLanguage() {
        return fromLanguage;
    }

    String getTargetLanguage() {
        return toLanguage;
    }

    String getAudioTranscription() {
        return videoFile;
        // If we were to get a local file, or a file from S3
        // videoFile = new File(videoFile.getAbsolutePath()); // get the file locally, or from S3
    }

    String getSourceLanguageTranscription {
        return videoFile.getSourceAudioTransciption();
    }

    String getTargetLanguageTranscription() {
        return videoFile.getTargetAudioTransciption()
    }

    String getTranslationSummary() {
        // get summary from Google APIs
        return "Language Summary: ";
    }

}