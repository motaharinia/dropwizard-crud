package com.motaharinia.client.project.utility.document.customcounter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


/**
 * کلاس داکیومنت شمارنده
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomCounterDocument {
    /**
     * شناسه رشته ای کلاسی که میخواهیم برای آن کلید اصلی بسازیم
     */
    @Id
    private String id;

    /**
     * کلید اصلی
     */
    private Long primaryKey;
}
