package com.ManShirtShop.dto.exchange;

import com.ManShirtShop.dto.exhange_detail.ExchangeDetailRequestAdmin;
import com.ManShirtShop.entities.Customer;
import com.ManShirtShop.entities.Employee;
import com.ManShirtShop.entities.ExchangeDetail;
import com.ManShirtShop.entities.Return;
import lombok.Data;
import java.util.List;

@Data
public class EchangeRequestAdmin {

    private Integer id;
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

    private List<ExchangeDetailRequestAdmin> exchangeDetail;
    private Integer returnId;

}
