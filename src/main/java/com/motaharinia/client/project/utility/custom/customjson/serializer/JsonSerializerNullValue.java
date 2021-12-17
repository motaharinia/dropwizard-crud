package com.motaharinia.client.project.utility.custom.customjson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author eng.motahari@gmail.com<br>
 *     این کلاس برای تبدیل مقدار نال به رشته جیسون خالی برای ارسال به سمت کلاینت میباشد
 */
public class JsonSerializerNullValue extends JsonSerializer<Object> {

    @Override
    public void serialize(Object obj, JsonGenerator jsonGen, SerializerProvider sp) throws IOException {
        jsonGen.writeString("");
    }
}
