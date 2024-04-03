package com.ManShirtShop.dto.Statistic;
import com.ManShirtShop.dto.order_the_store.OrderResponeAdmin;
import com.ManShirtShop.dto.returns.ReturnResponseAdminGetAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReturnDetailResponse {
    OrderResponeAdmin OrderResponeAdmin;
    ReturnResponseAdminGetAll returnResponseAdminGetAll;
}
