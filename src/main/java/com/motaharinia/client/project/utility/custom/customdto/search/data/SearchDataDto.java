package com.motaharinia.client.project.utility.custom.customdto.search.data;



import com.motaharinia.client.project.utility.custom.customdto.search.filter.SearchFilterDto;
import com.motaharinia.client.project.utility.tools.pagination.PaginationTools;
import com.motaharinia.client.project.utility.custom.customdto.search.data.columnconfig.SearchDataColumnConfigDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/motaharinia<br>
 * این کلاس مدل حاوی نتیجه جستجوی اطلاعات میباشد
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDataDto<T extends Serializable> implements Serializable {
    /**
     * تعداد کل سطرهای قابل نمایش که صفحه بندی شده اند و به صفحات کوچکتر تبدیل شده اند
     */
    private Long totalRecordSize;
    /**
     * شماره صفحه فعلی
     */
    private Integer pageNo;
    /**
     * لیست سطرهای داده صفحه
     */
    private List<T> pageRowList = new ArrayList<>();
    /**
     * تعداد کل صفحات در صفحه بندی اطلاعات
     */
    private Integer totalPageSize;
    /**
     * لیست تنظیمات ستونها
     */
    private List<SearchDataColumnConfigDto> columnConfigList = new ArrayList<>();
    /**
     * اطلاعات اضافی
     */
    private Map<String, String> userDataMap = new HashMap<>();


    /**
     * متد صفحه بندی مدل حاوی نتیجه جستجوی اطلاعات بر اساس مدل فیلتر جستجو و لیست کل داده ها
     *
     * @param searchFilterDto  مدل فیلتر جستجو
     * @param allRowList       لیست کل داده ها
     * @param columnConfigList لیست تنظیمات ستونها
     * @param userDataMap      اطلاعات اضافی
     * @param <T>              نوع داده
     * @return خروجی: مدل حاوی نتیجه جستجوی اطلاعات
     */
    public static <T extends Serializable> SearchDataDto<T> paginateAllRowList(SearchFilterDto searchFilterDto, List<T> allRowList, List<SearchDataColumnConfigDto> columnConfigList, Map<String, String> userDataMap) {
        SearchDataDto<T> searchDataDto = new SearchDataDto<>();
        searchDataDto.setTotalRecordSize((long) allRowList.size());
        searchDataDto.setPageNo(searchFilterDto.getPageNo());
        searchDataDto.setPageRowList(PaginationTools.paginateList(allRowList, searchFilterDto.getPageNo(), searchFilterDto.getPageRowSize()));
        Long totalPageSize = (searchDataDto.getTotalRecordSize() + searchFilterDto.getPageRowSize() - 1) / searchFilterDto.getPageRowSize();
        searchDataDto.setTotalPageSize(totalPageSize.intValue());
        searchDataDto.setColumnConfigList(columnConfigList);
        searchDataDto.setUserDataMap(userDataMap);
        return searchDataDto;
    }
}
