package com.motaharinia.client.project.utility.custom.customjson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityException;
import com.motaharinia.client.project.utility.tools.string.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;

/**
 * @author eng.motahari@gmail.com<br>
 * این کلاس برای تبدیل رشته جیسون کلاینت به رشته میباشد<br>
 * این کلاس در صورتی که رشته جیسون داده شده از نوع فیلدهای با تگ وب باشد آن را خروجی میدهد<br>
 * در غیر این صورت اگر فیلد رشته مورد نظر فیلد رشته ساده باشد تگهای وب را که مشکل امنیتی ممکن است ایجاد کنند از رشته جیسون حذف مینماید
 */
@Slf4j
public class JsonDeserializerString extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        if (ObjectUtils.isEmpty(jp.getCurrentName()) || ObjectUtils.isEmpty(jp.getText())) {
            return jp.getText();
        } else {
            if (jp.getCurrentName().equalsIgnoreCase("htmlCustomString")) {
                return jp.getText();
            } else {
                try {
                    return StringTools.removeHtml(jp.getText());
                } catch (UtilityException exception) {
                    log.error("UTILITY_EXCEPTION.JsonDeserializerString.deserialize() exception:",exception);
                    return jp.getText();
                }
            }
        }
    }
}
