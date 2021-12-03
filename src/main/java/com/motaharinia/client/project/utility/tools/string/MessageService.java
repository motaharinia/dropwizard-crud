package com.motaharinia.client.project.utility.tools.string;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس اینترفیس سرویس ترجمه که باید در پروژه اصلی پیاده سازی شود
 */
public interface MessageService {
    /**
     * متد ترجمه کلید
     *
     * @param key    کلید ترجمه
     * @param locale زبان
     * @return خروجی: پیام ترجمه شده
     */
    @NotNull
    String getMessage(@NotNull String key, @NotNull Locale locale);


    /**
     * متد ترجمه کلید با داشتن متغیرهای داخل ترجمه
     *
     * @param key          کلید ترجمه
     * @param argumentList متغیرهای داخل ترجمه
     * @param locale       زبان
     * @return خروجی: پیام ترجمه شده
     */
    @NotNull
    String getMessage(@NotNull String key, @NotNull List<String> argumentList, @NotNull Locale locale);

}
