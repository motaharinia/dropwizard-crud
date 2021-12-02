package com.motaharinia.crud.config.mvc;

import com.miao.easyi18n.support.ResourceBundleMessageSource;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Singleton
public class MessageServiceImpl implements MessageService{
    private final ResourceBundleMessageSource messageSource;
    public final String random;

    @Inject
    public MessageServiceImpl(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
        this.random= Instant.now().toString();
    }

    @Override
    public String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, List<String> args, Locale locale) {
        return messageSource.getMessage(key, args.toArray(), locale);
    }
}
