package com.motaharinia.crud.utility.tools.string;


/**
 * Created by IntelliJ IDEA.
 * @author https://github.com/motaharinia
 * مقادیر ثابت نوع ترکیب رشته تصادفی
 */
public enum RandomGenerationTypeEnum {

    /**
     *ترکیبی از حروف الفبای لاتین
     */
    LATIN_CHARACTERS("LATIN_CHARACTERS"),
    /**
     *ترکیبی از حروف الفبای لاتین کوچک
     */
    LATIN_LOWER_CHARACTERS("LATIN_LOWER_CHARACTERS"),
    /**
     * ترکیبی از حروف الفبای لاتین بزرگ
     */
    LATIN_UPPER_CHARACTERS("LATIN_UPPER_CHARACTERS"),
    /**
     * ترکیبی از اعداد
     */
    NUMBER("NUMBER"),
    /**
     * ترکیبی از حروف الفبای لاتین و اعداد
     */
    LATIN_CHARACTERS_NUMBERS("LATIN_CHARACTERS_NUMBERS"),
    /**
     *ترکیبی از علائم نگارشی مانند کاما و ...
     */
    PUNCTUATIONS("PUNCTUATIONS"),
    /**
     * ترکیبی از حروف الفبای لاتین و اعداد و علائم نگارشی
     */
    LATIN_CHARACTERS_NUMBERS_PUNCTUATIONS("LATIN_CHARACTERS_NUMBERS_PUNCTUATIONS"),
    /**
     * ترکیبی از حروف الفبای لاتین و اعداد و زیرخط
     */
    LATIN_CHARACTERS_NUMBERS_UNDERLINE("LATIN_CHARACTERS_NUMBERS_UNDERLINE"),
    /**
     *  ترکیبی از اعداد و زیرخط
     */
    NUMBERS_UNDERLINE("NUMBERS_UNDERLINE"),
    /**
     *ترکیبی از حروف الفبای پارسی و فاصله
     */
    PERSIAN_CHARACTERS_SPACE("PERSIAN_CHARACTERS_SPACE"),
    /**
     *ترکیبی از حروف الفبای پارسی و فاصله و اعداد
     */
    PERSIAN_CHARACTERS_SPACE_NUMBERS("PERSIAN_CHARACTERS_SPACE_NUMBERS"),
    ;
    private final String value;

    RandomGenerationTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
