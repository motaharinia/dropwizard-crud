package com.motaharinia.client.project.utility.tools.pagination;


import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;


/**
 * @author https://github.com/motaharinia<br>
 * اینترفیس متدهای ابزاری صفحه بندی داده ها
 */
public interface PaginationTools {


    /**
     * متد صفحه بندی کننده لیست طبق شماره صفحه و تعداد سطر داخل صفحه مورد نظر
     *
     * @param allRowList  لیست کل داده ها
     * @param pageNo      شماره صفحه مورد نظر ، اولین صفحه عدد 1 است
     * @param pageRowSize تعداد سطر در هر صفحه
     * @param <T>         نوع داده
     * @return خروجی: لیست صفحه بندی شده
     */
    @NotNull
    static <T extends Serializable> List<T> paginateList(@NotNull List<T> allRowList, @NotNull Integer pageNo, @NotNull Integer pageRowSize) {
        if (ObjectUtils.isEmpty(allRowList)) {
            return allRowList;
        }
        //محاسبه اندیسهای ابتدا و انتها جهت برش از لیست کلی
        int fromIndex = (pageNo - 1) * pageRowSize;
        int toIndex;
        if (fromIndex >= allRowList.size()) {
            fromIndex = allRowList.size() - 1;
        }
        toIndex = fromIndex + pageRowSize;
        if (toIndex > allRowList.size()) {
            toIndex = allRowList.size() ;
        }
        return allRowList.subList(fromIndex, toIndex);
    }


}
