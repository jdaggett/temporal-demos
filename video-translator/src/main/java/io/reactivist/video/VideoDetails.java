package io.reactivist.video;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = VideoTranslationDetails.class)
public interface VideoDetails {

    String getSourceLanguage();
    String getTargetLangage();

    String getSourceLanguageTranscription();
    String getTargetLanguageTranscription();

    String getTranslationSummary();
}
//
//// TODO: Specify your translation requirements here:
//    String fromLang = "en";
//    String toLang = "es";
//    String text = "Let's have some fun!";
//
//    Translator.translate(fromLang, toLang, text);
//  }
//
//  int size
//
//file size,
//duration,
//average frame rate,
//audio sample rate,
//video data rate,
//compression settings
//
//Codec: The format used to code the video information
//Audio sample rate: The frequency at which the audio is sampled
//Frame size: The pixel dimensions of each frame
//
//
//Author: The person who created the video
//Date created: The date the video was created
