package com.ManShirtShop.service.exchange_detail.impl;

import com.ManShirtShop.common.mapperUtil.ObjectMapperUtils;
import com.ManShirtShop.dto.client.product.ProductDetailResponseClient;
import com.ManShirtShop.dto.exhange_detail.ExchangeDetailRequestAdmin;
import com.ManShirtShop.dto.exhange_detail.ExchangeDetailResponse;
import com.ManShirtShop.entities.Exchange;
import com.ManShirtShop.entities.ExchangeDetail;
import com.ManShirtShop.entities.ProductDetail;
import com.ManShirtShop.entities.ProductDiscount;
import com.ManShirtShop.repository.EmployeeRepository;
import com.ManShirtShop.repository.ExchangeDetailRepository;
import com.ManShirtShop.repository.ProductDetailRepository;
import com.ManShirtShop.repository.ProductDiscountRepository;
import com.ManShirtShop.service.exchange_detail.ExchangeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeDetailServiceImpl implements ExchangeDetailService {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ExchangeDetailRepository exchangeDetailRepository;

    @Autowired
    ProductDiscountRepository productDiscountRepository;

    @Override
    @Transactional
    public List<ExchangeDetailResponse> create(List<ExchangeDetailRequestAdmin> request, Exchange exchange,String fullname) {
        List<ExchangeDetail> lst = new ArrayList<>();
        List<ProductDetail> lstProductDetail = new ArrayList<>();
        ExchangeDetail exchangeDetail = new ExchangeDetail();
        for (ExchangeDetailRequestAdmin x : request) {
            exchangeDetail = new ExchangeDetail();
            Optional<ProductDetail> productDetail = productDetailRepository.findById(x.getIdProductDetail());
            Optional<ProductDiscount> discount = productDiscountRepository
                    .getByProductI2dAndStatus(productDetail.get().getProduct().getId());
            if (!productDetail.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Không tìm thấy id Product Detail");
            }
            exchangeDetail.setCreateBy(fullname);
            exchangeDetail.setUpdateTime(Timestamp.from(Instant.now()));
            exchangeDetail.setExchange(exchange);
            exchangeDetail.setProductDetail(productDetail.get());
            exchangeDetail.setQuantity(x.getQuantity());
            exchangeDetail.setUnitprice(productDetail.get().getProduct().getPrice());
            if (discount.isPresent()) {
                exchangeDetail.setDiscount(discount.get().getPercent());
            } else {
                exchangeDetail.setDiscount(0);
            }
            productDetail.get().setQuantity(productDetail.get().getQuantity() - x.getQuantity());
            lstProductDetail.add(productDetail.get());
            lst.add(exchangeDetail);
        }
        productDetailRepository.saveAll(lstProductDetail);
        lst = exchangeDetailRepository.saveAll(lst);
        return ObjectMapperUtils.mapAll(lst,ExchangeDetailResponse.class);
    }
}
