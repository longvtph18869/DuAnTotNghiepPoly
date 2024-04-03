package com.ManShirtShop.service.exchange.impl;

import com.ManShirtShop.Authentication.dto.user.EmailUser;
import com.ManShirtShop.common.contans.OrderContant;
import com.ManShirtShop.common.genCode.GenCode;
import com.ManShirtShop.common.mapperUtil.ObjectMapperUtils;
import com.ManShirtShop.dto.exchange.EchangeRequestAdmin;
import com.ManShirtShop.dto.exchange.EchangeResponseAadmin;
import com.ManShirtShop.dto.exhange_detail.ExchangeDetailResponse;
import com.ManShirtShop.entities.Customer;
import com.ManShirtShop.entities.Employee;
import com.ManShirtShop.entities.Exchange;
import com.ManShirtShop.entities.Return;
import com.ManShirtShop.repository.CustomerRepository;
import com.ManShirtShop.repository.EmployeeRepository;
import com.ManShirtShop.repository.ExchangeRepository;
import com.ManShirtShop.repository.ReturnRepository;
import com.ManShirtShop.rest.giao_hang_nhanh.GhnService;
import com.ManShirtShop.rest.giao_hang_nhanh.GhnServiceImpl;
import com.ManShirtShop.service.exchange.ExchangeService;
import com.ManShirtShop.service.exchange_detail.ExchangeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ReturnRepository returnRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    ExchangeDetailService exchangeDetailService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GhnService ghnService;

    @Override
    @Transactional
    public Map create(EchangeRequestAdmin request) {
        Map<String, Object> map = new HashMap<>();
        Optional<Integer> integer = exchangeRepository.findIdById(request.getReturnId());
        if (integer.isPresent()) {
            map.put("status", false);
            map.put("message", "Đã trả hàng!");
            map.put("data", null);
            return map;
        }
        if (request.getReturnId() == null || request.getReturnId() <= 0) {
            map.put("status", false);
            map.put("message", "Đơn trả không hợp lệ!");
            map.put("data", null);
            return map;
        }
        Optional<Return> returnDb = returnRepository.findById(request.getReturnId());
        if (!returnDb.isPresent()) {
            if (request.getReturnId() == null || request.getReturnId() <= 0) {
                map.put("status", false);
                map.put("message", "Không tìm thấy đơn trả!");
                map.put("data", null);
                return map;
            }
        }
        Optional<Customer> customer = customerRepository.findCustomerByRetunsId(request.getReturnId());
        Optional<Employee> employee = employeeRepository.findByEmail(EmailUser.getEmailUser());
        if (!customer.isPresent()) {
            if (request.getReturnId() == null || request.getReturnId() <= 0) {
                map.put("status", false);
                map.put("message", "Không tìm thấy khách hàng!");
                map.put("data", null);
                return map;
            }
        }
        Exchange exchange = ObjectMapperUtils.map(request, Exchange.class);
        exchange.setExchangeDetail(null);
        exchange.setEmployee(employee.get());
        exchange.setCustomer(customer.get());
        exchange.setCreateBy(employee.get().getFullname());
        exchange.setCreateTime(Timestamp.from(Instant.now()));
        exchange.setUpdateTime(Timestamp.from(Instant.now()));
        exchange = exchangeRepository.save(exchange);
        exchange.setStatus(OrderContant.STATUS_CHUAN_BI_HANG);
        List<ExchangeDetailResponse> exchangeDetailResponse = exchangeDetailService.create(
                request.getExchangeDetail(), exchange, employee.get().getFullname());
        Double total = 0.0;
        for (ExchangeDetailResponse x : exchangeDetailResponse) {
            if (x.getDiscount() > 0) {
                total = total + ((x.getUnitprice() - (x.getUnitprice() * x.getDiscount() / 100))
                        * x.getQuantity());
            } else {
                total = total + (x.getUnitprice() * x.getQuantity());
            }
        }
        if (total < 0) {
            total = 0.0;
        }
        exchange.setTotal(total);
        exchange.setCode("HDD" + GenCode.code(exchange.getId()));
        exchange = exchangeRepository.save(exchange);
        EchangeResponseAadmin echangeResponseAadmin = ObjectMapperUtils.map(exchange, EchangeResponseAadmin.class);
        echangeResponseAadmin.setReturnCode(returnDb.get().getCode());
        echangeResponseAadmin.setEmployeeID(employee.get().getId());
        map.put("status", true);
        map.put("message", "OK!");
        map.put("data", echangeResponseAadmin);
        return map;
    }

    @Override
    public String updateStausGiaoHang(Integer id) {
        return ghnService.ghncreateExchange(id);
    }

    @Override
    public Map updateStausHuy(Integer id) {
        Map<String, Object> map = new HashMap<>();
        String employee = employeeRepository.getFullNameByEmail(EmailUser.getEmailUser());
        Optional<Exchange> exchange = exchangeRepository.findById(id);
        if (!exchange.isPresent()) {
            map.put("status", false);
            map.put("messagge", "Không tìm thấy đơn đổi");
        }
        exchange.get().setStatus(OrderContant.STATUS_DA_HUY);
        exchange.get().setUpdateTime(Timestamp.from(Instant.now()));
        exchange.get().setCreateBy(employee);
        exchangeRepository.save(exchange.get());
        map.put("status", true);
        map.put("messagge", "Thành Công!");
        return map;
    }

    @Override
    public Map updateStausThanhCong(Integer id) {
        Map<String, Object> map = new HashMap<>();
        String employee = employeeRepository.getFullNameByEmail(EmailUser.getEmailUser());
        Optional<Exchange> exchange = exchangeRepository.findById(id);
        if (!exchange.isPresent()) {
            map.put("status", false);
            map.put("messagge", "Không tìm thấy đơn đổi");
        }
        exchange.get().setStatus(OrderContant.STATUS_THANH_CONG);
        exchange.get().setUpdateTime(Timestamp.from(Instant.now()));
        exchange.get().setCreateBy(employee);
        exchangeRepository.save(exchange.get());
        map.put("status", true);
        map.put("messagge", "Thành Công!");
        return map;
    }

    @Override
    public Map updateStausGiaoThatBai(Integer id) {
        Map<String, Object> map = new HashMap<>();
        String employee = employeeRepository.getFullNameByEmail(EmailUser.getEmailUser());
        Optional<Exchange> exchange = exchangeRepository.findById(id);
        if (!exchange.isPresent()) {
            map.put("status", false);
            map.put("messagge", "Không tìm thấy đơn đổi");
        }
        exchange.get().setStatus(OrderContant.STATUS_GIAO_THAT_BAI);
        exchange.get().setUpdateTime(Timestamp.from(Instant.now()));
        exchange.get().setCreateBy(employee);
        exchangeRepository.save(exchange.get());
        map.put("status", true);
        map.put("messagge", "Thành Công!");
        return map;
    }


}
