package com.ManShirtShop.service.exchange_detail;

import com.ManShirtShop.dto.client.product.ProductDetailResponseClient;
import com.ManShirtShop.dto.exchange.EchangeRequestAdmin;
import com.ManShirtShop.dto.exhange_detail.ExchangeDetailRequestAdmin;
import com.ManShirtShop.dto.exhange_detail.ExchangeDetailResponse;
import com.ManShirtShop.entities.Exchange;

import java.util.List;

public interface ExchangeDetailService {

    List<ExchangeDetailResponse> create(List<ExchangeDetailRequestAdmin> request, Exchange exchange, String fullname);
}
