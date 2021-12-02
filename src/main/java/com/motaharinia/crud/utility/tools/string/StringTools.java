package com.motaharinia.crud.utility.tools.string;

import com.motaharinia.crud.config.mvc.MessageService;
import com.motaharinia.crud.utility.custom.customexception.utility.UtilityException;
import com.motaharinia.crud.utility.custom.customexception.utility.UtilityExceptionKeyEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author https://github.com/motaharinia
 *  اینترفیس پدر تمامی اینترفیسهای جستجوی پیشرفته دیتابیس که حکم میکند تمام آنها باید متد گتر آی دی را داشته باشند
 * تمام اینترفیسهای جستجوی پیشرفته دیتابیس باید از این اینترفیس گسترش یابند
 */
public interface StringTools {


    Random RANDOM = new Random();

    /**
     * این متد رشته و طول مورد نظر را از ورودی دریافت میکند و رشته هش شده بر مبنای الگوی رمزگذاری ام دی 5 با حداکثر طول درخواستی را تولید میکند
     *
     * @param input        رشته ورودی جهت رمزنگاری
     * @param resultLength حداکثر طول رشته هش شده خروجی
     * @return خروجی: رشته هش شده بر مبنای الگوی رمزگذاری ام دی 5 با حداکثر طول درخواستی
     * @throws NoSuchAlgorithmException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String generateMD5Hash(@NotNull String input, @NotNull Integer resultLength) throws NoSuchAlgorithmException {
        if (ObjectUtils.isEmpty(input)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(resultLength)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "resultLength");
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5sum = md.digest(input.getBytes());
        String output = String.format("%032x", new BigInteger(1, md5sum));
        if (resultLength <= input.length()) {
            return output.substring(0, resultLength);
        } else {
            return output;
        }
    }

    /**
     * این متد رشته ای را از ورودی دریافت میکند و تمام تگهای اچ تی ام ال آن را حذف میکند
     *
     * @param htmlString رشته ورودی
     * @return خروجی: رشته بدون تگهای اچ تی ام ال
     */
    @NotNull
    static String removeHtml(@NotNull String htmlString) {
        if (ObjectUtils.isEmpty(htmlString)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "htmlString");
        }
        return Jsoup.parse(htmlString).text();
    }

    /**
     * این متد رشته ورودی و طول مورد نظر رشته خروجی را دریافت میکند و رشته مورد نظر را به اندازه طول درخواستی کوتاه میکند
     * اگر طول رشته ورودی کمتر یا مساوی طول درخواستی باشد تغییری در رشته داده نمیشود
     * ولی در غیر این صورت رشته ورودی کوتاه شده و در انتهای آن سه نقطه قرار میگیرد
     *
     * @param input      رشته ورودی
     * @param charNumber طول مورد نظر
     * @return خروجی: رشته اصلاح شده طبق طول مورد نظر
     */
    @NotNull
    static String summarizeString(@NotNull String input, @NotNull Integer charNumber) {
        if (ObjectUtils.isEmpty(input)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(charNumber)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "charNumber");
        }
        String result;
        if (input.length() < charNumber) {
            result = input;
        } else {
            if (charNumber > 3) {
                result = input.substring(0, charNumber - 3) + "...";
            } else {
                result = input.substring(0, charNumber) + "...";
            }
        }
        return result;
    }

    /**
     * این متد رشته ورودی و عبارت مورد نظر را از ورودی میگیرد و آن عبارت در رشته را هایلایت مینماید
     *
     * @param inputText رشته ورودی
     * @param search    عبارت مورد نظر جهت هایلایت
     * @return خروجی: رشته با عبارات هایلایت شده
     */
    @NotNull
    static String highlight(@NotNull String inputText, @NotNull String search) {
        if (ObjectUtils.isEmpty(inputText)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputText");
        }
        if (ObjectUtils.isEmpty(search)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "search");
        }
        return inputText.replaceAll(search, "<span class='highlight'>" + search + "</span>");
    }

    /**
     * این متد رشته ای تصادفی را با توجه به نوع ، طول و اینکه اعداد با پیش صفر باشند یا خیر را تولید میکند
     *
     * @param randomGenerationTypeEnum نوع ترکیب رشته تصادفی
     * @param length                   طول رشته مورد نظر
     * @param withLeadingZero          با صفر شروع شود؟
     * @return خروجی: رشته تصادفی
     */
    @NotNull
    static String generateRandomString(@NotNull RandomGenerationTypeEnum randomGenerationTypeEnum, @NotNull Integer length, @NotNull Boolean withLeadingZero) {
        if (ObjectUtils.isEmpty(randomGenerationTypeEnum)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "randomGenerationTypeEnum");
        }
        if (ObjectUtils.isEmpty(length)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "length");
        }
        if (ObjectUtils.isEmpty(withLeadingZero)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withLeadingZero");
        }
        String characterLower = "abcdefghigklmnopqrstuvwxyz";
        String characterUpper = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
        String number = "1234567890";
        String punc = "_-$#@%^*&=+";
        String characterPersian = "ابپتثجچحخدذرزژسشصضطظعغفقکگلمنوهی";
        String characters = "";

        switch (randomGenerationTypeEnum) {
            case LATIN_CHARACTERS:
                characters = characterLower + characterUpper;
                break;
            case LATIN_LOWER_CHARACTERS:
                characters = characterLower;
                break;
            case LATIN_UPPER_CHARACTERS:
                characters = characterUpper;
                break;
            case NUMBER:
                characters = number;
                break;
            case LATIN_CHARACTERS_NUMBERS:
                characters = characterLower + characterUpper + number;
                break;
            case PUNCTUATIONS:
                characters = punc;
                break;
            case LATIN_CHARACTERS_NUMBERS_PUNCTUATIONS:
                characters = characterLower + characterUpper + number + punc;
                break;
            case LATIN_CHARACTERS_NUMBERS_UNDERLINE:
                characters = characterLower + characterUpper + number + "_";
                break;
            case NUMBERS_UNDERLINE:
                characters = number + "_";
                break;
            case PERSIAN_CHARACTERS_SPACE:
                characters = characterPersian + " ";
                break;
            case PERSIAN_CHARACTERS_SPACE_NUMBERS:
                characters = characterPersian + number + " ";
                break;
        }
        int charactersLength = characters.length();
        int randomNum;
        char randomChar;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomNum = RANDOM.nextInt(charactersLength);
            randomChar = characters.charAt(randomNum);
            if ((i == 0) && (!withLeadingZero)) {
                while (((int) randomChar == 48)) {
                    randomNum = RANDOM.nextInt((charactersLength - 1));
                    randomChar = characters.charAt(randomNum);
                }
            }
            //در حالت رشته ای در ابتدا یا انتها حرف فاصله نباشد
            if ((i == 0) || (i == length - 1)) {
                while (((int) randomChar == 32)) {
                    randomNum = RANDOM.nextInt((charactersLength - 1));
                    randomChar = characters.charAt(randomNum);
                }
            }
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }


    /**
     * متد ترجمه متن مورد نظر
     * @param messageService منبع ترجمه
     * @param customMessage کلید پیام برای ترجمه
     * @return خروجی: پیام ترجمه شده
     */
    static String translateCustomMessage(MessageService messageService, String customMessage) {
        if (!ObjectUtils.isEmpty(messageService) && !ObjectUtils.isEmpty(customMessage)) {
            if (customMessage.split("::").length > 1) {
                String[] customMessageArray = customMessage.split("::");
                String[] customMessageParametersArray = customMessageArray[1].split(",", -1);
                customMessage = messageService.getMessage(customMessageArray[0], Arrays.asList(customMessageParametersArray.clone()), Locale.getDefault());
            } else {
                customMessage = messageService.getMessage(customMessage, new ArrayList<>(), Locale.getDefault());
            }
        }
        return customMessage;
    }


}
