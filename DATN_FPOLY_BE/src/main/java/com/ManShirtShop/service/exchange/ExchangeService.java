package com.ManShirtShop.service.exchange;

import com.ManShirtShop.dto.exchange.EchangeRequestAdmin;

import java.util.Map;

public interface ExchangeService {
    Map create(EchangeRequestAdmin request);
    String updateStausGiaoHang(Integer id);

    Map updateStausHuy(Integer id);

    Map updateStausThanhCong(Integer id);

    Map updateStausGiaoThatBai(Integer id);
}
