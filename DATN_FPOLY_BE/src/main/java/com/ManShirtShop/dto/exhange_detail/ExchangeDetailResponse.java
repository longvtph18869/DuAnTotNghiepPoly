package com.ManShirtShop.dto.exhange_detail;

import com.ManShirtShop.dto.client.product.ProductDetailResponseClient;
import lombok.Data;


@Data
public class ExchangeDetailResponse {

    private Integer id;
    private int quantity;
    private double unitprice;
    private Integer discount;

    private Integer exchangeId;

    private ProductDetailResponseClient productDetail;
}
