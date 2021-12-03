package com.motaharinia.crud.utility.custom.customdto.search.data.columnconfig;

/**
* @author https://github.com/motaharinia<br>
 *     اینام مقادیر ثابت نحوه مرتب سازی مطالب
 */
public enum SearchDataColumnConfigSortTypeEnum {
    /**
     *مرتب سازی متنی
     */
    TEXT("TEXT"),
    /**
     *مرتب سازی عددی
     */
    NUMBER("NUMBERS")
    ;

    //نحوه مرتب سازی مطالب
    private String value;

    /**
     * تابع سازنده ثابت نحوه مرتب سازی مطالب از روی مقدار نحوه مرتب سازی مطالب آن
     *
     * @param value مقدار ثابت نحوه مرتب سازی مطالب
     */
    SearchDataColumnConfigSortTypeEnum(String value) {
        this.value = value;
    }

    /**
     * مقدار نحوه مرتب سازی مطالب را خروجی میدهد
     *
     * @return خروجی: مقدارنحوه مرتب سازی مطالب
     */
    public String getValue() {
        return this.value;
    }

}
