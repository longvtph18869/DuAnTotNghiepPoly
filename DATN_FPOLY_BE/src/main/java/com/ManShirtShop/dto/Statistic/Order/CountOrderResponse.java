package com.ManShirtShop.dto.Statistic.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountOrderResponse {
    private Integer month;
    private Integer orderSuccess;
    private Integer orderCancel;
    private Integer returnSuccess;
}
