package com.mylearn.microutil;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class Translator {
    private final static Logger log = LoggerFactory.getLogger(Translator.class);
    private static ResourceBundleMessageSource messageSource;
    @Autowired
    Translator(ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }
    private static final List<Locale> locales = Arrays.asList(new Locale("en"), new Locale("vi"));

    public String toLocaleWithDefault(String msgCode, String defaultMessage, @Nullable Object[] args) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(msgCode, null, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Locale locale = resolveLocale(request);
        return messageSource.getMessage(msgCode, args, defaultMessage, locale);
    }

    public static String toLocaleWithDefault(String msgCode, String defaultMessage) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(msgCode, null, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Locale locale = resolveLocale(request);
        return messageSource.getMessage(msgCode, null, defaultMessage, locale);
    }

    public static String toLocale(String msgCode, @Nullable Object[] args) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(msgCode, null, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Locale locale = resolveLocale(request);
        return messageSource.getMessage(msgCode, args, locale);
    }

    public static String toLocale(String msgCode) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(msgCode, null, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            return messageSource.getMessage(msgCode, null, resolveLocale(request));
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return "";
        }
    }
    public static String getMessage(String msgCode, Object... args) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(msgCode, null, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String message = messageSource.getMessage(msgCode, null, resolveLocale(request));
            return MessageFormat.format(message,args);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return "";
        }
    }

    public static Locale resolveLocale(HttpServletRequest request) {
        if (request.getHeader("Accept-Language") == null || request.getHeader("Accept-Language").isEmpty()) {
            return new Locale("vi");
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
        return Locale.lookup(list, locales);
    }

    public static String getMessageForProcess(String msgCode, Object... args) {
        return messageSource.getMessage(msgCode, args,  new Locale("vi"));
    }
}
