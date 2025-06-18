package com.AWS.Figma.Dashboard.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailySalesDto {

    private int day;
    private int itemsSold;

    // ✅ Hibernate calls this
    public DailySalesDto(Integer day, Long itemsSold) {
        this.day = day;                        // auto‑unboxing: Integer ➜ int
        this.itemsSold = itemsSold.intValue(); // Long ➜ int
    }

    // Optional: keep your other constructors for manual use
    public DailySalesDto(int day, int itemsSold) {
        this.day = day;
        this.itemsSold = itemsSold;
    }

    // (Optional) default ctor if frameworks ever need it
    public DailySalesDto() {}
}