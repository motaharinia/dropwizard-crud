package com.motaharinia.client.project.utility.custom.customjson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.motaharinia.client.project.utility.tools.string.MessageService;
import com.motaharinia.client.project.utility.tools.string.StringTools;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.util.Locale;

/**
 * @author https://github.com/motaharinia<br>
 * این کلاس برای تبدیل رشته به رشته جیسون برای ارسال به سمت کلاینت میباشد<br>
 * این کلاس در صورتی که رشته جیسون داده شده از نوع پیامهای ترجمه شدنی باشد آن را ترجمه میکند<br>
 */
public class JsonSerializerString extends JsonSerializer<String> {

    private final MessageService messageService;

    public JsonSerializerString(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void serialize(String objString, JsonGenerator jsonGen, SerializerProvider provider) throws IOException {
        if ((!ObjectUtils.isEmpty(messageService)) && (!ObjectUtils.isEmpty(objString)) && (objString.startsWith("USER_MESSAGE."))) {
            objString = StringTools.translateCustomMessage(messageService, objString, Locale.getDefault());
        }
        jsonGen.writeString(objString);
    }
}
