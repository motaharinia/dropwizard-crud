
package com.motaharinia.crud.utility.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author https://github.com/motaharinia<br>
 *     این کلاس به عنوان کلاس پدر تمام کلاسهای انتیتی آماده شده است و به تمام انتیتی ها فیلدهای استانداردی را اضافه میکتد
 */
@Data
public class CustomEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * آی پی کاربری که انتیتی را ایجاد کرده است
     */
    private String createUserIp;
    /**
     * آی پی کاربری که انتیتی را آخرین بار ویرایش کرده است
     */
    private String updateUserIp;

    /**
     * عدم نمایش انتیتی در بیزینس نرم افزار
     */
    private Boolean hidden = false;
    /**
     * غیر فعال کردن انتیتی در بیزینس نرم افزار
     */
    private Boolean invalid = false;

    /**
     * تاریخی که انتیتی ایجاد شده است
     */
    private LocalDateTime createAt;
    /**
     * تاریخی که انتیتی آخرین بار ویرایش شده است
     */
    private LocalDateTime updateAt;

    /**
     * آی دی کاربری که انتیتی را ایجاد کرده است
     */
    private Long createUserId;
    /**
     * آی دی کاربری که انتیتی را آخرین بار ویرایش کرده است
     */
    private Long updateUserId;
}
