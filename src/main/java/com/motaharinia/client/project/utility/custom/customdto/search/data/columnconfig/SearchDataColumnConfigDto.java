package com.motaharinia.client.project.utility.custom.customdto.search.data.columnconfig;

import com.motaharinia.client.project.utility.custom.customdto.search.filter.restriction.SearchFilterOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
* @author https://github.com/motaharinia<br>
 * از این کلاس مدل برای تنظیم کردن مشخصات نمایشی ستونهای خروجی داده استفاده میشود
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDataColumnConfigDto implements Serializable {
    /**
     * نام فیلد ستون
     */
    private String name;
    /**
     * اندیس و ترتیب قرارگیری ستون
     */
    private String index;
    /**
     * جهت نمایش افقی
     */
    private SearchDataColumnConfigAlignEnum align = SearchDataColumnConfigAlignEnum.CENTER;
    /**
     * عرض ستون
     */
    private Integer width;
    /**
     * نوع مرتب سازی ستون که عددی یا متنی است
     */
    private SearchDataColumnConfigSortTypeEnum sortType;
    /**
     * نوع جستجوی ستون که متنی یا انتخابی است
     */
    private SearchDataColumnConfigSearchTypeEnum searchType;
    /**
     * لیستی از آپشنهای جستجوی پیشرفته قابل استفاده در این ستون که بیشتر در جستجوی پیشرفته استفاده میشود
     */
    private List<SearchFilterOperationEnum> searchFilterOperationList;
    /**
     * رشته فرمت کننده ستون
     * مثلا میخواهیم برای مقادیر صفر در ستون کلمه خیر بیاریم و برای مقادیر یک در ستون کلمه بلی بیاریم
     */
    private String formatter;
    /**
     * جستجوی پیشرفته دارد یا خیر
     */
    private Boolean searchable;
    /**
     * قابل مرتب سازی داده هست یا خیر
     */
    private Boolean sortable;

}
