package com.ManShirtShop.dto.exchange;

import com.ManShirtShop.dto.customer.CustomerResponse;
import com.ManShirtShop.dto.exhange_detail.ExchangeDetailResponse;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class EchangeResponseAadmin {

    private Integer id;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String updateBy;

    private String createBy;
    private String code;
    private String updateName;
    private double freight;
    private String shipName;
    private String shipAddress;
    private String shipPhone;
    private String note;
    private double total;
    private Integer statusPay;

    private boolean saleForm;
    private Integer idCity;
    private Integer idDistrict;
    private String idWard;
    private String codeGhn;

    private CustomerResponse customer;
    private Integer employeeID;
    private List<ExchangeDetailResponse> exchangeDetail;
    private String returnCode;

}
