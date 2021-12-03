package com.motaharinia.crud.config.mvc;

import com.miao.easyi18n.support.ResourceBundleMessageSource;
import com.motaharinia.crud.utility.tools.string.MessageService;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.time.Instant;
import java.util.List;
import java.util.Locale;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس پیاده سازی سرویس ترجمه
 */

@Singleton
public class MessageServiceImpl implements MessageService {
    private final ResourceBundleMessageSource messageSource;
    public final String random;

    @Context
    protected HttpServletRequest httpServletRequest;

    @Inject
    public MessageServiceImpl(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
        this.random = Instant.now().toString();
    }

    /**
     * متد ترجمه کلید
     *
     * @param key    کلید ترجمه
     * @param locale زبان
     * @return خروجی: پیام ترجمه شده
     */
    @Override
    @NotNull
    public String getMessage(@NotNull String key, @NotNull Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * متد ترجمه کلید با داشتن متغیرهای داخل ترجمه
     *
     * @param key          کلید ترجمه
     * @param argumentList متغیرهای داخل ترجمه
     * @param locale       زبان
     * @return خروجی: پیام ترجمه شده
     */
    @Override
    @NotNull
    public String getMessage(@NotNull String key, @NotNull List<String> argumentList, @NotNull Locale locale) {
        return messageSource.getMessage(key, argumentList.toArray(), locale);
    }

}
