package com.motaharinia.client.project.utility.custom.customjson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.motaharinia.client.project.utility.custom.customjson.deserializer.JsonDeserializerString;
import com.motaharinia.client.project.utility.custom.customjson.serializer.JsonSerializerDate;
import com.motaharinia.client.project.utility.custom.customjson.serializer.JsonSerializerString;
import com.motaharinia.client.project.utility.tools.string.MessageService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;


import java.util.Date;

/**
 * @author eng.motahari@gmail.com<br>
 * این کلاس مپر برای تبدیل کلاسهای مدل به رشته جیسون و برعکس استفاده میشود. تفاوت آن نسبت به مپر پیش فرض داشتن مسیج سورس برای ترجمه است
 */

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper(MessageService messageService) {
        super();
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //تنظیم سریالایزر نال
//        DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
//        sp.setNullValueSerializer(new JsonSerializerNullValue());
//        this.setSerializerProvider(sp);

        SimpleModule simpleModule = new SimpleModule();
        //تنظیم سرایالایزرها برای تبدیل خودکار اطلاعات از مدل جاوا به رشته جیسون کلاینت
        simpleModule.addSerializer(Date.class, new JsonSerializerDate());
        if (!ObjectUtils.isEmpty(messageService)) {
            simpleModule.addSerializer(String.class, new JsonSerializerString(messageService));
//            simpleModule.addSerializer(CustomEnum.class, new JsonSerializerEnum(messageSource));
        }


        //تنظیم دیسریالایزرها برای تبدیل خودکار اطلاعات از رشته جیسون کلاینت به مدل جاوا
        simpleModule.addDeserializer(String.class, new JsonDeserializerString());
        this.registerModule(simpleModule);
    }

    public CustomObjectMapper() {
        super();
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //تنظیم سریالایزر نال
//        DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
//        sp.setNullValueSerializer(new JsonSerializerNullValue());
//        this.setSerializerProvider(sp);

        SimpleModule simpleModule = new SimpleModule();
        //تنظیم سرایالایزرها برای تبدیل خودکار اطلاعات از مدل جاوا به رشته جیسون کلاینت
        simpleModule.addSerializer(Date.class, new JsonSerializerDate());
        //تنظیم دیسریالایزرها برای تبدیل خودکار اطلاعات از رشته جیسون کلاینت به مدل جاوا
        simpleModule.addDeserializer(String.class, new JsonDeserializerString());
        this.registerModule(simpleModule);
    }

    @Override
    public ObjectMapper copy() {
        return this.toBuilder().build();
    }

}
