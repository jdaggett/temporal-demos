package io.reactivist.video;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface TranslationActivity {
    @ActivityMethod
    String translate(String fromLang, String toLang, String text);
}
