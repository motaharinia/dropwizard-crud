package com.motaharinia.client.project.utility.custom.customdto.search.filter.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* @author https://github.com/motaharinia<br>
 * این مدل یک دستور مرتب سازی را تشریح میکند که چه فیلدی چه نوع مرتب سازی داشته باشد
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilterSortDto implements Serializable {
    /**
     * نام فیلدی که میخواهیم روی آن ترتیب اعمال شود
     */
    private String fieldName;
    /**
     * نوع مرتب سازی نزولی و یا صعودی فیلد مورد نظر
     */
    private SearchFilterSortTypeEnum type;
}
