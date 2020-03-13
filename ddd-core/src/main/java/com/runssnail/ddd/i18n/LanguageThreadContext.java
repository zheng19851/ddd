package com.runssnail.ddd.i18n;

import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LanguageThreadContext {

    public static final String LANGUAGE_KEY = "_lang_";

    private static ThreadLocal<Locale> threadLocal = new ThreadLocal<>();

    public static Locale get() {
        Locale locale = threadLocal.get();
        if (locale != null) {
            return locale;
        }
        return Locale.getDefault();
    }

    public static void set(String lang) {
        Locale l = new Locale(lang);
        LanguageThreadContext.set(l);
    }

    public static void set(Locale locale) {
        log.info("set lang {}", locale.toString());
        threadLocal.set(locale);
    }

    public static void remove() {
        log.info("remove lang");
        threadLocal.remove();
    }
}
