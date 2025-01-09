package io.reactivist.video;

import com.google.api.translate.Language;
import com.google.api.translate.Translator;

public class TranslationActivityImpl implements TranslationActivity {

    @Override
    public String translate(String fromLang, String toLang, String text) {
        System.out.printf("\nTranslating $%d into language %s.\n", fromLang, toLang);
        System.out.flush();

        return text;
        // Use the Google Translation APIs to convert from one language to another
        //return Translator.translate(fromLang, toLang, text);
    }

}