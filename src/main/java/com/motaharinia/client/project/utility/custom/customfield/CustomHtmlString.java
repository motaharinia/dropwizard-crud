package com.motaharinia.client.project.utility.custom.customfield;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @author https://github.com/motaharinia<br>
 * این کلاس برای جابجا کردن داده های اپ تی ام ال استفاده میشود<br>
 * سامانه این فیلد را از فیلدهای معمولی رشته متمایز میکند و تگهای اچ تی ام ال داخل این فیلد را فیلتر نمیکند
 */
@Data
@NoArgsConstructor
public class CustomHtmlString implements Serializable {

    @JsonProperty("customHtmlString")
    private String content;


    @Override
    public String toString() {
        return "CustomHtmlString{" + "customHtmlString=" + content + '}';
    }


    /**
     * بررسی میکند آیا کاستوم ورودی نال یا خالی هست
     *
     * @param customHtmlString کاستوم ورودی
     * @return خروجی: نتیجه بررسی
     */
    public static boolean isEmpty(CustomHtmlString customHtmlString) {
        if (ObjectUtils.isEmpty(customHtmlString)) {
            return true;
        }
        return ObjectUtils.isEmpty(customHtmlString.getContent());
    }

}
